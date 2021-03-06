package dao;

import vo.Dam;
import vo.TipoDamEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import arquivo.RegressFile;

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

	public void insertDam(RegressFile regressFile, TipoDamEnum tipoDam) throws Exception {
		/*
		 * CODIGOLOTE: index 1
		 * NUMSEQ: index 2
		 * CODIGOAGENCIA: index 3 
		 * NUMDAM: index 4
		 * SEQDUPLICA��O:index 5 
		 * NUMREQ: index 6
		 * TIPODOCUMENTO: index 7 
		 * CODIGOUSUARIO: index 8
		 * CPFCNPJ: index 9
		 * DATAEMISSAO: index 10
		 * DATAARRECADACAO: index 11
		 * DATACREDITO: index 12 
		 * VALORDOCUMENTO: index 13
		 * VALORPAGO: index 14
		 * FORMAPAGAMENTO: index 15 
		 * VALORTARIFA: index 16
		 */
		
		Connection conn = this.getConnection();
		String sql;
		PreparedStatement stmt;
		ArrayList<Dam> lDams = regressFile.getDams();
		
		/*
		 * O IF abaixo foi deselegantemente inserido neste metodo porque a tabela DAM_DEFINITIVO esta desnormatizada, contendo campos que n�o deveria!
		 * A tabela foi deixada como esta, devido a receio de impacto negativo p�s altera��o para algum sistema, ainda desconhecido deste analista.
		 */
		if(tipoDam == TipoDamEnum.DEFINITIVO){
			sql = "insert into " + tipoDam.getSchemaDam() + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, null,null,null) ";
		}else{
			sql = "insert into " + tipoDam.getSchemaDam() + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		}

		stmt = conn.prepareStatement(sql);

		for (Dam dam : lDams) {

			stmt.setInt(1, regressFile.getLote().getCodigoLote());
			stmt.setInt(2, dam.getNumSeq());
			stmt.setString(3, dam.getCodigoAgencia());
			stmt.setString(4, dam.getNumDam());
			stmt.setInt(5, dam.getSeqDuplicacao());
			stmt.setInt(6, dam.getNumReq());
			stmt.setString(7, dam.getTipoDocumento());
			stmt.setString(8,dam.getCodigoUsuario());
			stmt.setString(9, dam.getCpfCnpj());
			stmt.setString(10, null);
			stmt.setDate(11, new Date(dam.getDataArrecadacao().getTimeInMillis()));
			stmt.setDate(12, new Date(dam.getDataCredito().getTimeInMillis()));
			stmt.setBigDecimal(13, null);
			stmt.setBigDecimal(14, dam.getValorPago());
			stmt.setString(15, String.valueOf(dam.getFormaPagamento()));
			stmt.setBigDecimal(16, dam.getValorTarifa());

			stmt.addBatch();
		}

		try {
			stmt.executeBatch();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}

	}
	
	public int countDam(String numDam, TipoDamEnum tipoDam) throws Exception{
		int retorno;
		String sql = "select count(*) from " + tipoDam.getSchemaDam() +" where numdam = ?";
		Connection conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);;
		ResultSet rs = null;
				
		stmt.setString(1, numDam);

		rs = stmt.executeQuery();
		rs.next();
		retorno = rs.getInt(1);

		conn.close();
		return retorno;
		
	}


}
