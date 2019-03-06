package br.com.geofusion.testegeofusionjp.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.geofusion.testegeofusionjp.utils.DataSetUtils;



@Component
public class DataSetsUtils {

    @Autowired
    private DataSetUtils utils;
    
    @Autowired
    private SparkSession sparkSession;
    
	public Dataset<Row> getBairros() {
		 Dataset<Row> readCSV = utils.readCSV("bairros.csv", ",");
		 readCSV.createOrReplaceTempView("bairros");	
		 Dataset<Row> bairrosOrganizado = sparkSession.sql("select codigo as codigo_bairros,nome,municipio,uf,area from bairros");
		 return bairrosOrganizado;
	}
	
	public Dataset<Row> getConcorrentes() {
		return utils.readCSV("concorrentes.csv", ",");
	}

	public Dataset<Row> getPotencial() {
		return utils.readCSV("potencial.csv", ",");
	}
	
	public Dataset<Row> getPopulacao() {
		return utils.readJSON("populacao.json");
	}
	
	public Dataset<Row> getEventoDeFluxo() {
		return utils.readCSV("eventos_de_fluxo.csv", ",");
	}
	
}
