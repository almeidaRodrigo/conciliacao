package dao;

import vo.Dam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DamDao {

	private Connection connection;

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @param connection
	 */
	public DamDao(Connection connection) {
		this.connection = connection;
	}

	public void insertDam(List<Dam> dams) {

	}

	public List<Dam> getDam(Dam dam) {
		return null;
	}
	
	public int countDam15(String numDam) throws SQLException{
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int retorno;
		
		String sql = "select count(*) from dba_juceb.dam where numdam = ?";
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, numDam);

		rs = stmt.executeQuery();
		rs.next();
		retorno = rs.getInt(1);
		
		conn.close();
		return retorno;
		
	}

}
