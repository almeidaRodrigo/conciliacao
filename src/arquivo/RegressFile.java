package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import conexao.ObterConexao;
import dao.DamDao;
import dao.LoteDao;
import utilitario.ObterCalendar;
import utilitario.RegressTreatment;
import vo.ConfigXml;
import vo.Dam;
import vo.Trailler;
import vo.Header;
import vo.Lote;
import vo.TipoDamEnum;

public class RegressFile extends ConciliacaoFiles {

	private static final long serialVersionUID = 1L;
	private Header header;
	private ArrayList<Dam> dams = new ArrayList<>();
	private Trailler trailler;
	private ConfigXml configXml;
	private TipoDamEnum tipoDam;
	private BigDecimal totalValorDams;
	private int totalQtdLinhas = 0;
	private Lote lote;
	
	public RegressFile(Path path, ConfigXml configXml, TipoDamEnum tipoDam) throws Exception {
		super(path);
		this.setConfigXml(configXml);
		this.tipoDam = tipoDam;
		this.populate();
		this.gerarLote(this);
		this.setDams(this.geraDataCredito(this.getDams()));
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * @return the dams
	 */
	public ArrayList<Dam> getDams() {
		return dams;
	}

	/**
	 * @param dams the dams to set
	 */
	public void setDams(ArrayList<Dam> dams) {
		this.dams = dams;
	}

	/**
	 * @return the trailler
	 */
	public Trailler getTrailler() {
		return trailler;
	}

	/**
	 * @param trailler the trailler to set
	 */
	public void setTrailler(Trailler trailler) {
		this.trailler = trailler;
	}

	/**
	 * @return the configXml
	 */
	public ConfigXml getConfigXml() {
		return configXml;
	}

	/**
	 * @param configXml the configXml to set
	 */
	public void setConfigXml(ConfigXml configXml) {
		this.configXml = configXml;
	}
	
	/**
	 * @return the tipoDam
	 */
	public TipoDamEnum getTipoDam() {
		return this.tipoDam;
	}
	
	/**
	 * @param tipoDam the tipoDam to set
	 */
	public void setTipoDam(TipoDamEnum tipoDam) {
		this.tipoDam = tipoDam;
	}
	
	/**
	 * @return the totalValorDams
	 */
	public BigDecimal getTotalValorDams() {
		return totalValorDams;
	}

	/**
	 * @param totalValorDams the totalValorDams to set
	 */
	public void setTotalValorDams(BigDecimal totalValorDams) {
		this.totalValorDams = totalValorDams;
	}

	/**
	 * @return the totalQtdLinhas
	 */
	public int getTotalQtdLinhas() {
		//Adcionado "+2" para refletir a quantidade dos DAMS + o cabeçalho e rodape.
		return totalQtdLinhas + 2;
	}

	/**
	 * @param totalQtdLinhas the totalQtdLinhas to set
	 */
	public void setTotalQtdLinhas(int totalQtdLinhas) {
		this.totalQtdLinhas = totalQtdLinhas;
	}
	
	/**
	 * @return the lote
	 */
	public Lote getLote() {
		return lote;
	}

	/**
	 * @param lote the lote to set
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
	}

	private void populate() throws Exception{
		/*
		 * numSeq: Inicia em 1, pois, o sequencial de boletos despreza a linha 0 (que se refere ao cabeçalho - Header).
		 * É o numero sequencial das linhas do arquivo, correspondente a cada pagamento.
		 */
		int numSeq = 1;
		
		/*
		 * seqDuplicacao: resultado do contador na tabela do dam. É o valor correspondente da quantidade de vezes da ocorrencia do DAM.
		 */
		int seqDuplicacao;
		
		String linha;
		ArrayList<String> linhas = new ArrayList<>();
		
		Field[] fieldsDam = new Dam().getClass().getDeclaredFields();
		
		FileReader arq = this.openFileReader();
		BufferedReader lerArquivo = new BufferedReader(arq);
		
		linha = lerArquivo.readLine();

		if(linha != null){
			Calendar cLote = ObterCalendar.obterCalendar(linha.substring(65, 73)); 
			
			this.header = new Header(
					linha.substring(0, 1), 
					linha.substring(1, 2), 
					linha.substring(2, 22),
					linha.substring(22, 42), 
					linha.substring(42, 45), 
					cLote, 
					new BigInteger(linha.substring(73, 79)), 
					linha.substring(79, 81), 
					linha.substring(81, 98));

			while (linha != null) {
				linha = lerArquivo.readLine();
				linhas.add(linha);
			}
			
			lerArquivo.close();
			arq.close();
			
		}else{
			
			NullPointerException ex = new NullPointerException("O arquivo de retorno está vazio, sem o Header ou não foi reconhecido. "
					+ "Por favor verifique o arquivo: "
					+ this.getName());
			throw ex;
		}

		for(int index = 0; index < linhas.size(); index++){
			String idFooter = linhas.get((linhas.size()-1));

			if(idFooter != null){
				if(idFooter.substring(0,1).equals("Z")){
					String valorTotal = idFooter.substring(7,25);
					valorTotal = Integer.parseInt(valorTotal.substring(0, valorTotal.length()-3))+"."+valorTotal.substring(valorTotal.length()-3, valorTotal.length());

					this.trailler = new Trailler(
							idFooter.substring(0,1), 
							Integer.parseInt(idFooter.substring(1,7)), 
							new BigDecimal(NumberFormat.getInstance(Locale.US).parse(valorTotal).toString()));

					linhas.remove((linhas.size()-1));
					break;
				}
			}

			linhas.remove((linhas.size()-1));
		}

		for (String lin : linhas) {
			int colRange[];
			Dam dam = new Dam();
			
			this.setTotalQtdLinhas(numSeq);

			//Primeiro Atributo do DAM: Efetua a captura do numSeq EXCLUSIVAMENTE na variavel deste metodo numSeq, a mesma é auto incrementada.
			colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[1].getName());
			dam.getClass().getField(fieldsDam[0].getName()).set(dam, numSeq);
			//-Fim numSeq

			//Segundo Atributo do DAM: Efetua a captura do SeqDuplicacao EXCLUSIVAMENTE via consulta a tabela do dam.
			colRange = this.configXml.getLayout().getColStartEndByAttribute("numDam");
			seqDuplicacao = new DamDao(ObterConexao.connect(this.configXml)).countDam(lin.substring(colRange[0], colRange[1]), this.tipoDam);
			dam.getClass().getField(fieldsDam[1].getName()).set(dam, seqDuplicacao);
			//-Fim SeqDuplicacao
			
			//Demais Atributos do DAM: preenchimento condicional.
			for(int index = 2;index < fieldsDam.length; index++){
				colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[index].getName());
				String dadoCampo = lin.substring(colRange[0], colRange[1]).trim();
				
				if(colRange[1] != 0 && colRange[0] <= colRange[1] && !dadoCampo.isEmpty()){
					switch (fieldsDam[index].getType().getName().trim()) {
						case "int":
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, Integer.parseInt(dadoCampo));
							break;
						case "java.math.BigDecimal":
							//Efetua a captura do float consideranco que os 2 ultimos caracteres da String serão as casas decimais.
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, 
									new BigDecimal(
											dadoCampo.substring(0, dadoCampo.length()-2)
											+"."
											+dadoCampo.substring(dadoCampo.length()-2, dadoCampo.length())));
							break;
						case "java.lang.String":
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, dadoCampo);
							break;
						case "java.util.Calendar":
							Calendar c = ObterCalendar.obterCalendar(dadoCampo);
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, c);
							break;
					}
				}
			}

			this.dams.add(dam);
			numSeq++;
		}

		this.setTotalValorDams(this.somaDams(this.getDams()));
		this.validate();

	}
	
	private void gerarLote(RegressFile regressFile) throws Exception{
		Header header = this.getHeader();

		Lote lote = new Lote(
				new LoteDao(ObterConexao.connect(this.getConfigXml())).getProximoCodigoLote(this.getTipoDam()),
				header.getCodigoBanco(),
				header.getNumLote(), 
				header.getDataGeracaoArquivo(), 
				regressFile.getTotalQtdLinhas()-2,//Excluindo Header e Trailler (por isso '-2').
				regressFile.getTotalValorDams());

		this.setLote(lote);
	}
	
	private BigDecimal somaDams(ArrayList<Dam> lDams){
		BigDecimal somaDams = new BigDecimal("0");
		
		for (Dam dam : lDams) {
			if(dam.getValorPago()!= null)
				somaDams = somaDams.add(dam.getValorPago());
		}

		return somaDams;
	}
	
	private ArrayList<Dam> geraDataCredito(ArrayList<Dam> lDams){
		ArrayList<Dam> lDamsRetorno = new ArrayList<>();
		Calendar novaData;
		
		for (Dam dam : lDams) {
			novaData = Calendar.getInstance();
			novaData.setTimeInMillis(dam.getDataArrecadacao().getTimeInMillis());
			
			switch (dam.getDataArrecadacao().get(Calendar.DAY_OF_WEEK)) {
			case 5:
			case 6:
				novaData.add(Calendar.DAY_OF_MONTH, 4);
				break;
			case 7:
				novaData.add(Calendar.DAY_OF_MONTH, 3);
				break;
			default:
				novaData.add(Calendar.DAY_OF_MONTH, 2);
				break;
			}
			
			dam.setDataCredito(novaData);
			
			lDamsRetorno.add(dam);
			
		}
		
		return lDamsRetorno;
	}
	
	private Boolean validate() throws Exception{
		return RegressTreatment.isValidRegressFile(this);
	}

}
