package controlador;

import conector.criptico;
import vista.Window;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;

public class CtrlWindow implements ActionListener{

    Window window;

    private String ipServer = "192.168.43.136";

    public CtrlWindow(Window window){
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().contentEquals("Enviar")){

            window.fieldChat.setText(window.fieldChat.getText() + "\n" + "Yo: " + window.textSend.getText());

            sendMessege();

            window.textSend.setText("");
        }

    }

    private void sendMessege(){
        try {
            System.setProperty("javax.net.ssl.keyStore", "./src/certs/clientKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "clientpass");
            System.setProperty("javax.net.ssl.trustStore", "./src/certs/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket(ipServer, 9090);

            OutputStream exit = socket.getOutputStream();

            criptico cipher = new criptico();   //Cifra el mensaje
            String code = cipher.encriptar(window.textSend.getText().toString());
            String cadena = window.listUsers.getSelectedItem().toString() +  ":" + code;
            exit.write(cadena.getBytes());

            exit.close();
            socket.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }



}
