package arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public abstract class ReaderXml extends ConciliacaoFiles implements FileConciliacao {

	public ReaderXml() {
		//Efetua a leitura do arquivo config.xml dentro da pasta do arquivo JAR (executavel deste projeto)
		super(System.getProperty("user.dir") + "\\config.xml");

	}

	public void OpenXml(FileInputStream in) {

	}


	/**
	 * @see arquivo.FileConciliacao#openFile(java.lang.String)
	 */
	public File openFile(String Path) {
		return null;
	}


	/**
	 * @see arquivo.FileConciliacao#saveFile(arquivo.FileConciliacao, java.lang.String)
	 */
	public void saveFile(File file, String data) {

	}


	/**
	 * @see arquivo.FileConciliacao#readFile(arquivo.FileConciliacao)
	 */
	public List<String> readFile(FileReader file) {
		return null;
	}


	/**
	 * @see arquivo.FileConciliacao#moveFile(arquivo.ConciliacaoFiles, arquivo.ConciliacaoFiles)
	 */
	public void moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut) {

	}

}
