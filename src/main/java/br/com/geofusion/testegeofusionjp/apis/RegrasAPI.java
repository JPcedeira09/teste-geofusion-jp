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
	
	@GetMapping("/join")
	public void getJoin() {
		rules.densidadeDemografica();
	}
}
