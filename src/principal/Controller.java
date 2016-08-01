package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import arquivo.Log;
import arquivo.ManipulateXml;
import arquivo.RegressFile;
import conexao.ObterConexao;
import dao.DamDao;
import dao.LoteDao;
import vo.ConfigXml;
import vo.Lote;
import vo.TipoDamEnum;

public class Controller {
	
	private ConfigXml configXml;
	private Log log;

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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Controller(Path configPath, Path logPath) throws Exception  {
		this.setConfigXml((ConfigXml) new ManipulateXml(configPath).openXml());
		this.setLog(new Log(this.configXml.getPathErrorLog()));
		
		while(true){
			System.out.println("Inciciando aguarde...");
			Thread.sleep(this.configXml.getIntervaloMilisegundos());
			System.out.println("...'aguarde' finalizado!");
			this.run();
			System.out.println("Tudo OK!");
		}
	}

	private void run() throws Exception {
		System.out.println("Entrou RUN");
		
		//Lista de arquivos de retorno para armazenar arquivos de 15 em 15 e definitivo;
		ArrayList<RegressFile> arquivoRet15 = new ArrayList<>();
		ArrayList<RegressFile> arquivoRetDef = new ArrayList<>();
		
		//Instancia os objetos para operação de acesso a dados e manipulação
		LoteDao loteDao = new LoteDao(ObterConexao.connect(this.getConfigXml()));
		DamDao damDao = new DamDao(ObterConexao.connect(this.getConfigXml()));
		
		//Arquivos dentro dos diretorios definidos no ConfigXml para DAMs de 15 em 15 e Definitivos;
		File[] listFiles15, listFilesDef;
		listFiles15 = new File(this.getConfigXml().getPathDam15Recebido().toUri()).listFiles();
		listFilesDef = new File(this.getConfigXml().getPathDamDefinitivoRecebido().toUri()).listFiles();
		
		//Preenchendo a lista com arquivo de retorno com DAMs do tipo 15 em 15.
		if(listFiles15 != null){
			for (File sPath : listFiles15) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());
				
				//Arquivo de retorno do tipo DAM de 15 em 15 (chamado de confirmação parcial);
				arquivoRet15.add(new RegressFile(path, this.getConfigXml(), TipoDamEnum.PARCIAL));
			}
		}
		
		if(listFilesDef != null){
			for (File sPath : listFilesDef) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());
				
				//Arquivo de retorno do tipo DAM definitivo (chamado de confirmação definitiva)
				arquivoRetDef.add(new RegressFile(path, this.getConfigXml(), TipoDamEnum.DEFINITIVO));
			}
		}
		
		//Se existir algum arquivo de retorno do tipo 15 em 15
		if(!arquivoRet15.isEmpty()){
			//Para cada arquivo retorno 15/15 encontrado
			for (RegressFile regressFile15 : arquivoRet15) {
				
				//Obtendo o lote do arquivo retorno;
				Lote lote = regressFile15.getLote(); 

				//Inserir o lote na base de dados;
				loteDao.insertLote(lote, TipoDamEnum.PARCIAL);
				
				//Inserir a lista de DAMs na base de dados;
				damDao.insertDam(regressFile15.getDams(), TipoDamEnum.PARCIAL);
			}
			//TODO: Mover arquivo para pasta Historico conforme ConfigXml;
			
			
		}
		
		if(!arquivoRetDef.isEmpty()){
			//Para cada arquivo retorno definitivo encontrado
			for (RegressFile regressFileDef : arquivoRetDef) {
				//Obtendo o lote do arquivo retorno;
				Lote lote = regressFileDef.getLote();
				
				//Inserir o lote na base de dados;
				loteDao.insertLote(lote, TipoDamEnum.DEFINITIVO);
				
				//Inserir a lista de DAMs na base de dados;
				damDao.insertDam(regressFileDef.getDams(), TipoDamEnum.DEFINITIVO);
			}
			//TODO: Mover arquivo para pasta Historico conforme ConfigXml;
			
		}
		
		
	}

}
