package br.com.geofusion.testegeofusionjp.controller;

import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.geofusion.testegeofusionjp.models.EventosSemanais;
import br.com.geofusion.testegeofusionjp.repository.DataSetsUtils;
import br.com.lemon.model.NasaRequest;


@Component
public class RulesController {

	@Autowired
	private DataSetsUtils datasetUtils;

	@Autowired
	private SparkSession sparkSession;

	@GetMapping("/densidade_demografica")
	public void densidadeDemografica(){

		Dataset<Row> bairros = datasetUtils.getBairros();

		Dataset<Row> populacao = datasetUtils.getPopulacao();

		Dataset<Row> join = bairros.join(populacao,populacao.col("codigo").equalTo(bairros.col("codigo_bairros")));

		Dataset<Row> select = join.select(join.col("codigo_bairros"),
				join.col("nome"),
				join.col("municipio"),
				join.col("uf"),
				join.col("area"),
				join.col("populacao"),
				(join.col("populacao").divide(join.col("area"))).alias("densidade_demografica"));

		select.show();
	}

	
	public void getCadaDiaSemana() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> sql = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana from eventos group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		sql.show();

		RDD<Row> rdd = sql.rdd();
//		sql.map(x -> new EventosSemanais(sql.col("").cast("Int"),sql.col("").cast("Int"),sql.col("").cast("Int")));
		
	}

	public Dataset<Row> manha() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> manha = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana, 'manha' as periodo, MIN(HOUR(datetime)) as horaMinimaDoPeriodo,MAX(HOUR(datetime)) as horaMaximaDoPeriodo from eventos where HOUR(datetime) >= 8  and HOUR(datetime) < 12 group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		manha.as(evidence$2);

		return manha;
	}

	public Dataset<Row> tarde() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> tarde = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana, 'tarde' as periodo, MIN(HOUR(datetime)) as horaMinimaDoPeriodo,MAX(HOUR(datetime)) as horaMaximaDoPeriodo from eventos where HOUR(datetime) >= 12 and HOUR(datetime) < 18 group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		tarde.show();

		return tarde;
	}

	public Dataset<Row> noite() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> noite = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana, 'noite' as periodo, MIN(HOUR(datetime)) as horaMinimaDoPeriodo,MAX(HOUR(datetime)) as horaMaximaDoPeriodo from eventos where HOUR(datetime) >= 18 and HOUR(datetime) < 24 group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		noite.show();
		return noite;
	}

	public void periodos() {
		
		Dataset<Row> union = manha().union(tarde()).union(noite());

		union.orderBy(union.col("codigo_concorrente"),union.col("diaDaSemana")).show();;
	}


}
