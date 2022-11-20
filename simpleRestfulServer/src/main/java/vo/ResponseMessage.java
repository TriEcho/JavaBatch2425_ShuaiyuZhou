package com.antra.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {
    private String message;

    @JsonProperty("data")
    private Object body;

    public ResponseMessage(){

    }
    public ResponseMessage(String msg){
        this.message = msg;
    }
    public ResponseMessage(String msg, Object obj){
        this.message = msg;
        this.body = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
