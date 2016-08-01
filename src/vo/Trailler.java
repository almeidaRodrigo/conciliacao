/**
 * 
 */
package vo;

import java.math.BigDecimal;

/**
 * @author rodrigo
 *
 */
public class Trailler {
	private String codigoRegistro;
	private int totalRegistros;
	private BigDecimal valorTotal;
	
	/**
	 * @param codigoRegistro
	 * @param totalRegistros
	 * @param valorTotal
	 */
	public Trailler(String codigoRegistro, int totalRegistros, BigDecimal valorTotal) {
		this.codigoRegistro = codigoRegistro;
		this.totalRegistros = totalRegistros;
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the codigoRegistro
	 */
	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @param codigoRegistro the codigoRegistro to set
	 */
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	/**
	 * @return the totalRegistros
	 */
	public int getTotalRegistros() {
		return totalRegistros;
	}

	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
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
