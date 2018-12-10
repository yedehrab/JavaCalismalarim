/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.SwingConstants;

/**
 *
 * @author Yunus Emre
 */
public class LabelFrame extends JFrame{
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    
    public LabelFrame(){
        super("Testing JLabel");
        setLayout(new FlowLayout());
        
        l1 = new JLabel("Label with text");
        l1.setToolTipText("This is l1");
        add(l1);
        
        Icon bug = new ImageIcon("galaxy.png");
        l2 = new JLabel("Label with text and icon", bug, SwingConstants.LEFT);
        l2.setToolTipText("This is l2");
        add(l2);
        
        l3 = new JLabel();
        l3.setText("Label with icon and text at bottom");
        l3.setIcon(bug);
        l3.setHorizontalTextPosition(SwingConstants.CENTER);
        l3.setVerticalTextPosition(SwingConstants.BOTTOM);
        l3.setToolTipText("This is label3");
        add(l3);
    }
    
    public static void menu(){
        // Main
        LabelFrame lf = new LabelFrame();
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lf.setSize(260, 180);
        lf.setVisible(true);
    }
    
}
