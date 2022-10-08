package br.com.caixa.daoTeste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.caixa.dao.ConectaMysql;
import br.com.caixa.model.Movimentacao;

public class MovimentoDaoTeste {

	static String creat = "insert into movimentacao(idcaixa, data, tipo, caixa, descricao, valor) values(?,?,?,?,?,?)";
	static String read = "select * from movimentacao";
	static String readPorId = "select * from movimentacao where id like ?";
	static String update = "update movimentacao set idcaixa = ?, data = ?, tipo = ?, caixa = ?, descricao = ?, valor = ?  where id like ?";
	static String delete = "delete from movimentacao where id like ?";
	static PreparedStatement pstm;
	static ResultSet rs;
	static ArrayList<Movimentacao> listaMovlAll;

	// teste metodo inserir dados
	public static void inserir() {

		new ConectaMysql(); // chamada estatica da classe de conexao;
		try {

			pstm = ConectaMysql.conectar().prepareStatement(creat);

			pstm.setInt(1, 4);
			pstm.setString(2, "06/10/2022");
			pstm.setString(3, "E");
			pstm.setString(4, "itau");
			pstm.setString(5, "itau");
			pstm.setDouble(6, 2000);

			pstm.executeUpdate();

			System.out.println("MovimentoDao: Metodo:inserir()* OK *"); // Debug;

		} catch (SQLException e) {
			System.out.println("erro" + e);

		} finally {
			try {

				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexao Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	// Teste metodo listar dados
	public static void listarTudoTeste() {

		new ConectaMysql();
		listaMovlAll = new ArrayList<Movimentacao>();
		Movimentacao mv = new Movimentacao();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(read);
			rs = pstm.executeQuery();

			while (rs.next()) {

				mv.setId(rs.getInt("id"));
				mv.setIdCaixa(rs.getInt("idcaixa"));
				mv.setData(rs.getString("data"));
				mv.setTipo(rs.getString("tipo"));
				mv.setCaixa(rs.getString("caixa"));
				mv.setDescricao(rs.getString("descricao"));
				mv.setValor(rs.getDouble("valor"));
				// System.out.println(mv); //debug

				listaMovlAll.add(mv);
				System.out.println("MovimentoDaoTeste: Metodo:listagem() *OK*"); // Debug;
				//System.out.println("lista: " +listaMovlAll);
				
				for (Movimentacao mto : listaMovlAll) {
					System.out.println("ID: " + mto.getId());
					System.out.println("idCaixa: " + mto.getIdCaixa());
					System.out.println("Data: " + mto.getData());
					System.out.println("tipo: " + mto.getTipo());
					System.out.println("Caixa: " + mto.getCaixa());
					System.out.println("Descricao: " + mto.getDescricao());
					System.out.println("Valor: " + mto.getValor());
				}

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {

				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexão Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	// Teste metodo listar dados
	public static void listarPorId(Movimentacao mv) {

		new ConectaMysql();
		listaMovlAll = new ArrayList<Movimentacao>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(readPorId);
			pstm.setInt(1, 4);
			rs = pstm.executeQuery();

			while (rs.next()) {

				mv.setId(rs.getInt("id"));
				mv.setIdCaixa(rs.getInt("idcaixa"));
				mv.setData(rs.getString("data"));
				mv.setTipo(rs.getString("tipo"));
				mv.setCaixa(rs.getString("caixa"));
				mv.setDescricao(rs.getString("descricao"));
				mv.setValor(rs.getDouble("valor"));
				// System.out.println(mv); //debug

				listaMovlAll.add(mv);
				System.out.println("MovimentoDaoTeste: Metodo:listagem() *OK*"); // Debug;

				for (Movimentacao mto : listaMovlAll) {
					System.out.println("ID: " + mto.getId());
					System.out.println("idCaixa: " + mto.getIdCaixa());
					System.out.println("Data: " + mto.getData());
					System.out.println("tipo: " + mto.getTipo());
					System.out.println("Caixa: " + mto.getCaixa());
					System.out.println("Descricao: " + mto.getDescricao());
					System.out.println("Valor: " + mto.getValor());
				}

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {

				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexão Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	public static void update() {

		new ConectaMysql();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(update);

			pstm.setInt(1, 5);
			pstm.setString(2, "12/06/2022");
			pstm.setString(3, "tipo");
			pstm.setString(4, "Inter");
			pstm.setString(5, "teste");
			pstm.setDouble(6, 6000);
			pstm.setInt(7, 4);

			pstm.executeUpdate();

			System.out.println("Dados alterados *-*");

		} catch (SQLException e) {

			System.out.println(e);
		} finally {
			try {

				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexão Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	public static void deletar() {

		new ConectaMysql();
		try {

			pstm = ConectaMysql.conectar().prepareStatement(delete);
			pstm.setInt(1, 4);
			pstm.execute();
			System.out.println("movimento excluido *OK*");
			

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try {
				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexao Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	public static void main(String[] args) {
		Movimentacao m = new Movimentacao();

		// inserir();
		listarTudoTeste();
		// listarPorId(m);
		//update();
		//deletar();
	}

}
