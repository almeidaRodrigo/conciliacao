/**
 * 
 */
package conexao;

import java.sql.Connection;

/**
 * @author rodrigo
 *
 */
public abstract class SqlServerConnection {
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
