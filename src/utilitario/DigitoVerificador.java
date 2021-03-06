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
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}
	
	public static String obterDigito(String value){
		int sum = 0;
		int resto = 0;

		//String sValue = "3" + String.valueOf(value); //O DAM deve iniciar com '3' + 7 numeros e passar a ser incrementado a partir dai.
		String digito = "";
		
		for(int i = 0; i < value.length(); i++){
			digito = value.substring(i, i+1);
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
