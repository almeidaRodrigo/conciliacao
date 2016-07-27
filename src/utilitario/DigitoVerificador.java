/**
 * 
 */
package utilitario;

/**
 * @author rodrigo
 *
 */
public final class DigitoVerificador {
	
	private DigitoVerificador(){
		
	}
	
	public static String mod11(int value){
		int sum = 0;
		int resto = 0;

		String sValue = "3" + String.valueOf(value);
		String digito = "";
		
		for(int i = 0; i < sValue.length(); i++){
			digito = sValue.substring(i, i+1);
			sum = sum + ( (i+1) * Integer.parseInt(digito));
		}
		
		resto = sum % 11;
		
		if(resto > 1){
			return ""+(11-resto);
		}else{
			return "0";
		}
		
	}
}
