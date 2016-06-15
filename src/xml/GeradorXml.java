package xml;

import com.thoughtworks.xstream.XStream;

public class GeradorXml {
	Object obj;
	
	public GeradorXml(Object obj){
		this.obj = obj;
		
	}
	
	public String makeXml(){
		XStream xstream = new XStream();
		return  xstream.toXML(this.obj);	
	}
	
}
