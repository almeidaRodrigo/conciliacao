/**
 * 
 */
package utilitario;

import java.util.Calendar;

/**
 * @author rodrigo
 *
 */
public final class ObterCalendar {
	
	
	
	private ObterCalendar(){
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}
	
	public static Calendar obterCalendar(String data) throws Exception{
		int ano = Integer.parseInt(data.substring(0, 4));
		int mes = Integer.parseInt(data.substring(4, 6));
		int dia = Integer.parseInt(data.substring(6, 8));

		if((mes > 0 && mes <=12) && (dia <= 31) ) {
			Calendar c = Calendar.getInstance();
			c.set(ano, mes, dia);
			return c;
		}else{
			throw new Exception("A data solicitada não pode ser criada pois não esta no formato AAAAMMDD.");
		}

	}

}
