package exceptions;

public class DadosEntradaException extends RuntimeException {

	public DadosEntradaException(String message) {
		super(message);
	}

	public DadosEntradaException(String message, Throwable cause) {
		super(message, cause);
	}
}
