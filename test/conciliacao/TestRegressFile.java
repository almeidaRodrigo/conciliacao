package conciliacao;

import java.io.File;
import java.nio.file.FileSystems;
import arquivo.ManipulateXml;
import arquivo.RegressFile;
import vo.ConfigXml;

public class TestRegressFile {

	public static void main(String[] args) {
		ConfigXml configXml;
		try {
			configXml = (ConfigXml) new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).openXml();
			RegressFile rf = new RegressFile(configXml.getPathDam15Recebido(), configXml);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
