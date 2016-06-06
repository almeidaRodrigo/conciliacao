package arquivo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class ConciliacaoFiles implements FileConciliacao {

	private String Path;

	public ConciliacaoFiles(String path) {
		this.setPath(path);
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Boolean isDirEmpty() {
		return null;
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
