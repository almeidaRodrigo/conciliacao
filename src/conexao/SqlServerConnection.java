/**
 * 
 */
package conexao;

import java.sql.Connection;

/**
 * @author rodrigo
 *
 */
public final class SqlServerConnection {
	private SqlServerConnection(){
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}
	
	/**
	 * @param configXml
	 * @throws Exception 
	 */
	public static Connection connect() throws Exception {
		return ConexaoFactory.connect(
				"sqlserver", 
				"jucebauto", 
				"juceb", 
				"10.2.8.86", 
				1433, 
				"");
	}

}
