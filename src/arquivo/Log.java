package arquivo;

import java.nio.file.Path;
import java.util.Calendar;

import erro.ErrorLog;

public class Log extends ConciliacaoFiles {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Log(Path path) {
		super(path);
	}

	public void makeLog(Exception exception) throws Exception {
		ErrorLog erroLog = new ErrorLog(Calendar.getInstance(), exception);
		this.saveFile(erroLog.toString(), true);
	}

}
