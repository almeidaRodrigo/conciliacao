package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import arquivo.ConciliacaoFiles;
import arquivo.Log;
import arquivo.ManipulateXml;
import arquivo.RegressFile;
import conexao.ObterConexao;
import dao.DamDao;
import dao.LoteDao;
import email.Mail;
import erro.ErrorLog;
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
			System.out.println("Inciciando tempo de espera pré definido...");
			Thread.sleep(this.configXml.getIntervaloMilisegundos());
			System.out.println("...tempo de espera pré definido finalizado!");
			this.run();
			System.out.println("Finalizado laço de repetição! Aplicação entrando em modo espera...");
		}
	}

	private void run() throws Exception {
		System.out.println("Iniciando processamento de arquivos...");
		int i = 1;
		
		//Lista de arquivos de retorno para armazenar arquivos de 15 em 15 e definitivo;
		ArrayList<RegressFile> arquivoRet15 = new ArrayList<>();
		ArrayList<RegressFile> arquivoRetDef = new ArrayList<>();
		
		//Obtem uma conexao para utilização em todos os objetos de DAO;
		Connection conn = ObterConexao.connect(this.getConfigXml());
		
		//Instancia os objetos para operação de acesso a dados e manipulação
		LoteDao loteDao = new LoteDao(conn);
		
		//Arquivos dentro dos diretorios definidos no ConfigXml para DAMs de 15 em 15 e Definitivos;
		File[] listFiles15, listFilesDef;
		listFiles15 = new File(this.getConfigXml().getPathDam15Recebido().toUri()).listFiles();
		listFilesDef = new File(this.getConfigXml().getPathDamDefinitivoRecebido().toUri()).listFiles();
		
		//Preenchendo a lista com arquivo de retorno com DAMs do tipo 15 em 15.
		if(listFiles15 != null){
			for (File sPath : listFiles15) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());

				try {
					//Arquivo de retorno do tipo DAM de 15 em 15 (chamado de confirmação parcial);
					arquivoRet15.add(new RegressFile(path, this.getConfigXml(), TipoDamEnum.PARCIAL));
				} catch (Exception e) {
					this.moverArquivoComErro(path, e);
				}
			}
		}
		
		if(listFilesDef != null){
			for (File sPath : listFilesDef) {
				Path path = FileSystems.getDefault().getPath(sPath.getPath());
				
				try {
					//Arquivo de retorno do tipo DAM definitivo (chamado de confirmação definitiva)
					arquivoRetDef.add(new RegressFile(path, this.getConfigXml(), TipoDamEnum.DEFINITIVO));
				} catch (Exception e) {
					this.moverArquivoComErro(path, e);
				}
				
			}
		}
		
		//Se existir algum arquivo de retorno do tipo 15 em 15
		if(!arquivoRet15.isEmpty()){
			//Para cada arquivo retorno 15/15 encontrado
			for (RegressFile regressFile15 : arquivoRet15) {
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

				System.out.println(i+ " arquivo(s) processados");
				i++;
			}

		}
		
		if(!arquivoRetDef.isEmpty()){
			//Para cada arquivo retorno definitivo encontrado
			for (RegressFile regressFileDef : arquivoRetDef) {
				try {
					//Inserir o lote e DAMs na base de dados;
					loteDao.insertLote(regressFileDef, TipoDamEnum.DEFINITIVO);
					
					//Mover arquivo para pasta Historico conforme ConfigXml;
					if(!regressFileDef.moveFile(regressFileDef, 
							new ConciliacaoFiles(regressFileDef.getConfigXml().getPathDamDefinitivoHistorico()))){
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
			}
		}
		
		if(!conn.isClosed())
			conn.close();
		
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
