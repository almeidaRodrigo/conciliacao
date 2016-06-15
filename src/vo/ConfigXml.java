package vo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class ConfigXml.
 * <p>Esta classe � respons�vel por instanciar 
 * um objeto que corresponde exatamente a todas as
 * configura��es necess�rias 
 * (lidas do arquivo de configura��o, no diretorio do JAR)
 * e permite o acesso por meio dos getters as configura��es.
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
	 * @param nomeBancoConvenio
	 * @param emailDestinoSuporte
	 * @param emailRemetenteSuporte
	 * @param hostNameEmail
	 * @param passHostEmail
	 * @param smtpPort
	 * @param ssl
	 * @param userNameEmail
	 * @param intervaloMinutos
	 */
	public ConfigXml(String userBd, String passBd, String pathDam15Recebido, String pathDam15Historico,
			String pathDamDefinitivoRecebido, String pathDamDefinitivoHistorico, String nomeBancoConvenio,
			ArrayList<String> emailDestinoSuporte, String emailRemetenteSuporte, String hostNameEmail,
			String passHostEmail, String smtpPort, Boolean ssl, String userNameEmail, int intervaloMinutos) {
		this.userBd = userBd;
		this.passBd = passBd;
		this.pathDam15Recebido = pathDam15Recebido;
		this.pathDam15Historico = pathDam15Historico;
		this.pathDamDefinitivoRecebido = pathDamDefinitivoRecebido;
		this.pathDamDefinitivoHistorico = pathDamDefinitivoHistorico;
		this.nomeBancoConvenio = nomeBancoConvenio;
		this.emailDestinoSuporte = emailDestinoSuporte;
		this.emailRemetenteSuporte = emailRemetenteSuporte;
		this.hostNameEmail = hostNameEmail;
		this.passHostEmail = passHostEmail;
		this.smtpPort = smtpPort;
		this.ssl = ssl;
		this.userNameEmail = userNameEmail;
		this.intervaloMinutos = intervaloMinutos;
	}

	/** The user bd. */
	private String userBd;
	
	/** The pass bd. */
	private String passBd;
	
	/** The path dam recebido. */
	private String pathDam15Recebido;
	
	/** The path dam historico. */
	private String pathDam15Historico;
	
	private String pathDamDefinitivoRecebido;
	
	private String pathDamDefinitivoHistorico;
	
	/** The nome banco convenio. */
	private String nomeBancoConvenio;
	
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
	
	private int intervaloMinutos;
	
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
	 * Gets the path dam recebido.
	 *
	 * @return the path dam recebido
	 */
	public String getPathDam15Recebido() {
		return pathDam15Recebido;
	}
	
	/**
	 * Sets the path dam recebido.
	 *
	 * @param pathDamRecebido the new path dam recebido
	 */
	public void setPathDam15Recebido(String pathDamRecebido) {
		this.pathDam15Recebido = pathDamRecebido;
	}
	
	/**
	 * Gets the path dam historico.
	 *
	 * @return the path dam historico
	 */
	public String getPathDam15Historico() {
		return pathDam15Historico;
	}
	
	/**
	 * Sets the path dam historico.
	 * <p>Responsav�l por setar um caminho RAIZ para a pasta de historico dos arquivos processados.</p>
	 * <p>Automaticamente ser� inserido um subdiretorio com o ano corrente da maquina.</p>
	 * @param pathDam15Historico the new path dam historico
	 */
	public void setPathDam15Historico(String pathDam15Historico) {
		this.pathDam15Historico = pathDam15Historico + File.separator + Calendar.getInstance().get(Calendar.YEAR)+ File.separator;
	}
	
	/**
	 * @return the pathDamDefinitivoRecebido
	 */
	public String getPathDamDefinitivoRecebido() {
		return pathDamDefinitivoRecebido;
	}

	/**
	 * @param pathDamDefinitivoRecebido the pathDamDefinitivoRecebido to set
	 */
	public void setPathDamDefinitivoRecebido(String pathDamDefinitivoRecebido) {
		this.pathDamDefinitivoRecebido = pathDamDefinitivoRecebido + File.separator + Calendar.getInstance().get(Calendar.YEAR)+ File.separator;
	}

	/**
	 * @return the pathDamDefinitivoHistorico
	 */
	public String getPathDamDefinitivoHistorico() {
		return pathDamDefinitivoHistorico;
	}

	/**
	 * @param pathDamDefinitivoHistorico the pathDamDefinitivoHistorico to set
	 */
	public void setPathDamDefinitivoHistorico(String pathDamDefinitivoHistorico) {
		this.pathDamDefinitivoHistorico = pathDamDefinitivoHistorico;
	}

	/**
	 * Gets the nome banco convenio.
	 *
	 * @return the nome banco convenio
	 */
	public String getNomeBancoConvenio() {
		return nomeBancoConvenio;
	}
	
	/**
	 * Sets the nome banco convenio.
	 *
	 * @param nomeBancoConvenio the new nome banco convenio
	 */
	public void setNomeBancoConvenio(String nomeBancoConvenio) {
		this.nomeBancoConvenio = nomeBancoConvenio;
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
	 * @return the intervaloMinutos
	 */
	public int getIntervaloMinutos() {
		return intervaloMinutos;
	}

	/**
	 * @param intervaloMinutos the intervaloMinutos to set
	 */
	public void setIntervaloMinutos(int intervaloMinutos) {
		this.intervaloMinutos = intervaloMinutos;
	}
	
	
	

}