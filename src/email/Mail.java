package email;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.*;
import erro.ErrorLog;
import vo.ConfigXml;

public class Mail {

	/**
	 * @param configXml
	 */
	public Mail(ConfigXml configXml, ErrorLog errorLog) {

		this.subject = configXml.getSubjectEmail();
		this.message = configXml.getMessageEmail();
		this.contactTo = this.loadListContactTo(configXml);
		this.contactFrom = this.loadContactFrom(configXml);
		this.access = new AccessMail(configXml);
		this.errorLog = errorLog;
		
		this.buildMail();
	}

	private String subject;

	private String message;

	private List<Contact> contactTo;

	private Contact contactFrom;

	private AccessMail access;

	private ErrorLog errorLog;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Contact> getContactTo() {
		return contactTo;
	}

	public void setContactTo(List<Contact> contactTo) {
		this.contactTo = contactTo;
	}

	public Contact getContactFrom() {
		return contactFrom;
	}

	public void setContactFrom(Contact contactFrom) {
		this.contactFrom = contactFrom;
	}

	public AccessMail getAccess() {
		return access;
	}

	public void setAccess(AccessMail access) {
		this.access = access;
	}

	public ErrorLog getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(ErrorLog errorLog) {
		this.errorLog = errorLog;
	}

	private ArrayList<Contact> loadListContactTo(ConfigXml configXml) {
		ArrayList<Contact> contacts = new ArrayList<>();
		ArrayList<String> emailStrings = configXml.getEmailDestinoSuporte();
		
		for (String emailsString : emailStrings) {
			contacts.add(new Contact(emailsString));
		}
		
		return contacts;
	}
	
	private Contact loadContactFrom(ConfigXml configXml){
		return new Contact(configXml.getEmailRemetenteSuporte());
	}

	private void buildMail() {
		this.message += this.errorLog.toString();
		
	}

	public void sendMail() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(this.getAccess().getHostName());
		email.setSmtpPort(this.getAccess().getSmtpPort());
		email.setSSLOnConnect(this.getAccess().getSsl());
		email.setAuthentication(this.getAccess().getUser(), this.access.getPass());
		email.setSSLOnConnect(this.getAccess().getSsl());
		email.setFrom(this.getContactFrom().getEmail());
		email.setSubject(this.getSubject());
		email.setMsg(this.getMessage());
		
		for (Contact contact : contactTo) {
			email.addTo(contact.getEmail());
		}
		
		email.send();

	}

}
