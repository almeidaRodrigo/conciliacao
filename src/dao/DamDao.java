package dao;

import vo.Dam;
import vo.TipoDamEnum;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DamDao {

	private Connection connection;
	
	/**
	 * @param connection
	 */
	public DamDao(Connection connection) {
		this.connection = connection;
	}

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

	public void insertDam(List<Dam> dams, TipoDamEnum tipoDam) {
		Connection conn = this.getConnection();
		StringBuilder sql = new StringBuilder();
		PreparedStatement stmt;
		ResultSet rs;
		
		//conn.setAutoCommit(false);
		
		for (Dam dam : dams) {
			/*
			sql.append("insert into " + tipoDam.getSchemaDam() + " (?,?,?,?,?); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, dam.getCodigoLote());
			stmt.setInt(2, );
			stmt.setDate(3, );
			stmt.setInt(4, );
			stmt.setBigDecimal(5, );
			stmt.execute();
			*/
		}
		
		

		
		
		
	}

	public List<Dam> getDam(Dam dam) {
		return null;
	}
	
	public int countDam(String numDam, TipoDamEnum tipoDam) throws SQLException{
		Connection conn = this.getConnection();
		String sql = "select count(*) from " + tipoDam.getSchemaDam() +" where numdam = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);;
		ResultSet rs = null;
		int retorno;

		stmt.setString(1, numDam);
		
		rs = stmt.executeQuery();
		rs.next();
		retorno = rs.getInt(1);
		
		conn.close();
		return retorno;
		
	}

}
