package Netty.domain.response;

import Netty.common.Packet;

import static Netty.common.Command.MESSAGE_RESPONSE;

public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;


    public MessageResponsePacket() {
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
