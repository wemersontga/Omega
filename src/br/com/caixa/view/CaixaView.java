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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wemerson Araujo;
 */

import javax.swing.JOptionPane;

public class CaixaView extends JFrame {
	

	    public CaixaView() {
	    	setLayout(null);
	    	Font f = new Font("arial", Font.BOLD, 16);
	    	
	    	//------------------------------------------------
	    	JPanel painel1 = new JPanel();
	    	painel1.setLayout(null);
	    	painel1.setBackground(Color.gray);
	    	painel1.setBounds(0,0,1000,150);
	    	
	    	
	    	JLabel label = new JLabel("Abril/2015");
	    	label.setBounds(150,60,100,20);
	    	painel1.add(label);
	    	
	    	JButton btnCaixa = new JButton("+ caixa");
	    	btnCaixa.setBounds(700,60,100,40);
	    	painel1.add(btnCaixa);
	    	
	    	JButton btnMov = new JButton("+ movimentação");
	    	btnMov.setBounds(820,60,150,40);
	    	painel1.add(btnMov);
	    	
	    	add(painel1);
	    	
	    	
	    	//------------------------------------------------
	    	JPanel painel2 = new JPanel();
	    	painel2.setLayout(null);
	    	//painel2.setBackground(Color.gray);
	    	painel2.setBounds(0,151,1000,200);
	    	
	    	JPanel painelEntrada = new JPanel();
	    	painelEntrada.setLayout(null);
	    	//painelEntrada.setBackground(Color.yellow);
	    	painelEntrada.setBounds(20,0,450,200);
	    	
	    	painelEntrada.setBorder(new TitledBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Entrada e saída do mês:")));
	    	
	    	JLabel lbEntrada = new JLabel("Entradas:");
	    	lbEntrada.setBounds(50, 30, 100, 20);
	    	painelEntrada.add(lbEntrada);
	    	
	    	JTextField txtEntrada = new JTextField("2000");
	    	txtEntrada.setBounds(280, 30, 150, 40);
	    	painelEntrada.add(txtEntrada);
	    	
	    	JLabel lbSaida = new JLabel("Saidas:");
	    	lbSaida.setBounds(50, 70, 100, 20);
	    	painelEntrada.add(lbSaida);
	    	
	    	JTextField txtSaida = new JTextField("1000");
	    	txtSaida.setBounds(280, 70, 150, 40);
	    	painelEntrada.add(txtSaida);
	    	
	    	JLabel lbDiv = new JLabel("______________________________________________________");
	    	lbDiv.setBounds(50, 120, 400, 20);
	    	painelEntrada.add(lbDiv);
	    	
	    	JLabel lbSaldo = new JLabel("Saldo:");
	    	lbSaldo.setBounds(50, 150, 500, 20);
	    	painelEntrada.add(lbSaldo);
	    	
	    	JTextField txtSaldo = new JTextField("4500");
	    	txtSaldo.setBounds(280, 150, 150, 40);
	    	painelEntrada.add(txtSaldo);
	    	
	    	painel2.add(painelEntrada);
	    	
	    	//-------------------------------------------------
	    	JPanel painelMov = new JPanel();
	    	painelMov.setLayout(null);
	    	//painelMov.setBackground(Color.yellow);
	    	painelMov.setBounds(510,0,450,200);
	    	
	    	painelMov.setBorder(new TitledBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Balanço Geral:")));
	    	
	    	JLabel lbEntradaMov = new JLabel("Entradas:");
	    	lbEntradaMov.setBounds(50, 30, 100, 20);
	    	painelMov.add(lbEntradaMov);
	    	
	    	JTextField txtEntradaMov = new JTextField("2000");
	    	txtEntradaMov.setBounds(280, 30, 150, 40);
	    	painelMov.add(txtEntradaMov);
	    	
	    	JLabel lbSaidaMov = new JLabel("Saidas:");
	    	lbSaidaMov.setBounds(50, 70, 100, 20);
	    	painelMov.add(lbSaidaMov);
	    	
	    	JTextField txtSaidaMov = new JTextField("1000");
	    	txtSaidaMov.setBounds(280, 70, 150, 40);
	    	painelMov.add(txtSaidaMov);
	    	
	    	JLabel lbDivMov = new JLabel("______________________________________________________");
	    	lbDivMov.setBounds(50, 120, 400, 20);
	    	painelMov.add(lbDivMov);
	    	
	    	JLabel lbSaldoMov = new JLabel("Saldo:");
	    	lbSaldoMov.setBounds(50, 150, 500, 20);
	    	painelMov.add(lbSaldoMov);
	    	
	    	JTextField txtSaldoMov = new JTextField("4500");
	    	txtSaldoMov.setBounds(280, 150, 150, 40);
	    	painelMov.add(txtSaldoMov);
	    	
	    	painel2.add(painelMov);
	    	
	    	add(painel2);
	    	//------------------------------------------------
	    	
	    	//Tabela------------------------------------------
	    	JPanel painel3 = new JPanel();
	    	painel3.setLayout(null);
	    	//painel3.setBackground(Color.gray);
	    	painel3.setBounds(0,380,1000,400);
	    	
	    	JLabel lblTabela = new JLabel("Tabela:");
	        lblTabela.setBounds(20, 0, 100, 20);
	        painel3.add(lblTabela);
	        
	        JTable tabela = new JTable();
	        tabela.setModel(new DefaultTableModel(
	                
	                new Object [][]{
	                    {null, null}, //linha 1 da tabela
	                    {null, null}, //linha 2
	                    {null, null}, //linha 3
	                    {null, null}, //linha 4
	                    {null, null}, //linha 5
	                    {null, null}, //linha 6
	                    {null, null}, //linha 7
	                    {null, null}, //linha 8
	                    
	                },
	                
	                //nome das colunas da tabela
	                new String[]{"Descricao", "Valor"}
	        ));
	        
	        JScrollPane scroll = new JScrollPane(tabela);
	        scroll.setViewportView(tabela);
	        scroll.setBounds(20, 50, 940, 200);
	        painel3.add(scroll);
	        
	        add(painel3);
	    	
	    	setBounds(100,50,1000,700);
	        setVisible(true);
	        setResizable(false);
	        setLocationRelativeTo(getOwner());
	    	
	    }
	    
	    
	    public static void main(String args[]) {
	        
	        new CaixaView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    }
	    
	}

