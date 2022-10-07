package br.com.caixa.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.caixa.dao.MovimentoDao;
import br.com.caixa.model.Movimentacao;

public class CaixaView extends JFrame {

	public JLabel janeiro, fevereiro, marco, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro;
	public JPanel painel1 = new JPanel();
	
	
	public CaixaView() {
		setLayout(null);
		Font f = new Font("arial", Font.BOLD, 16);

		// ------------------------------------------------
		painel1.setLayout(null);
		// painel1.setBackground(Color.gray);
		painel1.setBounds(0, 0, 1000, 150);

		JLabel label = new JLabel("Abril/2015");
		label.setBounds(100, 60, 100, 20);
		label.setFont(f);
		painel1.add(label);

		// -- chamada de metodos de fora do construtor -----//
		addData();
		// ------------------------------------------------//

		JComboBox box1 = new JComboBox<>(new String[] { "selecione" });
		box1.setBounds(20, 10, 100, 20);
		painel1.add(box1);

		JLabel lblcaixa = new JLabel("Caixa");
		lblcaixa.setBounds(230, 60, 100, 20);
		painel1.add(lblcaixa);

		JComboBox box2 = new JComboBox<>(new String[] { "Selecione" });
		box2.setBounds(280, 60, 100, 20);
		painel1.add(box2);

		JButton btnCaixa = new JButton("+ Caixa");
		btnCaixa.setBounds(690, 60, 100, 40);
		btnCaixa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NovoMovimentoView();
				
			}
		});
		painel1.add(btnCaixa);

		JButton btnMov = new JButton("+ Movimento");
		btnMov.setBounds(800, 60, 150, 40);
		btnMov.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnMov) {
					pesquisar();
				}
				
			}
		});
		painel1.add(btnMov);

		add(painel1);

		// ------------------------------------------------
		JPanel painel2 = new JPanel();
		painel2.setLayout(null);
		// painel2.setBackground(Color.gray);
		painel2.setBounds(0, 151, 1000, 200);

		JPanel painelEntrada = new JPanel();
		painelEntrada.setLayout(null);
		// painelEntrada.setBackground(Color.yellow);
		painelEntrada.setBounds(20, 0, 450, 200);

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

		// -------------------------------------------------
		JPanel painelMov = new JPanel();
		painelMov.setLayout(null);
		// painelMov.setBackground(Color.yellow);
		painelMov.setBounds(510, 0, 450, 200);

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
		

		//-- Tabela do form ------------------------------------------//
		JPanel painel3 = new JPanel();
		painel3.setLayout(null);
		// painel3.setBackground(Color.gray);
		painel3.setBounds(0, 380, 1000, 400);

		JLabel lblTabela = new JLabel("Movimentos do mês:");
		lblTabela.setBounds(20, 0, 200, 20);
		painel3.add(lblTabela);

		JTable tabela = new JTable();
		tabela.setModel(new DefaultTableModel(

				new Object[][] { { null, null }, // linha 1
						{ null, null }, // linha 2

				},

				// nome das colunas da tabela
				new String[] { "Descricao", "Valor" }));

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setViewportView(tabela);
		scroll.setBounds(20, 50, 940, 200);
		painel3.add(scroll);

		add(painel3);

		//-- código de criacao do form --//
		setBounds(100, 50, 1000, 700);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(getOwner());

	}
	//
	//
	//
	//
	//
	//
	public void pesquisar() {
		
		MovimentoDao mDao2 = new MovimentoDao();
		//Movimentacao mv = new Movimentacao();
		mDao2.listarTudo();
		
		for (Movimentacao mo : mDao2.lista) {
			
			System.out.println("valor da lista todos: " + mDao2.lista);
		}
		
	}

	
	
	// Esse metodo adiciona os meses no topo do form e limpa o construtor da classe;
	public void addData() {

		janeiro = new JLabel();
		janeiro.setText("Janeiro");
		janeiro.setBounds(200, 0, 50, 20);
		janeiro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				janeiro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				janeiro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				janeiro.setForeground(Color.blue);

			}

		});
		painel1.add(janeiro);

		fevereiro = new JLabel("Fevereiro");
		fevereiro.setBounds(250, 0, 60, 20);
		fevereiro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				fevereiro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				fevereiro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				fevereiro.setForeground(Color.blue);

			}
		});
		painel1.add(fevereiro);

		marco = new JLabel("Março");
		marco.setBounds(320, 0, 60, 20);
		marco.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				marco.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				marco.setCursor(new Cursor(Cursor.HAND_CURSOR));
				marco.setForeground(Color.blue);

			}
		});
		painel1.add(marco);

		abril = new JLabel("Abril");
		abril.setBounds(370, 0, 60, 20);
		abril.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				abril.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				abril.setCursor(new Cursor(Cursor.HAND_CURSOR));
				abril.setForeground(Color.blue);

			}
		});
		painel1.add(abril);

		maio = new JLabel("Maio");
		maio.setBounds(420, 0, 60, 20);
		maio.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				maio.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				maio.setCursor(new Cursor(Cursor.HAND_CURSOR));
				maio.setForeground(Color.blue);

			}
		});
		painel1.add(maio);

		junho = new JLabel("Junho");
		junho.setBounds(470, 0, 60, 20);
		junho.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				junho.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				junho.setCursor(new Cursor(Cursor.HAND_CURSOR));
				junho.setForeground(Color.blue);

			}
		});
		painel1.add(junho);

		julho = new JLabel("Julho");
		julho.setBounds(520, 0, 60, 20);
		julho.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				julho.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				julho.setCursor(new Cursor(Cursor.HAND_CURSOR));
				julho.setForeground(Color.blue);

			}
		});
		painel1.add(julho);

		agosto = new JLabel("Agosto");
		agosto.setBounds(570, 0, 60, 20);
		agosto.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				agosto.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				agosto.setCursor(new Cursor(Cursor.HAND_CURSOR));
				agosto.setForeground(Color.blue);

			}
		});
		painel1.add(agosto);

		setembro = new JLabel("Setembro");
		setembro.setBounds(620, 0, 60, 20);
		setembro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				setembro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				setembro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				setembro.setForeground(Color.blue);

			}
		});
		painel1.add(setembro);

		outubro = new JLabel("Outubro");
		outubro.setBounds(690, 0, 60, 20);
		outubro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				outubro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				outubro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				outubro.setForeground(Color.blue);

			}
		});
		painel1.add(outubro);

		novembro = new JLabel("Novembro");
		novembro.setBounds(750, 0, 60, 20);
		novembro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				novembro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				novembro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				novembro.setForeground(Color.blue);

			}
		});
		painel1.add(novembro);

		dezembro = new JLabel("Dezembro");
		dezembro.setBounds(820, 0, 60, 20);
		dezembro.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				dezembro.setForeground(Color.black);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				dezembro.setCursor(new Cursor(Cursor.HAND_CURSOR));
				dezembro.setForeground(Color.blue);

			}
		});
		painel1.add(dezembro);

	}

	public static void main(String args[]) {

		new CaixaView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
