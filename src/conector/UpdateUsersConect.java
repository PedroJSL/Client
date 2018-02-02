package conector;

import vista.Window;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UpdateUsersConect extends Thread {

    Window window;
    private int port = 4444;

    public UpdateUsersConect(Window window) {
        this.window = window;
    }

    @Override
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(4444);
            InetAddress group = InetAddress.getByName("225.10.10.11");
            socket.joinGroup(group);

            //String mens;

            while (true) {
                byte[] buf = new byte[100000];
                DatagramPacket paquete = new DatagramPacket(buf, buf.length);
                socket.receive(paquete);

                String mens = new String(paquete.getData()).trim();
                System.out.println("Recibido " + mens);
                System.out.println(mens.length());
                getUsers(mens.trim());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getUsers(String users) {
        System.out.println("te doy usuarios");
        window.listUsers.removeAll();
        String[] user = users.split(" ");
        for (int i = 0; i < user.length; i++) {
            window.listUsers.addItem(user[i]);
        }
    }


}
