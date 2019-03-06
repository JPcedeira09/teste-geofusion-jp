package br.com.geofusion.testegeofusionjp.apis;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
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
	public List<String> densidadeDemografica() {
		return rules.densidadeDemografica();
	}

	@GetMapping("/getCadaDiaSemana")
	public List<String> getCadaDiaSemana() {
		
		Dataset<Row> cadaDiaSemana = rules.getCadaDiaSemana();
		
		List<String> jsonDataset = cadaDiaSemana.toJSON().collectAsList();

		return jsonDataset;
	}

	@GetMapping("/getPeriodoDaNoite")
	public List<String> getNoite() {
		
		Dataset<Row> noite = rules.noite();

		List<String> jsonDataset = noite.toJSON().collectAsList();

		return jsonDataset;
	}

	@GetMapping("/getPeriodoDaTarde")
	public List<String> getTarde() {

		Dataset<Row> tarde = rules.tarde();

		List<String> jsonDataset = tarde.toJSON().collectAsList();

		return jsonDataset;

	}

	@GetMapping("/getPeriodoDaManha")
	public List<String> getManha() {

		Dataset<Row> manha = rules.manha();

		List<String> jsonDataset = manha.toJSON().collectAsList();

		return jsonDataset;
	}

	@GetMapping("/getPeriodoTotal")
	public List<String> union() {
		return rules.periodos();
	}

}
