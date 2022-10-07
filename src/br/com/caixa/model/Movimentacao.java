package br.com.caixa.model;

public class Movimentacao {

	int id;
	int idCaixa;
	String data;
	String tipo;
	String caixa;
	String descricao;
	Double valor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCaixa() {
		return idCaixa;
	}
	public void setIdCaixa(int idCaixa) {
		this.idCaixa = idCaixa;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCaixa() {
		return caixa;
	}
	public void setCaixa(String caixa) {
		this.caixa = caixa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "movimentacao [id=" + id + ", idCaixa=" + idCaixa + ", data=" + data + ", tipo=" + tipo + ", caixa="
				+ caixa + ", descricao=" + descricao + ", valor=" + valor + "]";
	}

	
}
