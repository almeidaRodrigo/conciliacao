package vo;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class ConfigXml.
 * <p>Esta classe é responsável por instanciar 
 * um objeto que corresponde exatamente a todas as
 * configurações necessárias 
 * (lidas do arquivo de configuração, no diretorio do JAR)
 * e permite o acesso por meio dos getters as configurações. Inclui as configurações de layout do DAM.
 * </p> 
 * <p>@author Rodrigo S Almeida - almeida.rodrigosantana at gmail.com</p>
 * <p>desenvolvida em 14/06/2016</p>
 */
public class ConfigXml {

	/**
	 * @param userBd
	 * @param passBd
	 * @param pathDam15Recebido
	 * @param pathDam15Historico
	 * @param pathDamDefinitivoRecebido
	 * @param pathDamDefinitivoHistorico
	 * @param pathArquivoErro
	 * @param emailDestinoSuporte
	 * @param emailRemetenteSuporte
	 * @param hostNameEmail
	 * @param passHostEmail
	 * @param smtpPort
	 * @param ssl
	 * @param userNameEmail
	 * @param subjectEmail
	 * @param messageEmail
	 * @param intervaloMilisegundos
	 */
	public ConfigXml(String userBd, String passBd, String hostBd, String tipoBanco, int portaBanco, 
			String nomeServicoBanco, String pathDam15Recebido, String pathDam15Historico,
			String pathDamDefinitivoRecebido, String pathDamDefinitivoHistorico, String pathArquivoErro,
			ArrayList<String> emailDestinoSuporte, String emailRemetenteSuporte, String hostNameEmail,
			String passHostEmail, String smtpPort, Boolean ssl, String userNameEmail, String subjectEmail,
			String messageEmail, Long intervaloMilisegundos, String pathErrorLog) {
		
		this.layout = new Layout();
		
		this.userBd = userBd;
		this.passBd = passBd;
		this.hostBd = hostBd;
		this.tipoBanco = tipoBanco;
		this.portaBanco = portaBanco;
		this.nomeServicoBanco = nomeServicoBanco;
		this.setPathDam15Recebido(pathDam15Recebido);
		this.setPathDam15Historico(pathDam15Historico);
		this.setPathDamDefinitivoRecebido(pathDamDefinitivoRecebido);
		this.setPathDamDefinitivoHistorico(pathDamDefinitivoHistorico);
		this.setPathArquivoErro(pathArquivoErro);
		this.emailDestinoSuporte = emailDestinoSuporte;
		this.emailRemetenteSuporte = emailRemetenteSuporte;
		this.hostNameEmail = hostNameEmail;
		this.passHostEmail = passHostEmail;
		this.smtpPort = smtpPort;
		this.ssl = ssl;
		this.userNameEmail = userNameEmail;
		this.subjectEmail = subjectEmail;
		this.messageEmail = messageEmail;
		this.intervaloMilisegundos = intervaloMilisegundos;
		this.setPathErrorLog(pathErrorLog);
		
	}
	
	/** The layout Dam. */
	private Layout layout;

	/** The user bd. */
	private String userBd;
	
	/** The pass bd. */
	private String passBd;
	
	/** The host bd. */
	private String hostBd;
	
	/** The tipo banco. */
	private String tipoBanco;
	
	/** The porta banco. */
	private int portaBanco;
	
	/** The nome servico banco. */
	private String nomeServicoBanco;
	
	/** The path dam recebido. */
	private Path pathDam15Recebido;
	
	/** The path dam historico. */
	private Path pathDam15Historico;
	
	private Path pathDamDefinitivoRecebido;
	
	private Path pathDamDefinitivoHistorico;
	
	/** The nome banco convenio. */
	private Path pathArquivoErro;
	
	/** The email destino suporte. */
	private ArrayList<String> emailDestinoSuporte;
	
	/** The email remetente suporte. */
	private String emailRemetenteSuporte;
	
	/** The host name email. */
	private String hostNameEmail;
	
	/** The pass host email. */
	private String passHostEmail;
	
	/** The smtp port. */
	private String smtpPort;
	
	/** The ssl. */
	private Boolean ssl;
	
	/** The user name email. */
	private String userNameEmail;
	
	private String subjectEmail;
	
	private String messageEmail;
	
	private Long intervaloMilisegundos;
	
