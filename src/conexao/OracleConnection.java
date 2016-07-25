/**
 * 
 */
package conexao;

import java.sql.Connection;

import vo.ConfigXml;

/**
 * @author rodrigo
 *
 */
public abstract class OracleConnection {
		
	/**
	 * @param configXml
	 * @throws Exception 
	 */
	public static Connection connect(ConfigXml configXml) throws Exception {
		return ConexaoFactory.connect(
				configXml.getTipoBanco(), 
				configXml.getUserBd(), 
				configXml.getPassBd(), 
				configXml.getHostBd(), 
				configXml.getPortaBanco(), 
				configXml.getNomeServicoBanco());
	}

}
