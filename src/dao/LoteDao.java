/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import arquivo.RegressFile;
import vo.TipoDamEnum;


/**
 * @author rodrigo
 *
 */
public class LoteDao {
	private Connection connection;
	private DamDao damDao;

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
	
	/**
	 * @return the damDao
	 */
	public DamDao getDamDao() {
		return damDao;
	}

	/**
	 * @param damDao the damDao to set
	 */
	public void setDamDao(DamDao damDao) {
		this.damDao = damDao;
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
	
	public void insertLote(RegressFile regressFile, TipoDamEnum tipoLote) throws Exception {
		StringBuilder sql = new StringBuilder();
		PreparedStatement stmt;
		ResultSet rs;
		
		sql.delete(0, sql.length());
		sql.append("select numerolote from "+ tipoLote.getSchemaLote() +" where numerolote = ?");
		
		stmt = connection.prepareStatement(sql.toString());
		stmt.setInt(1, regressFile.getLote().getNumeroLote().intValue());
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			throw new Exception("Já existe este lote inserido na base de dados. Número do lote: "
					+ rs.getString(1)  
					+". Favor verificar!");
		} else {
			damDao = new DamDao(connection);
			
			sql.delete(0, sql.length());
			sql.append("insert into " + tipoLote.getSchemaLote() + " values (?,?,?,?,?,?) ");

			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql.toString());

			stmt.setInt(1, regressFile.getLote().getCodigoLote());
			stmt.setString(2, regressFile.getLote().getCodigoBanco());
			stmt.setInt(3, regressFile.getLote().getNumeroLote().intValue());
			stmt.setDate(4, new Date(regressFile.getLote().getDataLote().getTimeInMillis()));
			stmt.setInt(5, regressFile.getLote().getQtdDocs());
			stmt.setBigDecimal(6, regressFile.getLote().getValorTotal());

			try {
				stmt.execute();
				damDao.insertDam(regressFile, tipoLote);
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
				throw new Exception("Não foi possivel inserir os DAMs deste lote, verifique o erro a seguir: "+e.getMessage());
			}

		}

	}


}
