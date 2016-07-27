package utilitario;

import java.util.ArrayList;

import arquivo.RegressFile;
import vo.Dam;

public final class RegressTreatment {
	
	private RegressTreatment(){
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}

	public static Boolean isValidRegressFile(RegressFile regressFile) throws Exception {
		try {
			testHeader(regressFile.getHeader());
			testDams(regressFile.getDams());
		} catch (Exception e) {
			Exception ex = new Exception(e.getMessage() + " O arquivo que provocou o erro é " + regressFile.getAbsolutePath());
			throw ex;
		}

		return true;
	}
	
	private static void testHeader(String header) throws Exception{
		if(!header.substring(0, 1).equals("A")){
			throw new Exception("Erro detectado no identificador do cabeçalho do arquivo retorno, o mesmo deve iniciar com o caracter 'A'.");
		}
	}
	
	private static void testDams(ArrayList<Dam> lDams) throws Exception{
		int linhaDam = 2;
		
		for (Dam dam : lDams) {
			if(dam.getCodigoRegistro() == null || !dam.getCodigoRegistro().equals("G"))
				throw new Exception("Erro detectado no identificador de registro do DAM. Era esperado o caracter 'G' no inicio da linha de retorno. Linha onde o erro foi detectado: "+ linhaDam +".");
			if(dam.getCodigoLote() <= 0 )
				throw new Exception("Erro detectado no código do lote do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getCodigoLote() +"." );
			if(dam.getNumSeq() < 0)
				throw new Exception("Erro detectado no número sequencial do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getNumSeq() +".");
			if(dam.getNumDam() == null)
				throw new Exception("Erro detectado no número do DAM, o número não pode ser nulo. Linha onde o erro foi detectado: "+ linhaDam +".");
			if(dam.getSeqDuplicacao() < 0)
				throw new Exception("Erro detectado no sequencial de duplicação do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getSeqDuplicacao() +".");
			/*
			if(dam.getTipoDocumento() == null)
				throw new Exception("Erro detectado no tipo do documento do DAM, o tipo não pode ser nulo. Linha onde o erro foi detectado: "+ linhaDam +".");
			*/
			if(dam.getDataArrecadacao() == null)
				throw new Exception("Erro detectado na data de arrecadação do DAM, a data não pode ser nula. Linha onde o erro foi detectado: "+ linhaDam +".");
			/*
			if(dam.getDataCredito() == null)
				throw new Exception("Erro detectado na data de credito do DAM, a data não pode ser nula. Linha onde o erro foi detectado: "+ linhaDam +".");
			*/
			if(dam.getValorPago() <= 0)
				throw new Exception("Erro detectado no valor pago do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getValorPago() +".");
			if(dam.getFormaPagamento() <= 0)
				throw new Exception("Erro detectado na forma de pagamento do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getFormaPagamento() +".");
			
			linhaDam++;
		}

	}
	
	

}
