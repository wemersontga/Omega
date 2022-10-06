package br.com.caixa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Wemerson Araujo;
 */

import javax.swing.JOptionPane;

public class CaixaView extends JFrame {
	
	

	    public CaixaView() {
	    	setLayout(null);
	    	Font f = new Font("arial", Font.BOLD, 16);
	    	
	    	JPanel painel1 = new JPanel();
	    	JLabel label = new JLabel("Abril/2015");
	    	label.setBounds(150,60,100,20);
	    	add(label);
	    	
	    	JButton btnCaixa = new JButton("+ caixa");
	    	btnCaixa.setBounds(700,60,100,40);
	    	add(btnCaixa);
	    	
	    	JButton btnMov = new JButton("+ movimentação");
	    	btnMov.setBounds(820,60,150,40);
	    	add(btnMov);
	    	
	    	setBounds(100,50,1000,580);
	        setVisible(true);
	        setResizable(false);
	        setLocationRelativeTo(getOwner());
	    	
	    }
	    
	    
	    public static void main(String args[]){
	        
	        new CaixaView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    }
	    
	}

