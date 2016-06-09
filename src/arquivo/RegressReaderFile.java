package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

import vo.Dam;

public class RegressReaderFile extends ConciliacaoFiles {

	private String header;

	private ArrayList<Dam> dams = new ArrayList<>();

	private String footer;

	public RegressReaderFile(ReaderConfigs readerConfigs) throws Exception {
		super(readerConfigs.getConfig("arquivoRetorno"));
		this.populate();
	
	}
	
	private void populate() throws Exception{
		String linha;
		int numSeq = 2;//Inicia em 2, pois, o sequencial de boletos despreza a linha 1 (que se refere ao cabeçalho - Header)
		ArrayList<String> linhas = new ArrayList<>();
		FileReader arq = this.openFile(this.getPath());
		BufferedReader lerArquivo = new BufferedReader(arq);
		
		linha = lerArquivo.readLine();

		if(linha != null){
			this.header = linha;
			
			while (linha != null) {
				linha = lerArquivo.readLine();
				linhas.add(linha);
			}
		}else{
			NullPointerException ex = new NullPointerException("O arquivo de retorno está vazio ou não foi reconhecido. Por favor verifique o arquivo!");
			throw ex;
		}
		
		this.footer = linhas.get(linhas.lastIndexOf(linhas));
		
		linhas.remove(linhas.lastIndexOf(linhas));
		
		for (String lin : linhas) {
			Calendar dataArrecadacao = Calendar.getInstance();
			Calendar dataCredito = Calendar.getInstance();	

			dataArrecadacao.set(Calendar.YEAR, Integer.parseInt(lin.substring(21, 24)));
			dataArrecadacao.set(Calendar.MONTH, Integer.parseInt(lin.substring(25, 26)));
			dataArrecadacao.set(Calendar.DAY_OF_MONTH, Integer.parseInt(lin.substring(27, 28)));
						
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
