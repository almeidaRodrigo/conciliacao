package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;

import com.thoughtworks.xstream.converters.reflection.FieldDictionary;

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
		this.setConfigXml(configXml);
		this.populate();
	
	}
	
	private void populate() throws Exception{
		String linha;
		int numSeq = 2;//Inicia em 2, pois, o sequencial de boletos despreza a linha 1 (que se refere ao cabeçalho - Header)
		ArrayList<String> linhas = new ArrayList<>();
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
		Layout layout = new Layout();
		//--
		
		this.footer = linhas.get(linhas.lastIndexOf(linhas));
		
		linhas.remove(linhas.lastIndexOf(linhas));
		
		for (String lin : linhas) {
			//TODO: implementar a leitura abstrata de campos efetuando busca com o metodo getColStartEndByAttribute da classe Layout
			for(int i =0;i < fieldsDam.length; i++){
				int colRange[] = layout.getColStartEndByAttribute(fieldsDam[i].getName());
				
				String className = fieldsDam[i].getType().getName();
				
				System.out.println(className);
				System.exit(0);
				
				switch (className) {
				case "":
					
					break;

				default:
					fieldsDam[i].set(fieldsDam[i], lin.substring(colRange[0], colRange[1]));
					break;
				}
				
				
				
				
			
			}
			
			
			
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
			
			numSeq++;
			
			this.dams.add(dam);
		}
		
	}
	
	

	

}
