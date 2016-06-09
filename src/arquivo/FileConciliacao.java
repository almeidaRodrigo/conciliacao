package arquivo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public interface FileConciliacao {

	public abstract FileReader openFile(String Path) throws FileNotFoundException;

	public abstract void saveFile(FileReader file, String data);

	public abstract List<String> readFile(FileReader file);

	public abstract void moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut);

}
