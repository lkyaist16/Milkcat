package com.example.springcloudstream.outbound;

import lombok.Data;

@Data
public class SendMsg{

    private Integer msgId;

    private String content;

    @Override
    public String toString() {
        return "SendMsg{" +
                "msgId=" + msgId +
                ", content='" + content + '\'' +
                '}';
    }

}
