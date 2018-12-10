/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Yunus Emre
 */
public class ButtonFrame extends JFrame{
    private JButton pb, fb;
    
    public static void menu(){
        ButtonFrame bf = new ButtonFrame();
        bf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bf.setSize(250, 150);
        bf.setVisible(true);
    }
    
    public ButtonFrame(){
        super("Testing button");
        setLayout(new FlowLayout());
        
        pb = new JButton("Sıkıcı buton");
        add(pb);
        
        Icon img1 = new ImageIcon("galaxy.png");
        Icon img2 = new ImageIcon("cancel.png");
        fb = new JButton("Süslü buton", img1);
        fb.setRolloverIcon(img2);
        add(fb);
        
        pb.addActionListener(new ButtonHandler());
        fb.addActionListener(new ButtonHandler());
        
        
        
    }
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(
                    ButtonFrame.this,
                    String.format("Şuna bastın: %s", e.getActionCommand())
                    );
        }
    }
}
