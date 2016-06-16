package email;

import vo.ConfigXml;

public class AccessMail {

	/**
	 * @param hostName
	 * @param smtpPort
	 * @param ssl
	 * @param user
	 * @param pass
	 */
	public AccessMail(ConfigXml configXml) {
		this.hostName = configXml.getHostNameEmail();
		this.smtpPort = Integer.parseInt(configXml.getSmtpPort());
		this.ssl = configXml.getSsl();
		this.user = configXml.getUserNameEmail();
		this.pass = configXml.getPassHostEmail();
	}

	private String hostName;

	private int smtpPort;

	private Boolean ssl;

	private String user;

	private String pass;

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the smtpPort
	 */
	public int getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort the smtpPort to set
	 */
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return the ssl
	 */
	public Boolean getSsl() {
		return ssl;
	}

	/**
	 * @param ssl the ssl to set
	 */
	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	

	
}
