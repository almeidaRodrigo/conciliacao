package vo;

import java.util.Calendar;

public class Dam {

	public int CodigoLote;

	public int NumSeq;
	
	public int SeqDuplicacao;

	public String CodigoAgencia;

	public String NumDam;

	public int NumReq;

	public String TipoDocumento;

	public String CodigoUsuario;

	public String CpfCnpj;

	//private Calendar DataEmissao;

	public Calendar DataArrecadacao;

	public Calendar DataCredito;

	//private float ValorDocumento;

	public float ValorPago;

	public int FormaPagamento;

	public float ValorTarifa;
	
	public Dam(){
		
	}

	/**
	 * @param codigoLote
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
	public Dam(int codigoLote, int numSeq, String codigoAgencia, String numDam, int seqDuplicacao, int numReq,
			String tipoDocumento, String codigoUsuario, String cpfCnpj, Calendar dataArrecadacao,
			Calendar dataCredito, float valorPago, int formaPagamento, float valorTarifa) {
		CodigoLote = codigoLote;
		NumSeq = numSeq;
		CodigoAgencia = codigoAgencia;
		NumDam = numDam;
		SeqDuplicacao = seqDuplicacao;
		NumReq = numReq;
		TipoDocumento = tipoDocumento;
		CodigoUsuario = codigoUsuario;
		CpfCnpj = cpfCnpj;
		//DataEmissao = dataEmissao;
		DataArrecadacao = dataArrecadacao;
		DataCredito = dataCredito;
		//ValorDocumento = valorDocumento;
		ValorPago = valorPago;
		FormaPagamento = formaPagamento;
		ValorTarifa = valorTarifa;
		
	}

	public int getCodigoLote() {
		return this.CodigoLote;
	}

	public void setCodigoLote(int codigoLote) {

	}

	public int getNumSeq() {
		return this.NumSeq;
	}

	public void setNumSeq(float numSeq) {

	}

	public String getCodigoAgencia() {
		return this.CodigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {

	}

	public String getNumDam() {
		return this.NumDam;
	}

	public void setNumDam(String numDam) {

	}

	public int getSeqDuplicacao() {
		return this.SeqDuplicacao;
	}

	public void setSeqDuplicacao(int seqDuplicacao) {

	}

	public int getNumReq() {
		return 0;
	}

	public void setNumReq(int numReq) {

	}

	public String getTipoDocumento() {
		return null;
	}

	public void setTipoDocumento(String tipoDocumento) {

	}

	public String getCodigoUsuario() {
		return this.CodigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {

	}

	public String getCpfCnpj() {
		return null;
	}

	public void setCpfCnpj(String cpfCnpj) {

	}

	public Calendar getDataEmissao() {
		return null;
	}

	public void setDataEmissao(Calendar dataEmissao) {

	}

	public Calendar getDataArrecadacao() {
		return this.DataArrecadacao;
	}

	public void setDataArrecadacao(Calendar dataArrecadacao) {

	}

	public Calendar getDataCredito() {
		return this.DataCredito;
	}

	public void setDataCredito(Calendar dataCredito) {
		this.DataCredito = dataCredito;
	}
/*
	public float getValorDocumento() {
		return 0;
	}

	public void setValorDocumento(float valorDocumento) {

	}
*/
	public float getValorPago() {
		return this.ValorPago;
	}

	public void setValorPago(float valorPago) {

	}

	public int getFormaPagamento() {
		return this.FormaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.FormaPagamento = formaPagamento;
	}

	public float getValorTarifa() {
		return this.ValorTarifa;
	}

	public void setValorTarifa(float valorTarifa) {
		this.ValorTarifa = valorTarifa;
	}

}
