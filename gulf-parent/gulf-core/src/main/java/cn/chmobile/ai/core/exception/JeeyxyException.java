package cn.chmobile.ai.core.exception;

public class JeeyxyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JeeyxyException() {
		super();
	}

	public JeeyxyException(String message, Throwable t) {
		super(message, t);
	}

	public JeeyxyException(String message) {
		super(message);
	}

	public JeeyxyException(Throwable t) {
		super(t);
	}
}