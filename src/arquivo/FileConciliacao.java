package arquivo;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public interface FileConciliacao {

	public abstract File openFile(String Path);

	public abstract void saveFile(File file, String data);

	public abstract List<String> readFile(FileReader file);

	public abstract void moveFile(ConciliacaoFiles fileIn, ConciliacaoFiles fileOut);

}
