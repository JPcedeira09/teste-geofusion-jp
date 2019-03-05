package br.com.geofusion.testegeofusionjp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geofusion.testegeofusionjp.utils.DataSetsUtils;

@RestController
@RequestMapping("/api/teste/geofusion")
public class RulesController {

	@Autowired
	private DataSetsUtils datasetUtils;
	
	@GetMapping("/teste1")
	public void test1(){
//		log.info("Realizando Teste......");
	}
	
	@GetMapping("/teste")
	public void test(){
		datasetUtils.getBairros();
	}
	
	
}
