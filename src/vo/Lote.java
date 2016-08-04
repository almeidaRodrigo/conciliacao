/**
 * 
 */
package vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * @author rodrigo
 *
 */
public class Lote {

	private int codigoLote;
	private String codigoBanco;
	private BigInteger numeroLote;
	private Calendar dataLote;
	private int qtdDocs;
	private BigDecimal valorTotal;
	
	public Lote(){
		
	}
		
	/**
	 * @param codigoBanco
	 * @param numeroLote
	 * @param dataLote
	 * @param qtdDocs
	 * @param valorTotal
	 */
	public Lote(int codigoLote, String codigoBanco, BigInteger numeroLote, Calendar dataLote, int qtdDocs,
			BigDecimal valorTotal) {

		this.codigoLote = codigoLote;
		this.codigoBanco = codigoBanco;
		this.numeroLote = numeroLote;
		this.dataLote = dataLote;
		this.qtdDocs = qtdDocs;
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the codigoLote
	 */
	public int getCodigoLote() {
		return codigoLote;
	}

	/**
	 * @param codigoLote the codigoLote to set
	 */
	public void setCodigoLote(int codigoLote) {
		this.codigoLote = codigoLote;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the numeroLote
	 */
	public BigInteger getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(BigInteger numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the dataLote
	 */
	public Calendar getDataLote() {
		return dataLote;
	}

	/**
	 * @param dataLote the dataLote to set
	 */
	public void setDataLote(Calendar dataLote) {
		this.dataLote = dataLote;
	}

	/**
	 * @return the qtdDocs
	 */
	public int getQtdDocs() {
		return qtdDocs;
	}

	/**
	 * @param qtdDocs the qtdDocs to set
	 */
	public void setQtdDocs(int qtdDocs) {
		this.qtdDocs = qtdDocs;
	}

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	
	
}
