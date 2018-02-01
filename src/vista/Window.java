package vista;

import conector.Listener;
import conector.UpdateUsersConect;
import controlador.CtrlWindow;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private JPanel panelMain, panel1, panel2, panel3, panel4;

    public JButton buttonSend;
    public JTextField textSend;
    public JScrollPane scroll;
    public JTextArea fieldChat;
    public JComboBox listUsers;

    public Window(){
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        createVentanaMain();
        add(panelMain);
        setVisible(true);
        setTitle("Client");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        UpdateUsersConect uuc = new UpdateUsersConect(this);
        uuc.start();
        Listener listener = new Listener(this);
        listener.start();
    }

    private void createVentanaMain(){
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        createPanel1();
        createPanel2();
        createPanel3();
        createPanel4();

        panelMain.add(panel1);
        panelMain.add(panel2);
        panelMain.add(panel3);
        panelMain.add(panel4);

    }

    private void createPanel1(){
        panel1 = new JPanel();
        listUsers = new JComboBox();

        panel1.setLayout(new GridLayout(0,1));
        panel1.add(listUsers);
    }

    private void createPanel2(){
        panel2 = new JPanel();
        fieldChat = new JTextArea(10,20);

        scroll = new JScrollPane(fieldChat);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fieldChat.setEditable(false);

        panel2.setLayout(new GridLayout(0,1));
        panel2.add(scroll);
    }


    private void createPanel3(){
        panel3 = new JPanel();
        textSend = new JTextField();

        panel3.setLayout(new GridLayout(1,1));
        panel3.add(textSend);
    }

    private void createPanel4(){
        panel4 = new JPanel();
        buttonSend = new JButton("Enviar");

        panel4.add(buttonSend);

        buttonSend.addActionListener(new CtrlWindow(this));
    }

}
