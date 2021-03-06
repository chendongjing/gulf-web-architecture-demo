package cn.chmobile.ai.core.dto;

import java.io.Serializable;

public class RestResult<T> implements Serializable {
	
	private static final long serialVersionUID = -4185151304730685014L;

	private boolean success;

    private T data;

    private String error;

    public RestResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public RestResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

	@Override
	public String toString() {
		return "RestResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}

}
