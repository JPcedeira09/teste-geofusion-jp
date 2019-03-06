package br.com.geofusion.testegeofusionjp.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Concorrentes {

	private Long codigo;
	private String nome;
	private String categoria;
	private Character faixa_preco;
	private String endereco;
	private String municipio;
	private String uf;
	private Long codigo_bairro;
	
}
