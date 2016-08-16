package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Calendar;
import org.apache.commons.mail.EmailException;
import email.Mail;
import erro.ErrorLog;

public class Start {

	/**
	 * The main method.
	 *
	 * @param args the arguments: argument 0 = file name of the config; argument 1 = file name of the log;
	 * @throws InterruptedException the interrupted exception
	 * @throws EmailException 
	 */
	public static void main(String[] args) throws InterruptedException, EmailException {
		//TODO: efetuar teste para /? ou ? e exibir uma ajuda
		
		try {
			Path configPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, args[0]);
			Path logPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, args[1]);
			new Controller(configPath, logPath);
		} catch (Exception e) {
			try {
				Path configPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml");
				Path logPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "logError.txt");
				new Controller(configPath, logPath);
			} catch (Exception e2) {
				e2.printStackTrace();
				new Mail(new ErrorLog(Calendar.getInstance(), e2)).sendMail();

				//TODO: caso nao consiga slavar o arquivo padrão com erro... exibir mensagem grafica e parar a execucao.
			}
			
		}
		
	}

}
