package br.com.geofusion.testegeofusionjp.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Potencial {

	private Long CODIGO;
	private Integer QTD_AGENCIAS;
	private Integer EMPRESAS;
	private Integer EMPREGADOS;
	private Character RENDA;
	private Double FATURAMENTO;
}
