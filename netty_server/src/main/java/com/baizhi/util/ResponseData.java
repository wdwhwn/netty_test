package com.baizhi.util;

import java.io.Serializable;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class ResponseData implements Serializable{
    private Object result;
    private Exception exception;

    @Override
    public String toString() {
        return "ResponseData{" +
                "result=" + result +
                ", exception=" + exception +
                '}';
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseData(Object result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public ResponseData() {
    }

    public ResponseData(Exception exception) {

        this.exception = exception;
    }

    public ResponseData(Object result) {
        this.result = result;
    }
}
