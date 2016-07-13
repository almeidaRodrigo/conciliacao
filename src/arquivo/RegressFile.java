package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;

import com.thoughtworks.xstream.converters.reflection.FieldDictionary;

import conexao.OracleConnection;
import dao.DamDao;
import vo.ConfigXml;
import vo.Dam;
import vo.Layout;
import vo.LayoutDam;

public class RegressFile extends ConciliacaoFiles {

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
		System.out.println("Teste de entrada no construtor - Ok a1");
		this.setConfigXml(configXml);
		System.out.println("Teste de entrada no construtor - Ok a2");
		this.populate();
		System.out.println("Teste de entrada no construtor - Ok a3");
	
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
		int colRange[];
		Dam dam = new Dam();
		
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
		//--
		Field[] fieldsDam = new Dam().getClass().getDeclaredFields();
		//--
		
		this.footer = linhas.get(linhas.size() - 1);
		linhas.remove(linhas.size() - 1);

		//-- 11/07/2016	
		for (String lin : linhas) {
			//TODO: implementar a leitura abstrata de campos efetuando busca com o metodo getColStartEndByAttribute da classe Layout
			
			//Efetua a captura do codigoLote EXCLUSIVAMENTE no cabeçalho do arquivo.
			colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[0].getName());
			dam.getClass().getField(fieldsDam[0].getName()).set(dam, Integer.parseInt(this.header.substring(colRange[0], colRange[1])));
			//-Fim codigoLote

			//Efetua a captura do numSeq EXCLUSIVAMENTE na variavel deste metodo numSeq, a mesma é auto incrementada.
			colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[1].getName());
			dam.getClass().getField(fieldsDam[1].getName()).set(dam, numSeq);
			//-Fim numSeq

			//Efetua a captura do SeqDuplicacao EXCLUSIVAMENTE via consulta a tabela do dam.
			colRange = this.configXml.getLayout().getColStartEndByAttribute("NumDam");
			seqDuplicacao = new DamDao(OracleConnection.connect(this.configXml)).countDam15(lin.substring(colRange[0], colRange[1]));
			dam.getClass().getField(fieldsDam[2].getName()).set(dam, seqDuplicacao);
			//-Fim SeqDuplicacao
			
			for(int i = 3;i < fieldsDam.length; i++){
				colRange = this.configXml.getLayout().getColStartEndByAttribute(fieldsDam[i].getName());
				//System.out.println(fieldsDam.length);
				//System.out.println(i);
				
				
				switch (fieldsDam[i].getType().getName().trim()) {
				case "int":
					dam.getClass().getField(fieldsDam[i].getName()).set(dam, Integer.parseInt((lin.substring(colRange[0], colRange[1]).isEmpty()?"0":lin.substring(colRange[0], colRange[1]))));
					break;
				case "java.lang.String":
					dam.getClass().getField(fieldsDam[i].getName()).set(dam, lin.substring(colRange[0], colRange[1]).trim());
					break;
				case "java.util.Calendar":
					Calendar c = Calendar.getInstance();
					c.set(Integer.parseInt(lin.substring(colRange[0], colRange[1]).trim().substring(0, 4)), 
							Integer.parseInt(lin.substring(colRange[0], colRange[1]).trim().substring(4, 6)), 
							Integer.parseInt(lin.substring(colRange[0], colRange[1]).trim().substring(6, 8)));
					dam.getClass().getField(fieldsDam[i].getName()).set(dam, c);
					break;
				}
				
				if(i == 9){
					System.out.println("QTD de indices: " + fieldsDam.length);
					System.out.println("Indice atual: " + i);
					System.out.println(fieldsDam[i].getName());
					//System.out.println(lin.substring(colRange[0], colRange[1]).trim());
					System.out.println(lin.substring(colRange[0], colRange[1]).trim().substring(6, 8));
					System.out.println(fieldsDam[i].getType().getName().trim());
					System.out.println(dam.getDataArrecadacao());
					System.out.println("-");
					
					
					System.exit(0);
				}
				
			
			}
			
			System.exit(0);
			//int colRange[] = getValueField(fieldsDam[9]);
			//int dataArrecadacaoYear = lin.substring(colRange[0], colRange[1])
					
			Calendar dataArrecadacao = Calendar.getInstance();
			Calendar dataCredito = Calendar.getInstance();	

			//TODO: Fazer com que estas datas recebam os parametros do arquivo XML
			dataArrecadacao.set(Calendar.YEAR, Integer.parseInt(lin.substring(21, 24)));
			dataArrecadacao.set(Calendar.MONTH, Integer.parseInt(lin.substring(25, 26)));
			dataArrecadacao.set(Calendar.DAY_OF_MONTH, Integer.parseInt(lin.substring(27, 28)));

			//TODO: Fazer com que estas datas recebam os parametros do arquivo XML						
			dataCredito.set(Calendar.YEAR, Integer.parseInt(lin.substring(29, 32)));
			dataCredito.set(Calendar.MONTH, Integer.parseInt(lin.substring(33, 34)));
			dataCredito.set(Calendar.DAY_OF_MONTH, Integer.parseInt(lin.substring(35, 36)));
			/*
			//TODO: Fazer com que este construtor receba os parametros do arquivo XML
			Dam dam = new Dam(Integer.parseInt(this.header.substring(73, 78)), 
					numSeq, 
					lin.substring(108, 115), 
					lin.substring(61, 68), 
					0, 
					0, 
					"000", 
					lin.substring(69, 79), 
					"", 
					dataArrecadacao, 
					dataCredito, 
					Float.parseFloat(lin.substring(81, 92)), 
					lin.substring(116, 116), 
					Float.parseFloat(lin.substring(93, 99)));
			*/
			numSeq++;
			
			this.dams.add(dam);
		}
		
	}
	
	

	

}
