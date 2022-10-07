package br.com.caixa.model;

public class Caixa {
		
	int id;
	String descricao;
	Double saldoInicial;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	@Override
	public String toString() {
		return "Caixa [id=" + id + ", descricao=" + descricao + ", saldoInicial=" + saldoInicial + "]";
	}
	

}
