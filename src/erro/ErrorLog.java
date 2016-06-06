package erro;

import java.util.Calendar;

public class ErrorLog {

	private Calendar timeError;
	private Exception error;
	private String stringErro;
	
	public ErrorLog(Calendar timeError, Exception e) {
		this.timeError = timeError;
		this.error = e;
		
		StringBuilder message = new StringBuilder();
		message.append("┬");
		message.append("|Data e hora do erro: " + this.timeError.getTime() + "\r\n");
		message.append("|Mensagem informada pela exceção é:\r\n");
		message.append("|" + this.error.getMessage() + "\r\n");
		message.append("|Pilha do erro:\r\n");
		message.append("|" + this.error.toString() + "\r\n");
		message.append("┴");
		
		this.stringErro = message.toString();
		
	}
	
	@Override
	public String toString(){
		return this.stringErro;
		
	}

}
