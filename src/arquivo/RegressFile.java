package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import conexao.OracleConnection;
import dao.DamDao;
import utilitario.RegressTreatment;
import vo.ConfigXml;
import vo.Dam;

public class RegressFile extends ConciliacaoFiles {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String header;

	private ArrayList<Dam> dams = new ArrayList<>();

	private String footer;
	
	private ConfigXml configXml;

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
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
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
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

	public RegressFile(Path path, ConfigXml configXml) throws Exception {
		super(path);
		this.setConfigXml(configXml);
		this.populate();
	
	}
	
	private int[] getValueField(Field fieldName) throws Exception{
		return this.configXml.getLayout().getColStartEndByAttribute(fieldName.getName());

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
			this.header = linha;
			
			while (linha != null) {
				linha = lerArquivo.readLine();
				linhas.add(linha);
			}
		}else{
			
			NullPointerException ex = new NullPointerException("O arquivo de retorno está vazio, sem o Header ou não foi reconhecido. "
					+ "Por favor verifique o arquivo: "
					+ this.getName());
			throw ex;
		}
		
		for(int index = 1; index < linhas.size(); index++){
			String idFooter = linhas.get(linhas.size() - 1);
			
			if(idFooter != null && idFooter.substring(0,1).equals("Z")){
				this.footer = idFooter;
				linhas.remove(linhas.size() - 1);
				break;
			}

			linhas.remove(linhas.size() - 1);
		}

		for (String lin : linhas) {
			int colRange[];
			Dam dam = new Dam();
			
			//Efetua a captura do codigoLote EXCLUSIVAMENTE no cabeçalho do arquivo.
			colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[0].getName());
			dam.getClass().getField(fieldsDam[0].getName()).set(dam, Integer.parseInt(this.header.substring(colRange[0], colRange[1])));
			//-Fim codigoLote

			//Efetua a captura do numSeq EXCLUSIVAMENTE na variavel deste metodo numSeq, a mesma é auto incrementada.
			colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[1].getName());
			dam.getClass().getField(fieldsDam[1].getName()).set(dam, numSeq);
			//-Fim numSeq

			//Efetua a captura do SeqDuplicacao EXCLUSIVAMENTE via consulta a tabela do dam.
			colRange = this.configXml.getLayout().getColStartEndByAttribute("numDam");
			seqDuplicacao = new DamDao(OracleConnection.connect(this.configXml)).countDam15(lin.substring(colRange[0], colRange[1]));
			dam.getClass().getField(fieldsDam[2].getName()).set(dam, seqDuplicacao);
			//-Fim SeqDuplicacao
			
			for(int index = 3;index < fieldsDam.length; index++){
				colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[index].getName());
				String dadoCampo = lin.substring(colRange[0], colRange[1]).trim();
				
				if(colRange[1] != 0 && colRange[0] <= colRange[1] && !dadoCampo.isEmpty()){
					switch (fieldsDam[index].getType().getName().trim()) {
						case "int":
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, Integer.parseInt(dadoCampo));
							break;
						case "float":
							 //Efetua a captura do float consideranco que os 2 ultimos caracteres da String serão as casas decimais.
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, 
									Float.parseFloat(
											dadoCampo.substring(0, 
															dadoCampo.length()-2)+"."+dadoCampo.substring(dadoCampo.length()-2, dadoCampo.length())));
							break;
						case "java.lang.String":
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, dadoCampo);
							break;
						case "java.util.Calendar":
							Calendar c = Calendar.getInstance();
							c.set(Integer.parseInt(dadoCampo.substring(0, 4)), 
									Integer.parseInt(dadoCampo.substring(4, 6)) -1, 
									Integer.parseInt(dadoCampo.substring(6, 8)));
							
							dam.getClass().getField(fieldsDam[index].getName()).set(dam, c);
							break;
					}
				}
			}

			this.dams.add(dam);
			numSeq++;
		}
		
		this.validate();
		
	}
	
	private void validate() throws Exception{
		RegressTreatment.isValidRegressFile(this);
	}

}
