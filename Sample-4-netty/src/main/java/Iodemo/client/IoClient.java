package Iodemo.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IoClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    while (true) {
                        try {
                            socket.getOutputStream().write((new Date() + "： hello server").getBytes());
                            Thread.sleep(2000);
                        } catch (Exception e) {

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {

            }
        }).start();
    }


}
