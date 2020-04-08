package Netty.domain.request;

import Netty.common.Packet;

import static Netty.common.Command.MESSAGE_REQUEST;

public class MessageRequestPacket extends Packet {

    private String message;

    private String toUserId;


    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String message, String toUserId) {
        this.message = message;
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
