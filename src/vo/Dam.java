package vo;

import java.util.Calendar;
import arquivo.RegressReaderFile;

public class Dam {

	private int CodigoLote;

	private int NumSeq;

	private String CodigoAgencia;

	private String NumDam;

	private int SeqDuplicacao;

	private int NumReq;

	private String TipoDocumento;

	private String CodigoUsuario;

	private String CpfCnpj;

	//private Calendar DataEmissao;

	private Calendar DataArrecadacao;

	private Calendar DataCredito;

	//private float ValorDocumento;

	private float ValorPago;

	private String FormaPagamento;

	private float ValorTarifa;

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
			Calendar dataCredito, float valorPago, String formaPagamento, float valorTarifa) {
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

	public float getCodigoLote() {
		return 0;
	}

	public void setCodigoLote(int codigoLote) {

	}

	public void getNumSeq() {

	}

	public void setNumSeq(float numSeq) {

	}

	public String getCodigoAgencia() {
		return null;
	}

	public void setCodigoAgencia(String codigoAgencia) {

	}

	public String getNumDam() {
		return null;
	}

	public void setNumDam(String numDam) {

	}

	public int getSeqDuplicacao() {
		return 0;
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
		return null;
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
		return null;
	}

	public void setDataArrecadacao(Calendar dataArrecadacao) {

	}

	public Calendar getDataCredito() {
		return null;
	}

	public void setDataCredito(Calendar dataCredito) {

	}
/*
	public float getValorDocumento() {
		return 0;
	}

	public void setValorDocumento(float valorDocumento) {

	}
*/
	public float getValorPago() {
		return 0;
	}

	public void setValorPago(float valorPago) {

	}

	public String getFormaPagamento() {
		return null;
	}

	public void setFormaPagamento(String formaPagamento) {

	}

	public float getValorTarifa() {
		return 0;
	}

	public void setValorTarifa(float valorTarifa) {

	}

}
