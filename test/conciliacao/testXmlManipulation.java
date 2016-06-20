package conciliacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import arquivo.ManipulateXml;
import vo.ConfigXml;

public class testXmlManipulation {
	

	public static void main(String[] args) {
		
		ArrayList<String> emailDestinatarios = new ArrayList<>();
		emailDestinatarios.add("suporte@juceb.ba.gov.br");
		
		try {
			new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).saveXml(new ConfigXml(
					"rodrigo", 
					"senhaTeste", 
					"c:\\dam15Recebido", 
					"c:\\dam15Historico",
					"c:\\damDefinitivoRecebido", 
					"c:\\damDefinitivoHistorico", 
					"BB", 
					emailDestinatarios, 
					"rodrigo@juceb.ba.gov.br", 
					"envio.ba.gov.br", 
					"", 
					"25", 
					false, 
					"",
					"Assunto do Email",
					"Mensagem inicial do email",
					(long) 6000,
					"c:\\erroConciliacao.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(new ManipulateXml("config.xml").openXml().getLayout().getLayoutDams().get(0).getColNumberEnd());
		
		try {
			System.out.println(((ConfigXml) new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).openXml()).getIntervaloMilisegundos());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

}
