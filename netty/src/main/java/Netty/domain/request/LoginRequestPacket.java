package Netty.domain.request;

import Netty.common.Command;
import Netty.common.Packet;

public class LoginRequestPacket extends Packet implements Command {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }

    public LoginRequestPacket setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginRequestPacket setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequestPacket setPassword(String password) {
        this.password = password;
        return this;
    }

}
