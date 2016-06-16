package arquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import vo.ConfigXml;
import xml.GeradorXml;

/*
 * ReaderXml: Classe responsavel por manipulacao de leitura do arquivo fixo "
 */

public class ManipulateXml extends ConciliacaoFiles {

	public ManipulateXml() {
		//Efetua a leitura do arquivo config.xml dentro da pasta do arquivo JAR (executavel deste projeto)
		super(System.getProperty("user.dir") + "\\config.xml");

	}

	public ConfigXml openXml() throws FileNotFoundException {
		return (ConfigXml) new XStream().fromXML(this.openFileStream());
	}
	
	public void saveXml(Object o) throws IOException{
		this.saveFile(new GeradorXml(o).makeXml(), false);
	}
	
	


}
