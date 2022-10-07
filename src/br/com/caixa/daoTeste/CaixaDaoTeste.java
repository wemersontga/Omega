package br.com.caixa.daoTeste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.caixa.dao.ConectaMysql;
import br.com.caixa.model.Caixa;

public class CaixaDaoTeste {

	static String creat = "insert into caixa(descricao, saldoinicial) values(?,?)";
	static String read = "select * from caixa";
	static String readPorId = "select * from caixa where id like ?";
	static String update = "update caixa set descricao = ?, saldoinicial = ? where id like ?";
	static String delete = "delete from caixa where id like ?";
	static String deleteMovimento = "delete from movimentacao where idcaixa like ?";
	static String dropFkCaixa = "alter table movimentacao drop foreign key fk_caixa_mov";
	static String foreignKeyRestrict = "alter table movimentacao add constraint fk_caixa_mov foreign key (idcaixa) references caixa (id) on update restrict on delete restrict";
	static PreparedStatement pstm;
	static ResultSet rs;
	static ArrayList<Caixa> listaCaixalAll;

	// teste metodo inserir dados
	public static void inserir() {

		new ConectaMysql(); // chamada estatica da classe de conexao;
		try {

			pstm = ConectaMysql.conectar().prepareStatement(creat);

			pstm.setString(1, "ola mundo");
			pstm.setDouble(2, 20200);

			pstm.executeUpdate();

			System.out.println("CaixaDao: Metodo:inserir()* OK *"); // Debug;

		} catch (SQLException e) {
			System.out.println("erro" + e);

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
	public static void listarTudo(Caixa caixa) {

		new ConectaMysql();
		listaCaixalAll = new ArrayList<Caixa>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(read);
			rs = pstm.executeQuery();

			while (rs.next()) {

				// Caixa caixa = new Caixa();
				caixa.setDescricao(rs.getString("descricao"));
				caixa.setSaldoInicial(rs.getDouble("saldoinicial"));
				// System.out.println(caixa); //debug

				listaCaixalAll.add(caixa);
				System.out.println("CaixaDao: Metodo:listagem() *OK*"); // Debug;

				for (Caixa cx : listaCaixalAll) {
					System.out.println("Descricao: " + cx.getDescricao());
					System.out.println("Saldo Inicial: " + cx.getSaldoInicial());
				}

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
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
	public static void listarPorId(Caixa caixa) {

		new ConectaMysql();
		listaCaixalAll = new ArrayList<Caixa>();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(readPorId);
			pstm.setInt(1, 2);
			rs = pstm.executeQuery();

			while (rs.next()) {

				// Caixa caixa = new Caixa();
				caixa.setId(rs.getInt("id"));
				caixa.setDescricao(rs.getString("descricao"));
				caixa.setSaldoInicial(rs.getDouble("saldoinicial"));
				// System.out.println(caixa); //debug

				listaCaixalAll.add(caixa);
				System.out.println("CaixaDao: Metodo:listagem() *OK*"); // Debug;

				for (Caixa cx : listaCaixalAll) {
					System.out.println("id: " + cx.getId());
					System.out.println("Descricao: " + cx.getDescricao());
					System.out.println("Saldo Inicial: " + cx.getSaldoInicial());
				}

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {

				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexão Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}

	}

	//Atualização de dados
	public static void update() {

		new ConectaMysql();
		try {
			pstm = ConectaMysql.conectar().prepareStatement(update);

			pstm.setString(1, "aabb");
			pstm.setDouble(2, 5.000);
			pstm.setInt(3, 1);

			pstm.executeUpdate();

			System.out.println("Dados alterados *-*");

		} catch (SQLException e) {

			System.out.println(e);
		}
		finally {
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
        try{
        	pstm = ConectaMysql.conectar().prepareStatement(dropFkCaixa);
        	pstm.execute();
        	System.out.println("Drop FK *OK*");
        	Thread.sleep(1000);
        	
        	pstm = ConectaMysql.conectar().prepareStatement(delete);
            pstm.setInt(1, 3);
            pstm.execute();
            System.out.println("caixa excluido *OK*");
            Thread.sleep(1000);
            
            pstm = ConectaMysql.conectar().prepareStatement(deleteMovimento);
            pstm.setInt(1, 3);
            pstm.execute();
            System.out.println("movimento excluido *OK*");
            Thread.sleep(1000);
            
            pstm = ConectaMysql.conectar().prepareStatement(foreignKeyRestrict);
            pstm.execute();
            System.out.println("FK recriada *OK*");
            
        }catch(SQLException | InterruptedException e){
            
            e.printStackTrace();
            
        }
        finally {
			try {
				pstm.close();
				ConectaMysql.con.close();
				System.out.println("Conexão Fechada!!!");

			} catch (SQLException ex) {

				System.out.println("Erro ao Fechar a Conexao!!!" + ex.getMessage());

			}

		}
		
	}

	public static void main(String[] args) {

		Caixa caixa = new Caixa();
		// inserir(caixa);
		// listarTudo(caixa);
		//listarPorId(caixa);
		//update();
		deletar();

	}

}
