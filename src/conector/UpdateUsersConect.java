package conector;

import vista.Window;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UpdateUsersConect extends Thread{

    Window window;
    private int port = 4444;

    public UpdateUsersConect(Window window){
        this.window = window;
    }

    @Override
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(port);
            InetAddress group = InetAddress.getByName("224.0.0.1");
            socket.joinGroup(group);
            String mens;

            while (true){

                byte[] packetByte = new byte[1000];
                DatagramPacket packetReciv = new DatagramPacket(packetByte, packetByte.length);
                socket.receive(packetReciv);

                mens = new String(packetReciv.getData());
                System.out.println(mens.trim());
                getUsers(mens.trim());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getUsers(String users){
        window.listUsers.removeAll();
        String[] user = users.split(" ");
        for (int i = 0;i < user.length;i++){
            window.listUsers.addItem(user[i]);
            System.out.println("Llegué a getUsers y esta estos son los usuarios"+ user[i]);
        }
    }


}
