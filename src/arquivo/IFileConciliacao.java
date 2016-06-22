package arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public interface IFileConciliacao {

	FileReader openFileReader() throws FileNotFoundException;
	
	public FileInputStream openFileStream() throws FileNotFoundException;

	public void saveFile(String data, Boolean update) throws IOException;

	public boolean moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut);

}
