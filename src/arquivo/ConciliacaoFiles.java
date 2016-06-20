package arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ConciliacaoFiles implements IFileConciliacao {

	private Path path;

	public ConciliacaoFiles(Path path) {
		this.setPath(path);
	}

	public Path getPath() {
		return this.path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Boolean isDirEmpty() {
		return null;
	}

	/**
	 * @throws FileNotFoundException 
	 * @see arquivo.IFileConciliacao#openFile(java.lang.String)
	 */
	public FileReader openFileReader() throws FileNotFoundException {
		return new FileReader(this.getPath().toString());
	}
	
	@Override
	public FileInputStream openFileStream() throws FileNotFoundException {
		return new FileInputStream(this.getPath().toString());
		
	}

	/**
	 * @throws IOException 
	 * @see arquivo.IFileConciliacao#saveFile(arquivo.IFileConciliacao, java.lang.String)
	 */
	public void saveFile(String data, Boolean update) throws IOException {
		FileWriter file = new FileWriter(this.getPath().toString(), update);
		file.write(data);
		file.close();
	}


	/**
	 * @see arquivo.IFileConciliacao#readFile(arquivo.IFileConciliacao)
	 */
	public List<String> readFile(FileReader file) {
		//TODO: Implementação readFile
		return null;
	}


	/**
	 * @see arquivo.IFileConciliacao#moveFile(arquivo.ConciliacaoFiles, arquivo.ConciliacaoFiles)
	 */
	public void moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut) {
		//TODO: Implementação moveFile

	}

}