	private Path pathErrorLog;
		
	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @param layout the layoutDam to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	/**
	 * Gets the user bd.
	 *
	 * @return the user bd
	 */
	public String getUserBd() {
		return userBd;
	}
	
	/**
	 * Sets the user bd.
	 *
	 * @param userBd the new user bd
	 */
	public void setUserBd(String userBd) {
		this.userBd = userBd;
	}
	
	/**
	 * Gets the pass bd.
	 *
	 * @return the pass bd
	 */
	public String getPassBd() {
		return passBd;
	}
	
	/**
	 * Sets the pass bd.
	 *
	 * @param passBd the new pass bd
	 */
	public void setPassBd(String passBd) {
		this.passBd = passBd;
	}
	
	/**
	 * @return the hostBd
	 */
	public String getHostBd() {
		return hostBd;
	}

	/**
	 * @param hostBd the hostBd to set
	 */
	public void setHostBd(String hostBd) {
		this.hostBd = hostBd;
	}

	/**
	 * @return the tipoBanco
	 */
	public String getTipoBanco() {
		return tipoBanco;
	}

	/**
	 * @param tipoBanco the tipoBanco to set
	 */
	public void setTipoBanco(String tipoBanco) {
		this.tipoBanco = tipoBanco;
	}

	/**
	 * @return the portaBanco
	 */
	public int getPortaBanco() {
		return portaBanco;
	}

	/**
	 * @param portaBanco the portaBanco to set
	 */
	public void setPortaBanco(int portaBanco) {
		this.portaBanco = portaBanco;
	}

	/**
	 * @return the nomeServicoBanco
	 */
	public String getNomeServicoBanco() {
		return nomeServicoBanco;
	}

	/**
	 * @param nomeServicoBanco the nomeServicoBanco to set
	 */
	public void setNomeServicoBanco(String nomeServicoBanco) {
		this.nomeServicoBanco = nomeServicoBanco;
	}

	/**
	 * Gets the path dam recebido.
	 *
	 * @return the path dam recebido
	 */
	public Path getPathDam15Recebido() {
		return this.pathDam15Recebido;
	}
	
	/**
	 * Sets the path dam recebido.
	 *
	 * @param pathDamRecebido the new path dam recebido
	 */
	public void setPathDam15Recebido(String pathDamRecebido) {
		this.pathDam15Recebido = FileSystems.getDefault().getPath(pathDamRecebido);
	}
	
	/**
	 * Gets the path dam historico.
	 *
	 * @return the path dam historico
	 */
	public Path getPathDam15Historico() {
		return this.pathDam15Historico;
	}
	
	/**
	 * Sets the path dam historico.
	 * <p>Responsavél por setar um caminho RAIZ para a pasta de historico dos arquivos processados.</p>
	 * <p>Automaticamente será inserido um subdiretorio com o ano corrente da maquina.</p>
	 * @param pathDam15Historico the new path dam historico
	 */
	public void setPathDam15Historico(String pathDam15Historico) {
		this.pathDam15Historico =  FileSystems.getDefault().getPath(pathDam15Historico + File.separator + Calendar.getInstance().get(Calendar.YEAR)); 
	}
	
	/**
	 * @return the pathDamDefinitivoRecebido
	 */
	public Path getPathDamDefinitivoRecebido() {
		return this.pathDamDefinitivoRecebido;
	}

