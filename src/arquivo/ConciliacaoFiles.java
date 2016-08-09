package arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

import vo.ConfigXml;
import vo.TipoDamEnum;

public class ConciliacaoFiles extends File implements IFileConciliacao {
	
	private static final long serialVersionUID = 721790979662936626L;
		
	public ConciliacaoFiles(Path path) {
		super(path.toUri());
	}
	
	public ConciliacaoFiles(String path) {
		super(path);
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
	 * @return 
	 * @throws Exception 
	 * @see arquivo.IFileConciliacao#moveFile(arquivo.ConciliacaoFiles, arquivo.ConciliacaoFiles)
	 */
	public Boolean moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut) throws Exception {
		if(fileOut.exists()){
			fileOut.delete();
		}
		
		return fileIn.renameTo(fileOut);

	}

}
