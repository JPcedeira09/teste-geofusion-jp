package br.com.geofusion.testegeofusionjp.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geofusion.testegeofusionjp.controller.RulesController;

@RestController
@RequestMapping("/api/teste/geofusion")
public class RegrasAPI {

	@Autowired
	private RulesController rules;

	@GetMapping("/densidadeDemografica")
	public void densidadeDemografica() {
		rules.densidadeDemografica();
	}

	@GetMapping("/getCadaDiaSemana")
	public void getCadaDiaSemana() {
		rules.getCadaDiaSemana();
	}
	
	@GetMapping("/getPeriodoDaNoite")
	public void getNoite() {
		rules.noite();
	}

	@GetMapping("/getPeriodoDaTarde")
	public void getTarde() {
		rules.tarde();

	}
	
	@GetMapping("/getPeriodoDaManha")
	public void getManha() {
		rules.manha();

	}

	@GetMapping("/getPeriodoTotal")
	public void union() {
		rules.periodos();
	}

}
