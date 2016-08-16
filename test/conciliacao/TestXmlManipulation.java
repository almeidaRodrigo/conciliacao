package conciliacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import arquivo.ManipulateXml;
import vo.ConfigXml;

public class TestXmlManipulation {
	

	public static void main(String[] args) {
		
		ArrayList<String> emailDestinatarios = new ArrayList<>();
		emailDestinatarios.add("suporte@juceb.ba.gov.br");
		
		try {
			new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).saveXml(new ConfigXml(
					"rodrigo", 
					"senhaTeste", 
					"10.57.0.106",
					"oracle",
					1521,
					"prod",
					"y:\\bb", 
					"d:\\Conciliacao\\historico\\bb\\2016",
					"y:\\bbcon", 
					"d:\\Conciliacao\\historicoD2\\bb\\2016", 
					"d:\\Concialicaov2\\damComErro", 
					emailDestinatarios, 
					"rodrigo@juceb.ba.gov.br", 
					"envio.ba.gov.br", 
					"", 
					"25", 
					false, 
					"",
					"ATENÇÃO: Erro Conciliação Bancária - Java",
					"Foi detectada inconsistência na execução do sistema: ",
					(long) 900000,
					"d:\\Concialicaov2\\erroConciliacao.txt"));
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
