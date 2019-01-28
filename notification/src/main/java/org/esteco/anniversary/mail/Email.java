package org.esteco.anniversary.mail;

public class Email {

    private String body;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Email instance = new Email();

        private Builder(){
        }

        public Builder from(String from){
            instance.from = from;
            return this;
        }

        public Builder to(String to){
            instance.to = to;
            return this;
        }

        public Builder cc(String cc){
            instance.cc = cc;
            return this;
        }

        public Builder body(String body){
            instance.body = body;
            return this;
        }

        public Builder subject(String subject){
            instance.subject = subject;
            return this;
        }

        public Email build(){
            return instance;
        }
    }
}
