package com.example.springboot.common;

import com.example.springboot.threadlocal.WebContextHolder;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Description:
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/21
 */
public class MyResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private MyResponse<T>.Meta meta;
    private T data;

    public MyResponse() {
    }

    public MyResponse<T> success() {
        String sessionId = WebContextHolder.getSession().getId();
        this.meta = new MyResponse.Meta("SUCCESS", sessionId);
        return this;
    }

    public MyResponse<T> successWithSessionId(String sessionId) {
        this.meta = new MyResponse.Meta("SUCCESS", sessionId);
        return this;
    }

    public MyResponse<T> success(T data) {
        String sessionId = WebContextHolder.getSession().getId();
        this.meta = new MyResponse.Meta("SUCCESS", sessionId);
        this.data = data;
        return this;
    }

    public MyResponse<T> failure(String message) {
        HttpSession session = WebContextHolder.getSession();
        if (session == null) {
            this.meta = new MyResponse.Meta("FAIL", message, (String) null);
        } else {
            this.meta = new MyResponse.Meta("FAIL", message, session.getId());
        }

        return this;
    }

    public MyResponse<T> failure(String result, String message) {
        HttpSession session = WebContextHolder.getSession();
        if (session == null) {
            this.meta = new MyResponse.Meta(result, message, (String) null);
        } else {
            this.meta = new MyResponse.Meta(result, message, session.getId());
        }

        return this;
    }

    public MyResponse<T> exceptionFail(String result, String message) {
        this.meta = new MyResponse.Meta(result, message, (String) null);
        return this;
    }

    public MyResponse<T>.Meta getMeta() {
        return this.meta;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "RestResponse [meta=" + this.meta + ", data=" + this.data + "]";
    }

    class Meta implements Serializable {
        private String result;
        private String message;
        private String sessionId;

        public Meta(String result, String sessionId) {
            this.result = result;
            this.message = "ok";
            this.sessionId = sessionId;
        }

        public Meta(String result, String message, String sessionId) {
            this.result = result;
            this.message = message;
            this.sessionId = sessionId;
        }

        public String getResult() {
            return this.result;
        }

        public String getMessage() {
            return this.message;
        }

        public String getSessionId() {
            return this.sessionId;
        }

        @Override
        public String toString() {
            return "Meta [result=" + this.result + ", message=" + this.message + ", sessionId=" + this.sessionId + "]";
        }
    }
}
