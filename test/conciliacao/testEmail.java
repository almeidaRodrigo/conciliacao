package conciliacao;

import java.util.Calendar;

import arquivo.ManipulateXml;
import email.Mail;
import erro.ErrorLog;
import vo.ConfigXml;

public class testEmail {

	public static void main(String[] args) {
		
		ErrorLog errorLog = new ErrorLog(Calendar.getInstance(), new Exception("Erro de teste"));
		ConfigXml configXml;
		Mail email;
		
		try {
			configXml = new ManipulateXml().openXml();
			
			email = new Mail(configXml, errorLog);
			email.sendMail();
			
			System.out.println("Email enviado!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		
		

	}

}
