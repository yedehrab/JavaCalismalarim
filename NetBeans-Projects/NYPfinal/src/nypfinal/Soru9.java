/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypfinal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 *
 * @author Yunus Emre
 */
public class Soru9 extends JFrame implements ActionListener{
    private JTextField tf1; // Ad Soyad
    private JTextPane tp1; // ad yazma
    private JTextField tf3; // yaş
    private JTextPane tp2; // yaş yazma
    
    private ButtonGroup bg; // Radio buton bağlantısı
    private JRadioButton rb1; // bay 
    private JRadioButton rb2; // Bayan
    
    private JComboBox jlist; // liste
    private String[] list; // liste elemanları
    private static int SIZE_LIST = 100; // eleman sayısı
    private int index = 0;
    
    private JButton b1; // Ekle
    private JButton b2; // Sil
    
    public Soru9(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        tf1 = new JTextField();
        tf1.setText("Ad Soyad");
        tf1.setEditable(false);
        this.add(tf1);
        
        tp1 = new JTextPane();
        tp1.setText("");
        tp1.setToolTipText("Adınızı buraya yazınız");
        this.add(tp1);
        
        rb1 = new JRadioButton();
        rb1.setText("Bay");
        this.add(rb1);
        
        tf3 = new JTextField();
        tf3.setText("Yaş");
        tf3.setEditable(false);
        this.add(tf3);
        
        tp2 = new JTextPane();
        tp2.setText("");
        tp2.setToolTipText("Yaşınızı buraya yazınız");
        tp2.setSize(100, 10);
        this.add(tp2);
        
        rb2 = new JRadioButton();
        rb2.setText("Bayan");
        this.add(rb2);
        
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        
        list = new String[SIZE_LIST];
        jlist = new JComboBox(list);
        jlist.setToolTipText("Kayıt listesi");
        jlist.setMaximumRowCount(5);
        add(jlist);
        
        
        b1 = new JButton("Ekle");
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Sil");
        b2.addActionListener(this);
        add(b2);
        
        
    }
    
    public static void menu(){
        Soru9 s = new Soru9();
        s.setSize(350, 150);
        s.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(b1)){
            list[index++] = String.format("%d %s %s %s",
                    index,
                    tp1.getText(),
                    tp2.getText(),
                    rb1.isSelected() ? "Bay" : "Bayan"
            );
        }
        else if (e.getSource().equals(b2) && jlist.getSelectedIndex() > -1){
            list[jlist.getSelectedIndex()] = "";
        }
        
        jlist = new JComboBox(list);
    }
}
