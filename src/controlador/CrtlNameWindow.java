package controlador;

import vista.NameWindow;
import vista.Window;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;

public class CrtlNameWindow implements ActionListener{

    private String ipServer = "192.168.1.56";

    private NameWindow NameWindow;

    public CrtlNameWindow(NameWindow NameWindow){
        this.NameWindow = NameWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contentEquals("Accept")){
            if (!NameWindow.jtfUser.getText().equals("")){

                connectToServer();

                Window window = new Window();
                NameWindow.setVisible(false);
            }
        }
    }

    private void connectToServer(){
        try {
            System.setProperty("javax.net.ssl.keyStore", "./src/certs/clientKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "clientpass");
            System.setProperty("javax.net.ssl.trustStore", "./src/certs/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket(ipServer, 9090);

            OutputStream salida = socket.getOutputStream();
            salida.write(NameWindow.jtfUser.getText().getBytes());

            salida.close();
            socket.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
