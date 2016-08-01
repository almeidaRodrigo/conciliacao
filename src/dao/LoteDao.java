/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vo.Lote;
import vo.TipoDamEnum;


/**
 * @author rodrigo
 *
 */
public class LoteDao {
	private Connection connection;

	/**
	 * @param connection
	 */
	public LoteDao(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public int getProximoCodigoLote(TipoDamEnum tipoLote) throws Exception{
		Connection conn = this.getConnection();
		String sql;
		PreparedStatement stmt;
		ResultSet rs;
		
		sql = "select MAX(codigolote) from "+ tipoLote.getSchemaLote();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		if(rs.next()){
			return rs.getInt(1) + 1;
		}else{
			throw new Exception("Numero do próximo Código de Lote não encontrado!");
		}
		
		
	}
	
	public void insertLote(Lote lote, TipoDamEnum tipoLote) throws Exception {
		Connection conn = this.getConnection();
		String sql;
		PreparedStatement stmt;
		ResultSet rs;

		sql = "select numerolote from "+ tipoLote.getSchemaLote() +" where numerolote = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, lote.getNumeroLote().intValue());
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			throw new Exception("Já existe este lote inserido na base de dados. Número do lote: "
					+ rs.getString(1)  
					+". Favor verificar!");
		} else {
			sql = "insert into " + tipoLote.getSchemaLote() + " values (?,?,?,?,?,?); ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.getProximoCodigoLote(tipoLote));
			stmt.setString(2, lote.getCodigoBanco());
			stmt.setInt(3, lote.getNumeroLote().intValue());
			stmt.setDate(4, new Date(lote.getDataLote().getTimeInMillis()));
			stmt.setInt(5, lote.getQtdDocs());
			stmt.setBigDecimal(6, lote.getValorTotal());
			
			System.out.println(stmt);
			
			stmt.execute();
		}

	}


}
