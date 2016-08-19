package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailException;
import email.Mail;
import erro.ErrorLog;

public class Start {

	/**
	 * The main method.
	 *
	 * @param args the arguments: argument 0 = file name of the config; argument 1 = file name of the log;
	 * @throws EmailException 
	 */
	public static void main(String[] args){
		//TODO: efetuar teste para /? ou ? e exibir uma ajuda
		
		try {
			Path configPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, args[0]);
			Path logPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, args[1]);
			new Controller(configPath, logPath);
		} catch (Exception e1) {
			try {
				Path configPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml");
				Path logPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "logError.txt");
				new Controller(configPath, logPath);
			} catch (Exception e2) {
				String erro = e2.getMessage();

				try {
					new Mail(new ErrorLog(Calendar.getInstance(), e2)).sendMail();
				} catch (EmailException e3) {
					erro += " | " + e3.getMessage();
				}
				
				JOptionPane.showMessageDialog(null, "Erro crítico de sistema: " + erro, "CONCILIAÇÃO - ERRO CRITICO:", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
	}

}
