package br.com.minhaloja.domain;

import java.io.Serializable;
import java.util.List;

public class AjaxResponseBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String msg;
    private List<T> result;
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
