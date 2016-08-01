/**
 * 
 */
package vo;

import java.math.BigInteger;
import java.util.Calendar;

/**
 * @author rodrigo
 *
 */
public class Header {
	private String codigoRegistro;
	private String codigoRemessa;
	private String codigoConvenio;
	private String nomeEmpresa;
	private String codigoBanco;
	private Calendar dataGeracaoArquivo;
	private BigInteger numLote;
	private String versaoLayout;
	private String codigobarras;
	
	/**
	 * @param codigoRegistro
	 * @param codigoRemessa
	 * @param codigoConvenio
	 * @param nomeEmpresa
	 * @param codigoBanco
	 * @param dataGeracaoArquivo
	 * @param numLote
	 * @param versaoLayout
	 * @param codigobarras
	 */
	public Header(String codigoRegistro, String codigoRemessa, String codigoConvenio, String nomeEmpresa,
			String codigoBanco, Calendar dataGeracaoArquivo, BigInteger numLote, String versaoLayout, String codigobarras) {
		this.codigoRegistro = codigoRegistro;
		this.codigoRemessa = codigoRemessa;
		this.codigoConvenio = codigoConvenio;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoBanco = codigoBanco;
		this.dataGeracaoArquivo = dataGeracaoArquivo;
		this.numLote = numLote;
		this.versaoLayout = versaoLayout;
		this.codigobarras = codigobarras;
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
	 * @return the codigoRemessa
	 */
	public String getCodigoRemessa() {
		return codigoRemessa;
	}

	/**
	 * @param codigoRemessa the codigoRemessa to set
	 */
	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	/**
	 * @return the codigoConvenio
	 */
	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param codigoConvenio the codigoConvenio to set
	 */
	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * @return the nomeEmpresa
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa the nomeEmpresa to set
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
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
	 * @return the dataGeracaoArquivo
	 */
	public Calendar getDataGeracaoArquivo() {
		return dataGeracaoArquivo;
	}

	/**
	 * @param dataGeracaoArquivo the dataGeracaoArquivo to set
	 */
	public void setDataGeracaoArquivo(Calendar dataGeracaoArquivo) {
		this.dataGeracaoArquivo = dataGeracaoArquivo;
	}

	/**
	 * @return the numLote
	 */
	public BigInteger getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote the numLote to set
	 */
	public void setNumLote(BigInteger numLote) {
		this.numLote = numLote;
	}

	/**
	 * @return the versaoLayout
	 */
	public String getVersaoLayout() {
		return versaoLayout;
	}

	/**
	 * @param versaoLayout the versaoLayout to set
	 */
	public void setVersaoLayout(String versaoLayout) {
		this.versaoLayout = versaoLayout;
	}

	/**
	 * @return the codigobarras
	 */
	public String getCodigobarras() {
		return codigobarras;
	}

	/**
	 * @param codigobarras the codigobarras to set
	 */
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	
	
	
	
}
