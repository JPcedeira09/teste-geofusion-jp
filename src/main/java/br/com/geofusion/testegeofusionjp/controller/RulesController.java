package br.com.geofusion.testegeofusionjp.controller;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.geofusion.testegeofusionjp.repository.DataSetsUtils;


@Component
public class RulesController {

	@Autowired
	private DataSetsUtils datasetUtils;

	@Autowired
	private SparkSession sparkSession;

	public List<String> densidadeDemografica(){

		Dataset<Row> bairros = datasetUtils.getBairros();

		Dataset<Row> populacao = datasetUtils.getPopulacao();

		Dataset<Row> join = bairros.join(populacao,populacao.col("codigo").equalTo(bairros.col("codigo_bairros")));

//		Dataset<DensidadeDemografica> select = join.select(join.col("codigo_bairros"),
//				join.col("nome"),
//				join.col("municipio"),
//				join.col("uf"),
//				join.col("area"),
//				join.col("populacao"),
//				(join.col("populacao").divide(join.col("area"))).alias("densidade_demografica")).as(Encoders.bean(DensidadeDemografica.class));

		Dataset<Row> select = join.select(join.col("codigo_bairros"),
				join.col("nome"),
				join.col("municipio"),
				join.col("uf"),
				join.col("area"),
				join.col("populacao"),
				(join.col("populacao").divide(join.col("area"))).alias("densidade_demografica"));

		select.show();

		List<String> jsonDataset = select.toJSON().collectAsList();

		return jsonDataset;
	}


	public Dataset<Row> getCadaDiaSemana() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> sql = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana from eventos group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		sql.show();


		return sql;
	}

	public Dataset<Row> manha() {

		Dataset<Row> eventos = datasetUtils.getEventoDeFluxo();

		eventos.createOrReplaceTempView("eventos");	

		Dataset<Row> manha = sparkSession.sql("select codigo_concorrente, count(codigo) as transacoesDiaDaSemana, DAYOFWEEK(datetime) as diaDaSemana, 'manha' as periodo, MIN(HOUR(datetime)) as horaMinimaDoPeriodo,MAX(HOUR(datetime)) as horaMaximaDoPeriodo from eventos where HOUR(datetime) >= 8  and HOUR(datetime) < 12 group by codigo_concorrente, DAYOFWEEK(datetime) order by codigo_concorrente, DAYOFWEEK(datetime)");

		manha.show();

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

	public List<String> periodos() {

		Dataset<Row> union = manha().union(tarde()).union(noite());

		Dataset<Row> orderBy = union.orderBy(union.col("codigo_concorrente"),union.col("diaDaSemana"));
		orderBy.show();

		List<String> jsonDataset = orderBy.toJSON().collectAsList();

		return jsonDataset;
	}

}
