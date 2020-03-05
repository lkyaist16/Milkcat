package Netty.domain.response;

import Netty.common.Command;
import Netty.common.Packet;

public class LoginResponsePacket extends Packet implements Command {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public LoginResponsePacket setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
