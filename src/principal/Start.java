package principal;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Calendar;

import erro.ErrorLog;

public class Start {

	/**
	 * The main method.
	 *
	 * @param args the arguments: argument 0 = file name of the config; argument 1 = file name of the log;
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException {
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
				System.out.println(new ErrorLog(Calendar.getInstance(), e2).toString());
				//TODO: caso nao consiga slavar o arquivo padrão com erro... exibir mensagem grafica e parar a execucao.
			}
			
		}
		
	}

}
