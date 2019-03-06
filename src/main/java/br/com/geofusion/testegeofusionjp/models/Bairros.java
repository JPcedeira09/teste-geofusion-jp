package br.com.geofusion.testegeofusionjp.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Bairros {
	private Long codigo;
	private String nome;
	private String municipio;
	private String uf;
	private Double area;
}
