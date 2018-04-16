package cn.chmobile.ai.core.exception;

public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -4038571140163001132L;

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
}

