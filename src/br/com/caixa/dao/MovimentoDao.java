package br.com.caixa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caixa.model.Movimentacao;

public class MovimentoDao {

	public static String creat = "insert into movimentacao(idcaixa, data, tipo, caixa, descricao, valor) values(?,?,?,?,?,?)";
	public static String read = "select * from movimentacao";
	public static String readPorId = "select * from movimentacao where id like ?";
	public static String update = "update movimentacao set idcaixa = ?, data = ?, tipo = ?, caixa = ?, descricao = ?, valor = ?  where id like ?";
	public static String delete = "delete from movimentacao where id like ?";
	public static PreparedStatement pstm;
	public static ResultSet rs;
	public ArrayList<Movimentacao> listaMovlAll;
	public ArrayList<Movimentacao> listaMovPorCode;
	public List<Movimentacao> lista = new ArrayList<>();

	// metodo inserir dados
	public void inserir(Movimentacao mv) {

		new ConectaMysql(); // chamada estatica da classe de conexao;
		try {

			pstm = ConectaMysql.conectar().prepareStatement(creat);

			pstm.setInt(1, mv.getIdCaixa());
			pstm.setString(2, mv.getData());
			pstm.setString(3, mv.getTipo());
			pstm.setString(4, mv.getCaixa());
			pstm.setString(5, mv.getDescricao());
			pstm.setDouble(6, mv.getValor());

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

	// metodo listar dados
	public void listarTudo() {

		new ConectaMysql();
		listaMovlAll = new ArrayList<>();
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
				System.out.println("mv: " + mv); //debug

				listaMovlAll.add(mv);
				System.out.println("MovimentoDaoTeste: Metodo:listagem() *OK*"); // Debug;
				
				lista.add(mv);
				
				 for (Movimentacao mto : listaMovlAll) {
					 System.out.println("ID: " + mto.getId()); System.out.println("idCaixa: " + mto.getIdCaixa());
				 System.out.println("Data: " + mto.getData()); System.out.println("tipo: " +
				 mto.getTipo()); System.out.println("Caixa: " + mto.getCaixa());
				 System.out.println("Descricao: " + mto.getDescricao());
				 System.out.println("Valor: " + mto.getValor()); }
				 

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

	// metodo listar dados
	public void listarPorId(Movimentacao mv) {

		new ConectaMysql();
		listaMovPorCode = new ArrayList<Movimentacao>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(readPorId);
			pstm.setInt(1, mv.getId());
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

				listaMovPorCode.add(mv);
				System.out.println("MovimentoDaoTeste: Metodo:listagem() *OK*"); // Debug;

				/*
				 * for (Movimentacao mto : listaMovlAll) { System.out.println("ID: " +
				 * mto.getId()); System.out.println("idCaixa: " + mto.getIdCaixa());
				 * System.out.println("Data: " + mto.getData()); System.out.println("tipo: " +
				 * mto.getTipo()); System.out.println("Caixa: " + mto.getCaixa());
				 * System.out.println("Descricao: " + mto.getDescricao());
				 * System.out.println("Valor: " + mto.getValor()); }
				 */

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

	// metodo atualizar movimento
	public void update(Movimentacao mv) {

		new ConectaMysql();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(update);

			pstm.setInt(1, mv.getIdCaixa());
			pstm.setString(2, mv.getData());
			pstm.setString(3, mv.getTipo());
			pstm.setString(4, mv.getCaixa());
			pstm.setString(5, mv.getDescricao());
			pstm.setDouble(6, mv.getValor());

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

	// metodo que exclui dados de movimento
	public static void deletar(Movimentacao mv) {

		new ConectaMysql();
		try {

			pstm = ConectaMysql.conectar().prepareStatement(delete);
			pstm.setInt(1, mv.getId());
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
	

}
