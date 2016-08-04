package vo;

import java.math.BigDecimal;
import java.util.Calendar;

import utilitario.DigitoVerificador;

public class Dam {

	public int numSeq;
	
	public int seqDuplicacao;

	public String codigoAgencia;

	public String numDam;

	public int numReq;

	public String tipoDocumento;

	public String codigoUsuario;

	public String cpfCnpj;

	//private Calendar dataEmissao;

	public Calendar dataArrecadacao;

	public Calendar dataCredito;

	//private float valorDocumento;

	public BigDecimal valorPago;

	public int formaPagamento;

	public BigDecimal valorTarifa;
	
	public String codigoRegistro;
	
	public Dam(){
		
	}

	/**
	 * @param numSeq
	 * @param codigoAgencia
	 * @param numDam
	 * @param seqDuplicacao
	 * @param numReq
	 * @param tipoDocumento
	 * @param codigoUsuario
	 * @param cpfCnpj
	 * @param dataArrecadacao
	 * @param dataCredito
	 * @param valorPago
	 * @param formaPagamento
	 * @param valorTarifa
	 * 
	 */
	public Dam(int numSeq, String codigoAgencia, String numDam, int seqDuplicacao, int numReq,
			String tipoDocumento, String codigoUsuario, String cpfCnpj, Calendar dataArrecadacao,
			Calendar dataCredito, BigDecimal valorPago, int formaPagamento, BigDecimal valorTarifa) {

		this.numSeq = numSeq;
		this.codigoAgencia = codigoAgencia;
		this.numDam = numDam;
		this.seqDuplicacao = seqDuplicacao;
		this.numReq = numReq;
		this.tipoDocumento = tipoDocumento;
		this.codigoUsuario = codigoUsuario;
		this.cpfCnpj = cpfCnpj;
		//this.dataEmissao = dataEmissao;
		this.dataArrecadacao = dataArrecadacao;
		this.dataCredito = dataCredito;
		//this.valorDocumento = valorDocumento;
		this.valorPago = valorPago;
		this.formaPagamento = formaPagamento;
		this.valorTarifa = valorTarifa;
		
	}

	/**
	 * @return the numSeq
	 */
	public int getNumSeq() {
		return numSeq;
	}

	/**
	 * @param numSeq the numSeq to set
	 */
	public void setNumSeq(int numSeq) {
		this.numSeq = numSeq;
	}

	/**
	 * @return the seqDuplicacao
	 */
	public int getSeqDuplicacao() {
		return seqDuplicacao;
	}

	/**
	 * @param seqDuplicacao the seqDuplicacao to set
	 */
	public void setSeqDuplicacao(int seqDuplicacao) {
		this.seqDuplicacao = seqDuplicacao;
	}

	/**
	 * @return the codigoAgencia
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * @param codigoAgencia the codigoAgencia to set
	 */
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	/**
	 * @return the numDam
	 */
	public String getNumDam() {
		return numDam;
	}
	
	/**
	 * @return the numDam + DV mod11
	 */
	public String getDigitoVerificador() {
		return DigitoVerificador.mod11(Integer.parseInt(numDam));
	}
	
	/**
	 * @param numDam the numDam to set
	 */
	public void setNumDam(String numDam) {
		this.numDam = numDam;
	}

	/**
	 * @return the numReq
	 */
	public int getNumReq() {
		return numReq;
	}

	/**
	 * @param numReq the numReq to set
	 */
	public void setNumReq(int numReq) {
		this.numReq = numReq;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the dataArrecadacao
	 */
	public Calendar getDataArrecadacao() {
		return dataArrecadacao;
	}

	/**
	 * @param dataArrecadacao the dataArrecadacao to set
	 */
	public void setDataArrecadacao(Calendar dataArrecadacao) {
		this.dataArrecadacao = dataArrecadacao;
	}

	/**
	 * @return the dataCredito
	 */
	public Calendar getDataCredito() {
		return dataCredito;
	}

	/**
	 * @param dataCredito the dataCredito to set
	 */
	public void setDataCredito(Calendar dataCredito) {
		this.dataCredito = dataCredito;
	}

	/**
	 * @return the valorPago
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago the valorPago to set
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * @return the formaPagamento
	 */
	public int getFormaPagamento() {
		return formaPagamento;
	}

	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	/**
	 * @return the valorTarifa
	 */
	public BigDecimal getValorTarifa() {
		return valorTarifa;
	}

	/**
	 * @param valorTarifa the valorTarifa to set
	 */
	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
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

}
