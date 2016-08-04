package utilitario;

import java.util.ArrayList;

import arquivo.RegressFile;
import vo.Dam;
import vo.Header;
import vo.Trailler;

public final class RegressTreatment {
	
	private RegressTreatment(){
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}

	public static Boolean isValidRegressFile(RegressFile regressFile) throws Exception {
		try {
			testHeader(regressFile.getHeader());
			testDams(regressFile.getDams());
			testFooter(regressFile);
		} catch (Exception e) {
			Exception ex = new Exception(e.getMessage() + " O arquivo que provocou o erro é " 
					+ regressFile.getAbsolutePath() +"."
					+ " Verifique adcionalmente se o arquivo de configuração 'Config.xml' esta com os valores das posições dos campos.");
			throw ex;
		}

		return Boolean.TRUE;
	}
	
	private static void testHeader(Header header) throws Exception{
		if(!header.getCodigoRegistro().equals("A")){
			throw new Exception("Erro detectado no identificador do cabeçalho do arquivo retorno, o mesmo deve iniciar com o caracter 'A'.");
		}
	}
	
	private static void testFooter(RegressFile regressFile) throws Exception{
		Trailler footer = regressFile.getTrailler();
		
		if(!footer.getCodigoRegistro().equals("Z"))
			throw new Exception("Erro detectado no identificador TRAILLER (roda pé). O mesmo deve iniciar com 'Z'.");
		
		if(regressFile.getTotalQtdLinhas() !=footer.getTotalRegistros())
			throw new Exception("Erro detectado no somatorio do número de linhas do arquivo. Favor verificar QTD de linhas e o valor correspondente no TRAILLER (roda pé).");
		
		if(regressFile.getTotalValorDams().compareTo(footer.getValorTotal()) != 0){
			throw new Exception("Erro detectado no somatorio do valor recebido. O valor recebido foi: " + regressFile.getTotalValorDams() + "."
					+" O valor constante no Trailler (roda pé) é: " +footer.getValorTotal() + "."
					+" Favor verificar somatorio dos DAMs e o valor correspondente no TRAILLER (roda pé).");
		}
			
	}
	
	private static void testDams(ArrayList<Dam> lDams) throws Exception{
		int linhaDam = 2;
		
		for (Dam dam : lDams) {
			if(dam.getCodigoRegistro() == null || !dam.getCodigoRegistro().equals("G"))
				throw new Exception("Erro detectado no identificador de registro do DAM. Era esperado o caracter 'G' no inicio da linha de retorno. Linha onde o erro foi detectado: "+ linhaDam +".");
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
			if(dam.getValorPago() != null){
				if(dam.getValorPago().intValue() <= 0)
					throw new Exception("Erro detectado no valor pago do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getValorPago().doubleValue() +".");
			}else{
				throw new Exception("Erro detectado no no valor pago do DAM, o número não pode ser nulo. Linha onde o erro foi detectado: "+ linhaDam +".");
			}
			if(dam.getFormaPagamento() <= 0)
				throw new Exception("Erro detectado na forma de pagamento do DAM. Linha onde o erro foi detectado: "+ linhaDam +". Valor obtido: " + dam.getFormaPagamento() +".");
			
			linhaDam++;
		}

	}
	
	

}
