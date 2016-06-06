package principal;

import arquivo.Log;
import arquivo.ReaderConfigs;

public class Controller {

	private ReaderConfigs readerConfigs;
	private Log log;

	public ReaderConfigs getReaderConfigs() {
		return readerConfigs;
	}

	public void setReaderConfigs(ReaderConfigs readerConfigs) {
		this.readerConfigs = readerConfigs;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Controller() throws InterruptedException {
		this.setReaderConfigs(new ReaderConfigs());
		this.setLog(new Log(this.getReaderConfigs()));
		
		while(true){
			try {
				Thread.sleep(Long.parseLong(getReaderConfigs().getConfig("tempoEspera")));
				this.run();
			} catch (NumberFormatException e) {
				this.log.MakeLog(e);
			} catch (InterruptedException e) {
				this.log.MakeLog(e);
			}finally {
				Thread.sleep(7200000);
			}
		}

	}

	private void run() {

	}

}
