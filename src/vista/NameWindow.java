package vista;

import controlador.CrtlNameWindow;

import javax.swing.*;
import java.awt.*;

public class NameWindow extends JFrame{

    private JPanel panel;
    private JLabel jlUser;
    public JTextField jtfUser;
    private JButton accept;

    public NameWindow(){
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        crearPanel();
        add(panel);
        setVisible(true);
        setTitle("Client");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    private void crearPanel(){
        panel = new JPanel();
        jlUser = new JLabel("User: ");
        jtfUser = new JTextField();
        accept = new JButton("Accept");

        panel.setLayout(new GridLayout(0,1,2,5));
        panel.add(jlUser);
        panel.add(jtfUser);
        panel.add(accept);

        accept.addActionListener(new CrtlNameWindow(this));
    }

}
