package br.com.geofusion.testegeofusionjp.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventosSemanais {
	
	private Double codigo_concorrente;
	private Integer transacoesDiaDaSemana;
	private Integer diaDaSemana;
	
	public EventosSemanais(Double codigo_concorrente, Integer transacoesDiaDaSemana, Integer diaDaSemana) {
		super();
		this.codigo_concorrente = codigo_concorrente;
		this.transacoesDiaDaSemana = transacoesDiaDaSemana;
		this.diaDaSemana = diaDaSemana;
	}
	
	
}
