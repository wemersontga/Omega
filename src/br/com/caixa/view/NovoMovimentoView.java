package br.com.caixa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.caixa.dao.CaixaDao;
import br.com.caixa.dao.MovimentoDao;
import br.com.caixa.model.Movimentacao;

public class NovoMovimentoView extends JFrame {

	JComboBox<String> boxIdCaixa;
	JComboBox boxTipo;
	
	public NovoMovimentoView() {
		super("+Movimento");
		setLayout(null);
		Font f = new Font("arial", Font.BOLD, 16);
		
		JLabel lbIdCaixa = new JLabel("IdCaixa");
		lbIdCaixa.setBounds(20,30,100,20);
		add(lbIdCaixa);
		
		
		
		boxIdCaixa = new JComboBox<>(new String[] { "Selecione", "4"});
		boxIdCaixa.setForeground(Color.red);
		//boxIdCaixa.setFont(f);
		boxIdCaixa.setBounds(20, 50, 120, 20);
		//boxIdCaixa.setEnabled(false);
		// box.setEditable(false);
		add(boxIdCaixa);
		//-----------------------------------
		JLabel lbData = new JLabel("Data");
		lbData.setBounds(20,80,100,20);
		add(lbData);
		
		JTextField txtData = new JTextField();
		txtData.setBounds(20,100,100,20);
		add(txtData);
		//----------------------------------
		JLabel lbTipo = new JLabel("Tipo");
		lbTipo.setBounds(20,130,100,20);
		add(lbTipo);
		
		boxTipo = new JComboBox<>(new String[] { "Selecione", "E", "S"});
		boxTipo.setForeground(Color.red);
		//boxIdCaixa.setFont(f);
		boxTipo.setBounds(20, 150, 100, 20);
		//boxIdCaixa.setEnabled(false);
		// box.setEditable(false);
		add(boxTipo);
		//-----------------------------------
		JLabel lbCaixa = new JLabel("Caixa");
		lbCaixa.setBounds(20,180,100,20);
		add(lbCaixa);
		
		JTextField txtCaixa = new JTextField();
		txtCaixa.setBounds(20,200,150,20);
		add(txtCaixa);
		//------------------------------------
		JLabel lbValor = new JLabel("Valor");
		lbValor.setBounds(20,230,100,20);
		add(lbValor);
		
		JTextField txtValor = new JTextField();
		txtValor.setBounds(20,250,100,20);
		add(txtValor);
		//------------------------------------
		JLabel lbDescricao = new JLabel("Descrição");
		lbDescricao.setBounds(20,280,100,20);
		add(lbDescricao);
		
		JTextField txtDescricao = new JTextField();
		txtDescricao.setBounds(20,300,200,60);
		add(txtDescricao);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(80,370,80,30);
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSalvar) {
					
					if(txtCaixa.getText().equals("") || txtData.getText().equals("")
							|| txtDescricao.getText().equals("")  || txtValor.getText().equals("")
							|| boxIdCaixa.getSelectedItem() == "Selecione"
							|| boxTipo.getSelectedItem() == "Selecione"){
						
		                JOptionPane.showMessageDialog(rootPane, "Verifique campos vazios!!!");
		                
		            }else {
		            	
		            	Movimentacao mv = new Movimentacao();
		            	mv.setIdCaixa(Integer.parseInt(boxIdCaixa.getSelectedItem()+""));
		            	mv.setData(txtData.getText());
		            	mv.setTipo(boxTipo.getSelectedItem()+"");
		            	mv.setCaixa(txtCaixa.getText());
		            	mv.setDescricao(txtDescricao.getText());
		            	mv.setValor(Double.parseDouble(txtValor.getText()));
		            	
		            	MovimentoDao mvDao = new MovimentoDao();
		            	mvDao.inserir(mv);
		            	
		            	JOptionPane.showMessageDialog(rootPane, "Dados Salvos!!!");
		            }
					
					
				}
				
			}
		});
		add(btnSalvar);

		
		// -- codigo de criacao do form --//
		setBounds(100, 50, 260, 450);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(getOwner());

	}

	public static void main(String[] args) {

		new NovoMovimentoView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

