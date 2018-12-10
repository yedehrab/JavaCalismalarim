/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Yunus Emre
 */
public class TextFieldFrame extends JFrame{
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JPasswordField pf;
    
    public static void menu(){
        TextFieldFrame tff = new TextFieldFrame();
        tff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tff.setSize(350, 100);
        tff.setVisible(true);
        
    }
    
    public TextFieldFrame(){
        super("Testing JTextFields");
        setLayout(new FlowLayout());
        
        tf1 = new JTextField(10);
        tf1.addActionListener(new TextFieldHandler());
        add(tf1);
        
        tf2 = new JTextField("Enter text here");
        add(tf2);
        
        tf3 = new JTextField("Uneditable text field", 21);
        tf3.setEditable(false);
        add(tf3);
        
        pf = new JPasswordField("Hidden text");
        add(pf);
    }
    
    private class TextFieldHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = "";
            
            if (e.getSource().equals(tf1)){
                text = String.format("tf1: %s", e.getActionCommand());
            }
            
            JOptionPane.showMessageDialog(null, text);
        }
        
    }
}
