package arquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import com.thoughtworks.xstream.XStream;
import xml.GeradorXml;

/*
 * ReaderXml: Classe responsavel por manipulacao de leitura do arquivo fixo "
 */

public class ManipulateXml extends ConciliacaoFiles {

	public ManipulateXml(Path path) {
		super(path);
		//Efetua a leitura do arquivo config.xml dentro da pasta do arquivo JAR (executavel deste projeto)
		//Path path = FileSystems.getDefault().getPath(System.getProperty("user.dir") , fileName);
	}

	public Object openXml() throws FileNotFoundException {
		return new XStream().fromXML(this.openFileStream());
	}
	
	public void saveXml(Object o) throws IOException{
		this.saveFile(new GeradorXml(o).makeXml(), false);
	}
	
	


}
