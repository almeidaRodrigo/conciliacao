package principal;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import arquivo.Log;
import arquivo.ManipulateXml;
import vo.ConfigXml;

public class Controller {
	
	private Path path;
	private ConfigXml configXml;
	private Log log;

	/**
	 * @return the configXml
	 */
	public ConfigXml getConfigXml() {
		return configXml;
	}

	/**
	 * @param configXml the configXml to set
	 */
	public void setConfigXml(ConfigXml configXml) {
		this.configXml = configXml;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Controller(Path configPath, Path logPath) throws FileNotFoundException, InterruptedException  {
		this.setConfigXml((ConfigXml) new ManipulateXml(configPath).openXml());
		this.setLog(new Log(this.configXml.getPathErrorLog()));
		
		while(true){
			System.out.println("Inciciando aguarde...");
			Thread.sleep(this.configXml.getIntervaloMilisegundos());
			System.out.println("...'aguarde' finalizado!");
			this.run();
		}
	}

	private void run() {
		//TODO: Regra de instanciação de DAM, tratamento / validação, processamento, Inserção em Banco de dados e remoção do arquivo para pasta de historico.
		System.out.println("Entrou");
		System.exit(0);
	}

}
