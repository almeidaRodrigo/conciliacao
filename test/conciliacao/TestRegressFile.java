package conciliacao;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import arquivo.ManipulateXml;
import arquivo.RegressFile;
import vo.ConfigXml;
import vo.Dam;

public class TestRegressFile {

	public static void main(String[] args) {
		ConfigXml configXml;

		try {
			configXml = (ConfigXml) new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).openXml();
			File[] listFiles = new File(configXml.getPathDam15Recebido().toUri()).listFiles();
			ArrayList<RegressFile> regressFiles = new ArrayList<>();
			
			//int i = 0;
			for (File stringPath : listFiles) {

				System.out.println(stringPath.getPath());
				//System.out.println(i);
				Path path = FileSystems.getDefault().getPath(stringPath.getPath());
				
				regressFiles.add(new RegressFile(path, configXml));
				/*
				System.out.println("---");
				System.out.println(regressFiles.get(i).getDams().get(0).getCodigoLote());
				System.out.println(regressFiles.get(i).getDams().get(0).getNumSeq());
				System.out.println(regressFiles.get(i).getDams().get(0).getCodigoAgencia());
				System.out.println(regressFiles.get(i).getDams().get(0).getNumDam());
				System.out.println(regressFiles.get(i).getDams().get(0).getSeqDuplicacao());
				System.out.println(regressFiles.get(i).getDams().get(0).getNumReq());
				System.out.println(regressFiles.get(i).getDams().get(0).getTipoDocumento());
				System.out.println(regressFiles.get(i).getDams().get(0).getCodigoUsuario());
				System.out.println(regressFiles.get(i).getDams().get(0).getCpfCnpj());
				//System.out.println(regressFiles.get(0).getDams().get(0).getDataEmissao().getTime().toString());
				System.out.println(regressFiles.get(i).getDams().get(0).getDataArrecadacao().getTime().toString());
				//System.out.println(regressFiles.get(0).getDams().get(0).getDataCredito().getTime().toString());
				System.out.println(regressFiles.get(i).getDams().get(0).getValorPago());
				System.out.println(regressFiles.get(i).getDams().get(0).getFormaPagamento());
				System.out.println(regressFiles.get(i).getDams().get(0).getValorTarifa());
				System.out.println("");
				//System.out.println("---");
				
				i= i+1;
				*/
			}
			/*
			System.out.println("");
			System.out.println("---");
			System.out.println("");
			*/
			//System.out.println("Lista de arquivo de retorno Populado!");
			
			for (RegressFile regressFile : regressFiles) {
				ArrayList<Dam> lDams = regressFile.getDams();
				
				for (Dam dam : lDams) {
					System.out.println("---");
					System.out.println(dam.getCodigoLote());
					System.out.println(dam.getNumSeq());
					System.out.println(dam.getNumDam());
					System.out.println(dam.getSeqDuplicacao());
					System.out.println(dam.getNumReq());
					System.out.println(dam.getTipoDocumento());
					System.out.println(dam.getCodigoUsuario());
					System.out.println(dam.getCpfCnpj());
					System.out.println(dam.getDataEmissao());
					System.out.println(dam.getDataArrecadacao().getTime().toString());
					System.out.println(dam.getDataCredito());
					System.out.println(dam.getValorPago());
					System.out.println(dam.getFormaPagamento());
					System.out.println(dam.getValorTarifa());
					System.out.println("---");
					System.out.println("");
				}
			}


		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
