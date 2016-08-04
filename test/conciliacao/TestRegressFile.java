package conciliacao;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import arquivo.ManipulateXml;
import arquivo.RegressFile;
import vo.ConfigXml;
import vo.Dam;
import vo.TipoDamEnum;

public class TestRegressFile {

	public static void main(String[] args) {
		ConfigXml configXml;
		
		try {
			configXml = (ConfigXml) new ManipulateXml(FileSystems.getDefault().getPath(System.getProperty("user.dir"), File.separator, "config.xml")).openXml();
			
			//Testando o DAM parcial. DAM de 15 em 15 minutos. Para testar DAM definitivo, mudar o ENUM para DEFINITIVO.
			File[] listFiles = new File(configXml.getPathDam15Recebido().toUri()).listFiles();
			
			ArrayList<RegressFile> regressFiles = new ArrayList<>();

			for (File stringPath : listFiles) {
				Path path = FileSystems.getDefault().getPath(stringPath.getPath());
				
				//Testando o DAM parcial. DAM de 15 em 15 minutos. Para testar DAM definitivo, mudar o ENUM para DEFINITIVO.
				regressFiles.add(new RegressFile(path, configXml, TipoDamEnum.PARCIAL));
			}

			for (RegressFile regressFile : regressFiles) {
				ArrayList<Dam> lDams = regressFile.getDams();
				
				System.out.println(regressFile.getAbsolutePath());
				System.out.println(regressFile.getTotalQtdLinhas());
				System.out.println(regressFile.getTotalValorDams());
				
				for (Dam dam : lDams) {

					System.out.println("---");
					System.out.println("Codigo Lote: "+regressFile.getLote().getCodigoLote());
					System.out.println("Numero Seq: "+dam.getNumSeq());
					System.out.println("NumDam: "+dam.getNumDam());
					System.out.println("SeqDuplicação: "+dam.getSeqDuplicacao());
					System.out.println("NumRequerimento: "+dam.getNumReq());
					System.out.println("TipoDocumento: "+dam.getTipoDocumento());
					System.out.println("CodigoUsuario: "+dam.getCodigoUsuario());
					System.out.println("CpfCnpj: "+dam.getCpfCnpj());
					//System.out.println(dam.getDataEmissao());
					System.out.println("Data Arrecadação: "+dam.getDataArrecadacao().getTime().toString());
					System.out.println("DataCredito: "+dam.getDataCredito());
					System.out.println("DataValorPago: "+dam.getValorPago());
					System.out.println("Forma de Pagamento: "+dam.getFormaPagamento());
					System.out.println("ValorTarifa: "+dam.getValorTarifa());
					System.out.println("---");
					System.out.println("");
				
				}
				
			}


		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
