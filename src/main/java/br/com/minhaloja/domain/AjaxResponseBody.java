package br.com.minhaloja.domain;

import java.io.Serializable;
import java.util.List;

public class AjaxResponseBody implements Serializable {
    private static final long serialVersionUID = 1L;

    String msg;
    List<?> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
