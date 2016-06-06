package arquivo;

import java.util.Calendar;

import erro.ErrorLog;

public class Log extends ConciliacaoFiles {

	public Log(ReaderConfigs readerConfigs) {
		super(readerConfigs.getConfig("dirLog"));
	}

	public void MakeLog(Exception exception) {
		ErrorLog erroLog = new ErrorLog(Calendar.getInstance(), exception);
		this.saveFile(this.openFile(this.getPath()), erroLog.toString());
		
		

	}

}
