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
public final class ObterConexao {
	private ObterConexao(){
		//Evitar instancia de classe, pois, esta classe somente tem metodos static.
	}
	
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