	/**
	 * @param pathDamDefinitivoRecebido the pathDamDefinitivoRecebido to set
	 */
	public void setPathDamDefinitivoRecebido(String pathDamDefinitivoRecebido) {
		this.pathDamDefinitivoRecebido =  FileSystems.getDefault().getPath(pathDamDefinitivoRecebido + File.separator + Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * @return the pathDamDefinitivoHistorico
	 */
	public Path getPathDamDefinitivoHistorico() {
		return this.pathDamDefinitivoHistorico;
	}

	/**
	 * @param pathDamDefinitivoHistorico the pathDamDefinitivoHistorico to set
	 */
	public void setPathDamDefinitivoHistorico(String pathDamDefinitivoHistorico) {
		this.pathDamDefinitivoHistorico =  FileSystems.getDefault().getPath(pathDamDefinitivoHistorico + File.separator + Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * Gets the nome banco convenio.
	 *
	 * @return the path Arquivo Erro
	 */
	public Path getPathArquivoErro() {
		return pathArquivoErro;
	}
	
	/**
	 * Sets the path Arquivo Erro.
	 *
	 * @param pathArquivoErro the new path Arquivo Erro
	 */
	public void setPathArquivoErro(String pathArquivoErro) {
		this.pathArquivoErro =  FileSystems.getDefault().getPath(pathArquivoErro + File.separator + Calendar.getInstance().get(Calendar.YEAR));
	}
	
	/**
	 * Gets the email destino suporte.
	 *
	 * @return the email destino suporte
	 */
	public ArrayList<String> getEmailDestinoSuporte() {
		return emailDestinoSuporte;
	}
	
	/**
	 * Sets the email destino suporte.
	 *
	 * @param emailDestinoSuporte the new email destino suporte
	 */
	public void setEmailDestinoSuporte(ArrayList<String> emailDestinoSuporte) {
		this.emailDestinoSuporte = emailDestinoSuporte;
	}
	
	/**
	 * Gets the email remetente suporte.
	 *
	 * @return the email remetente suporte
	 */
	public String getEmailRemetenteSuporte() {
		return emailRemetenteSuporte;
	}
	
	/**
	 * Sets the email remetente suporte.
	 *
	 * @param emailRemetenteSuporte the new email remetente suporte
	 */
	public void setEmailRemetenteSuporte(String emailRemetenteSuporte) {
		this.emailRemetenteSuporte = emailRemetenteSuporte;
	}
	
	/**
	 * Gets the host name email.
	 *
	 * @return the host name email
	 */
	public String getHostNameEmail() {
		return hostNameEmail;
	}
	
	/**
	 * Sets the host name email.
	 *
	 * @param hostNameEmail the new host name email
	 */
	public void setHostNameEmail(String hostNameEmail) {
		this.hostNameEmail = hostNameEmail;
	}
	
	/**
	 * Gets the pass host email.
	 *
	 * @return the pass host email
	 */
	public String getPassHostEmail() {
		return passHostEmail;
	}
	
	/**
	 * Sets the pass host email.
	 *
	 * @param passHostEmail the new pass host email
	 */
	public void setPassHostEmail(String passHostEmail) {
		this.passHostEmail = passHostEmail;
	}
	
	/**
	 * Gets the smtp port.
	 *
	 * @return the smtp port
	 */
	public String getSmtpPort() {
		return smtpPort;
	}
	
	/**
	 * Sets the smtp port.
	 *
	 * @param smtpPort the new smtp port
	 */
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	
	/**
	 * Gets the ssl.
	 *
	 * @return the ssl
	 */
	public Boolean getSsl() {
		return ssl;
	}
	
	/**
	 * Sets the ssl.
	 *
	 * @param ssl the new ssl
	 */
	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}
	
	/**
	 * Gets the user name email.
	 *
	 * @return the user name email
	 */
	public String getUserNameEmail() {
		return userNameEmail;
	}
	
	/**
	 * Sets the user name email.
	 *
	 * @param userNameEmail the new user name email
	 */
	public void setUserNameEmail(String userNameEmail) {
		this.userNameEmail = userNameEmail;
	}

	/**
	 * @return the subjectEmail
	 */
	public String getSubjectEmail() {
		return subjectEmail;
	}

	/**
	 * @param subjectEmail the subjectEmail to set
	 */
	public void setSubjectEmail(String subjectEmail) {
		this.subjectEmail = subjectEmail;
	}

	/**
	 * @return the messageEmail
	 */
	public String getMessageEmail() {
		return messageEmail;
	}

	/**
	 * @param messageEmail the messageEmail to set
	 */
	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}

	/**
	 * @return the intervaloMinutos
	 */
	public Long getIntervaloMilisegundos() {
		return intervaloMilisegundos;
	}

	/**
	 * @param intervaloMilisegundos the intervaloMinutos to set
	 */
	public void setIntervaloMilisegundos(Long intervaloMilisegundos) {
		this.intervaloMilisegundos = intervaloMilisegundos;
	}

	/**
	 * @return the errorLogPath
	 */
	public Path getPathErrorLog() {
		return pathErrorLog;
	}

	/**
	 * @param pathErrorLog the errorLogPath to set
	 */
	public void setPathErrorLog(String pathErrorLog) {
		this.pathErrorLog = FileSystems.getDefault().getPath(pathErrorLog);
	}
	
	
	

}
