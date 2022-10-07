package br.com.caixa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.caixa.model.Caixa;
import br.com.caixa.model.Movimentacao;

public class CaixaDao {

	static String creat = "insert into caixa(descricao, saldoinicial) values(?,?)";
	String read = "select * from caixa";
	String readPorId = "select * from caixa where id like ?";
	String update = "update caixa set descricao = ?, saldoinicial = ?, where id like ?";
	String delete = "delete from caixa where id like ?";
	static String deleteMovimento = "delete from movimentacao where idcaixa like ?";
	static String dropFkCaixa = "alter table movimentacao drop foreign key fk_caixa_mov";
	static String foreignKeyRestrict = "alter table movimentacao add constraint fk_caixa_mov foreign key (idcaixa) references caixa (id) on update restrict on delete restrict";
	static PreparedStatement pstm;
	static ResultSet rs;
	ArrayList<Caixa> listaCaixaAll;
	ArrayList<Caixa> listaCaixaPorCode;

	// metodo inserir dados
	public void inserir(Caixa caixa) {

		try {
			new ConectaMysql(); // chamada estatica da classe de conexao;

			pstm = ConectaMysql.conectar().prepareStatement(creat);

			pstm.setString(1, caixa.getDescricao());
			pstm.setDouble(2, caixa.getSaldoInicial());

			pstm.executeUpdate();

			System.out.println("CaixaDao: Metodo:inserir()* OK *"); // Debug;

		} catch (SQLException e) {
			System.out.println("erro" + e);

		} finally {
			try {
				pstm.close();

			} catch (SQLException e) {

				System.out.println(e);

			}

		}

	}

	// metodo listar dados
	public void listarTudo(Caixa caixa) {

		new ConectaMysql();
		listaCaixaAll = new ArrayList<Caixa>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(read);
			rs = pstm.executeQuery();

			while (rs.next()) {

				caixa.setDescricao(rs.getString("descricao"));
				caixa.setSaldoInicial(rs.getDouble("saldoinicial"));
				// System.out.println(caixa); //debug

				listaCaixaAll.add(caixa);
				System.out.println("CaixaDao: Metodo:listagem() *OK*"); // Debug;

				for (Caixa cx : listaCaixaAll) {
					System.out.println("Descricao: " + cx.getDescricao());
					System.out.println("Saldo Inicial: " + cx.getSaldoInicial());
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
	public void listarPorId(Caixa caixa) {

		new ConectaMysql();
		listaCaixaPorCode = new ArrayList<Caixa>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(readPorId);
			pstm.setInt(1, caixa.getId());
			rs = pstm.executeQuery();

			while (rs.next()) {

				caixa.setId(rs.getInt("id"));
				caixa.setDescricao(rs.getString("descricao"));
				caixa.setSaldoInicial(rs.getDouble("saldoinicial"));
				// System.out.println(caixa); //debug

				listaCaixaPorCode.add(caixa);
				System.out.println("CaixaDao: Metodo:listagem() *OK*"); // Debug;

				/*
				 * for (Caixa cx : listaCaixalAll) { System.out.println("id: " + cx.getId());
				 * System.out.println("Descricao: " + cx.getDescricao());
				 * System.out.println("Saldo Inicial: " + cx.getSaldoInicial()); }
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

	// Atualização de dados
	public void update(Caixa caixa) {

		new ConectaMysql();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(update);

			pstm.setString(1, caixa.getDescricao());
			pstm.setDouble(2, caixa.getSaldoInicial());
			pstm.setInt(3, caixa.getId());

			pstm.executeUpdate();

			System.out.println("Dados alterados *-*");

		} catch (SQLException e) {

			System.out.println(e);
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

	//Este metodo remove a FK para deletar dados e recria apos isso
	public void deletar(Caixa caixa) {

		new ConectaMysql();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(dropFkCaixa);
			pstm.execute();
			System.out.println("Drop FK *OK*");
			Thread.sleep(500);

			pstm = ConectaMysql.conectar().prepareStatement(delete);
			pstm.setInt(1, caixa.getId());
			pstm.execute();
			System.out.println("caixa excluido *OK*");
			Thread.sleep(500);

			Movimentacao mv = new Movimentacao();
			pstm = ConectaMysql.conectar().prepareStatement(deleteMovimento);
			/*caso tenha movimento vinculado ao caixa ele sera deletado junto
			//necessario apagar ou editar os movimentos antes de deletar registros no caixa,
			isso para evitar dados sem vinculo*/
			pstm.setInt(1, mv.getIdCaixa());
			pstm.execute();
			System.out.println("movimento excluido *OK*");
			Thread.sleep(1000);

			pstm = ConectaMysql.conectar().prepareStatement(foreignKeyRestrict);
			pstm.execute();
			System.out.println("FK recriada *OK*");

		} catch (SQLException | InterruptedException e) {

			e.printStackTrace();

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

	public static void main(String args[]) {

	}

}
