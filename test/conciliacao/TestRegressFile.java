package conciliacao;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import arquivo.ManipulateXml;
import arquivo.RegressFile;
import vo.ConfigXml;

public class TestRegressFile {

	public static void main(String[] args) {
		ConfigXml configXml;

		try {
			configXml = (ConfigXml) new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).openXml();
			File[] listFiles = new File(configXml.getPathDam15Recebido().toUri()).listFiles();
			ArrayList<RegressFile> regressFiles = new ArrayList<>();
			
			for (File stringPath : listFiles) {
				System.out.println(stringPath.getPath());
				Path path = FileSystems.getDefault().getPath(stringPath.getPath());
				
				regressFiles.add(new RegressFile(path, configXml));
			}
			
			System.out.println("Lista de arquivo de retorno Populado!");


		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
