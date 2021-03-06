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
	 * @param configXml
	 *            the configXml to set
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

	public Controller(Path configPath, Path logPath) throws Exception {
		this.setConfigXml((ConfigXml) new ManipulateXml(configPath).openXml());
		this.setLog(new Log(this.configXml.getPathErrorLog()));

		while (true) {
			//Inicializa processar arquivo
			this.run();
			
			//Entra em modo de espera pelo tempo determinado no Config.xml
			Thread.sleep(this.configXml.getIntervaloMilisegundos());

		}
	}

	private void run() throws Exception {
		// System.out.println("Iniciando processamento de arquivos...");
		Connection conn = null;

		// Arquivos dentro dos diretorios definidos no ConfigXml para DAMs de 15
		// em 15 e Definitivos;
		File[] listFiles15, listFilesDef;
		listFiles15 = new File(this.getConfigXml().getPathDam15Recebido().toUri()).listFiles();
		listFilesDef = new File(this.getConfigXml().getPathDamDefinitivoRecebido().toUri()).listFiles();

		// Preenchendo a lista com arquivo de retorno com DAMs do tipo 15 em 15.
		if (listFiles15 != null)
			if (listFiles15.length > 0) {
				// Obtem uma conexao para utiliza��o em todos os objetos de DAO;
				conn = ObterConexao.connect(this.getConfigXml());

				// Objeto para opera��o de acesso a dados e manipula��o
				this.procArquivo(new LoteDao(conn), listFiles15, TipoDamEnum.PARCIAL);
				listFiles15 = null;

			}

		// Preenchendo a lista com arquivo de retorno com DAMs do tipo
		// definitivo.
		if (listFilesDef != null)
			if (listFilesDef.length > 0) {
				// Obtem uma conexao para utiliza��o em todos os objetos de DAO;
				conn = ObterConexao.connect(this.getConfigXml());

				// Objeto para opera��o de acesso a dados e manipula��o
				this.procArquivo(new LoteDao(conn), listFilesDef, TipoDamEnum.DEFINITIVO);
				listFilesDef = null;

			}

		// System.out.println("...Fim do processamento dos arquivos.");

		// Realiza-se o fechamento da conex�o, caso esteja aberta;
		if (conn != null) {
			if (!conn.isClosed())
				conn.close();
		}

	}

	private void procArquivo(LoteDao loteDao, File[] listFiles, TipoDamEnum tipoDam) throws Exception {
		Path path = null;
		Path pathHistorico = null;
		RegressFile regressFile = null;
		ConciliacaoFiles fileMoved = null;
		ConciliacaoFiles fileError = null;

		for (File fPath : listFiles) {
			if (fPath.isFile()) {
				path = FileSystems.getDefault().getPath(fPath.getPath());

				try {
					// Arquivo de retorno
					regressFile = new RegressFile(path, this.getConfigXml(), tipoDam);

					// Arquivo que ser� movido para pasta de erro
					fileError = new ConciliacaoFiles(
							regressFile.getConfigXml().getPathArquivoErro() + File.separator + regressFile.getName());

					try {
						// Inserir o lote na base de dados;
						loteDao.insertLote(regressFile, tipoDam);

						if (tipoDam.equals(TipoDamEnum.PARCIAL)) {
							pathHistorico = regressFile.getConfigXml().getPathDam15Historico();

							// Arquivo que ser� movido para pasta historico
							fileMoved = new ConciliacaoFiles(pathHistorico + File.separator + regressFile.getName());
						} else if (tipoDam.equals(TipoDamEnum.DEFINITIVO)) {
							pathHistorico = regressFile.getConfigXml().getPathDamDefinitivoHistorico();

							// Arquivo que ser� movido para pasta historicoD2
							// com a particularidade de formata��o de nome.
							fileMoved = new ConciliacaoFiles(pathHistorico + File.separator
									+ String.format("%1$tY%1$tm%1$td", Calendar.getInstance().getTime())
									+ String.format("%06d", regressFile.getLote().getNumeroLote()) + ".txt");
						} else {
							throw new Exception(
									"Erro gerado no Controller: N�o foi possivel determinar o tipo de DAM.");
						}

						// Mover arquivo para pasta Historico conforme ConfigXml
						// e TipoDam;
						if (!regressFile.moveFile(regressFile, fileMoved)) {
							throw new Exception("O arquivo de retorno n�o pode ser movido para pasta Historico.");
						}
					} catch (Exception e) {
						if (!regressFile.moveFile(regressFile, fileError)) {
							throw new Exception(
									"Erro critico de sistema: O arquivo de retorno n�o pode ser movido para pasta de ERRO. "
											+ e.getMessage());
						}

						new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
						new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();

					} finally {
						pathHistorico = null;
						fileMoved = null;
					}

				} catch (Exception e) {
					new Log(this.getConfigXml().getPathErrorLog()).makeLog(e);
					new Mail(this.configXml, new ErrorLog(Calendar.getInstance(), e)).sendMail();
					this.moverArquivoComErro(path, e);

				} finally {
					regressFile = null;
					fileError = null;
					path = null;

				}

			}
		}
	}

	private void moverArquivoComErro(Path path, Exception e) throws Exception {
		ConciliacaoFiles regressFile = new ConciliacaoFiles(path);
		ConciliacaoFiles fileError = new ConciliacaoFiles(
				this.configXml.getPathArquivoErro() + File.separator + regressFile.getName());

		if (!regressFile.getParentFile().exists()) {
			regressFile.getParentFile().mkdirs();
		}

		if (!regressFile.moveFile(regressFile, fileError)) {
			throw new Exception(
					"Erro critico de sistema: O arquivo de retorno n�o pode ser movido para pasta de ERRO no momento da instancia��o, verifique permiss�es no diretorio definido. "
							+ e.getMessage());
		}

		new Mail(this.getConfigXml(), new ErrorLog(Calendar.getInstance(),
				new Exception("O arquivo de retorno pode n�o ser v�lido. " + e.getMessage()))).sendMail();

		regressFile = null;
		fileError = null;

	}

}
