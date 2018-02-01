package conector;

import vista.Window;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {

    Window window;

    public Listener(Window window){
        this.window = window;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverClient = new ServerSocket(9091);
            Socket socketClient = null;

            while (true){
                socketClient = serverClient.accept();
                InputStream entry = socketClient.getInputStream();

                String received = String.valueOf(entry.read());
                String[] word = received.split(":");
                window.fieldChat.append("\n" + word[0] + ": ");

                criptico cipher = new criptico();
                String nWord = cipher.desencriptar(word[1]);

                window.fieldChat.append(nWord);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
