package arquivo;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	 * @throws FileNotFoundException 
	 * @see arquivo.FileConciliacao#openFile(java.lang.String)
	 */
	public FileReader openFile(String Path) throws FileNotFoundException {
		//TODO: Implementação openFile
		return new FileReader(Path);
	}


	/**
	 * @see arquivo.FileConciliacao#saveFile(arquivo.FileConciliacao, java.lang.String)
	 */
	public void saveFile(FileReader file, String data) {
		//TODO: Implementação saveFile
	}


	/**
	 * @see arquivo.FileConciliacao#readFile(arquivo.FileConciliacao)
	 */
	public List<String> readFile(FileReader file) {
		//TODO: Implementação readFile
		return null;
	}


	/**
	 * @see arquivo.FileConciliacao#moveFile(arquivo.ConciliacaoFiles, arquivo.ConciliacaoFiles)
	 */
	public void moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut) {
		//TODO: Implementação moveFile

	}

}
