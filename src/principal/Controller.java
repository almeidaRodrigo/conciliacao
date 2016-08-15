package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.Calendar;

import arquivo.ConciliacaoFiles;
import arquivo.Log;
import arquivo.ManipulateXml;
import arquivo.RegressFile;
import conexao.ObterConexao;
import dao.LoteDao;
import email.Mail;
import erro.ErrorLog;
import vo.ConfigXml;
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
			System.out.println("Inciciando tempo de espera pré definido...");
			Thread.sleep(this.configXml.getIntervaloMilisegundos());
			System.out.println("...tempo de espera pré definido finalizado!");
			this.run();
			System.out.println("Finalizado laço de repetição! Aplicação entrando em modo espera...");
		}
	}

	private void run() throws Exception {
		System.out.println("Iniciando processamento de arquivos...");

		Connection conn = null;
		LoteDao loteDao = null;
		
		//Arquivos dentro dos diretorios definidos no ConfigXml para DAMs de 15 em 15 e Definitivos;
		File[] listFiles15, listFilesDef;
		listFiles15 = new File(this.getConfigXml().getPathDam15Recebido().toUri()).listFiles();
		listFilesDef = new File(this.getConfigXml().getPathDamDefinitivoRecebido().toUri()).listFiles();
		
		//Preenchendo a lista com arquivo de retorno com DAMs do tipo 15 em 15.
		if(listFiles15.length > 0){
			//Obtem uma conexao para utilização em todos os objetos de DAO;
			conn = ObterConexao.connect(this.getConfigXml());
			//Instancia os objetos para operação de acesso a dados e manipulação
			loteDao = new LoteDao(conn);
			
			for (File sPath : listFiles15) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());

				try {
					//Arquivo de retorno do tipo DAM de 15 em 15 (chamado de confirmação parcial);
					RegressFile regressFile15 = new RegressFile(path, this.getConfigXml(), TipoDamEnum.PARCIAL);

					try {
						//Inserir o lote na base de dados;
						loteDao.insertLote(regressFile15, TipoDamEnum.PARCIAL);

						//Mover arquivo para pasta Historico conforme ConfigXml;
						if(!regressFile15.moveFile(regressFile15, 
								new ConciliacaoFiles(regressFile15.getConfigXml().getPathDam15Historico()+File.separator+regressFile15.getName()))){
							throw new Exception("O arquivo de retorno não pode ser movido para pasta Historico.");
						}
					} catch (Exception e) {
						if(!regressFile15.moveFile(regressFile15, 
								new ConciliacaoFiles(regressFile15.getConfigXml().getPathArquivoErro()+File.separator+regressFile15.getName()))){
							throw new Exception("Erro critico de sistema: O arquivo de retorno não pode ser movido para pasta de ERRO. "+e.getMessage());
						}
						
						new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
						new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();
						
					}

				} catch (Exception e) {
					new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
					new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();
					this.moverArquivoComErro(path, e);
				}
			}
		}
		
		if(listFilesDef.length > 0){
			//Obtem uma conexao para utilização em todos os objetos de DAO;
			conn = ObterConexao.connect(this.getConfigXml());
			//Instancia os objetos para operação de acesso a dados e manipulação
			loteDao = new LoteDao(conn);
			
			for (File sPath : listFilesDef) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());
				
				try {
					//Arquivo de retorno do tipo DAM definitivo (chamado de confirmação definitiva)
					RegressFile regressFileDef = new RegressFile(path, this.getConfigXml(), TipoDamEnum.DEFINITIVO);

					try {
						//Inserir o lote e DAMs na base de dados;
						loteDao.insertLote(regressFileDef, TipoDamEnum.DEFINITIVO);
						
						//Mover arquivo para pasta Historico conforme ConfigXml;
						if(!regressFileDef.moveFile(regressFileDef, 
								new ConciliacaoFiles(regressFileDef.getConfigXml().getPathDamDefinitivoHistorico()+File.separator+regressFileDef.getName()))){
							throw new Exception("O arquivo de retorno não pode ser movido para pasta Historico Definitivo.");
						}
					} catch (Exception e) {
						if(!regressFileDef.moveFile(regressFileDef, 
								new ConciliacaoFiles(regressFileDef.getConfigXml().getPathArquivoErro()+File.separator+regressFileDef.getName()))){
							throw new Exception("Erro critico de sistema: O arquivo de retorno não pode ser movido para pasta de ERRO. "+e.getMessage());
						}
						
						new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
						new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();
						
					}

				} catch (Exception e) {
					new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
					new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();
					this.moverArquivoComErro(path, e);
				}
				
			}
		}
		
		System.out.println("...Fim do processamento dos arquivos.");
		
		//Realiza-se o fechamento da conexão, caso esteja aberta;
		if(conn != null){
			if(!conn.isClosed())
				conn.close();
		}
		

	}
	
	private void moverArquivoComErro(Path path, Exception e) throws Exception{
		ConciliacaoFiles regressFile = new ConciliacaoFiles(path);
		
		if(!regressFile.getParentFile().exists()){
			regressFile.getParentFile().mkdirs();
		}

		if(!regressFile.moveFile(regressFile, new ConciliacaoFiles(this.configXml.getPathArquivoErro()+File.separator+regressFile.getName()))){
			throw new Exception("Erro critico de sistema: O arquivo de retorno não pode ser movido para pasta de ERRO no momento da instanciação, verifique permissões no diretorio definido. "+e.getMessage());
		}
				
		new Mail(this.getConfigXml(), new ErrorLog(Calendar.getInstance(), new Exception("O arquivo de retorno pode não ser válido. "+ e.getMessage()))).sendMail();
		
	}
	
	
}
