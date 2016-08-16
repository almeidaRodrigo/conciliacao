package arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public interface IFileConciliacao {

	FileReader openFileReader() throws FileNotFoundException;
	
	public FileInputStream openFileStream() throws FileNotFoundException;

	public void saveFile(String data, Boolean update) throws IOException;

	public Boolean moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut) throws Exception;

}
