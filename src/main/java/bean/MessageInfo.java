package bean;

public class MessageInfo {
    private Integer messageId;
    private String  messageTitile;
    private String  messageContent;
    private  String messageConnect;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitile() {
        return messageTitile;
    }

    public void setMessageTitile(String messageTitile) {
        this.messageTitile = messageTitile;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageConnect() {
        return messageConnect;
    }

    public void setMessageConnect(String messageConnect) {
        this.messageConnect = messageConnect;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }

    private Integer messageState;

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageTitile='" + messageTitile + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageConnect='" + messageConnect + '\'' +
                ", messageState=" + messageState +
                '}';
    }
}
