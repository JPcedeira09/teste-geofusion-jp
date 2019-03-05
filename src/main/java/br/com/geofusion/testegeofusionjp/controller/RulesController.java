package br.com.geofusion.testegeofusionjp.controller;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.geofusion.testegeofusionjp.repository.DataSetsUtils;


@Component
public class RulesController {

	@Autowired
	private DataSetsUtils datasetUtils;
	
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
//	
//	private static void join2(SparkContext context) {
//
//		Dataset<AccountFixedTableFee> ds_account_fixed_table_fee = DataSetsUtils.get_account_fixed_table_fee(context);
//
//		Dataset<Row> taxaPorPagamento = DataSetsUtils.getTaxaPorPagamento(context);
//
//		Dataset<Row> join2 = ds_account_fixed_table_fee
//				.join(taxaPorPagamento, taxaPorPagamento.col("id_payment_form_matrix").equalTo(ds_account_fixed_table_fee.col("payment_form_id")))
//				.select(ds_account_fixed_table_fee.col("fixed_table_fee_id"),
//						ds_account_fixed_table_fee.col("account_id"),
//						ds_account_fixed_table_fee.col("parcel_number"),
//						ds_account_fixed_table_fee.col("fixed_tax_percentual"),
//						ds_account_fixed_table_fee.col("parcel_tax_percentual"),
//						ds_account_fixed_table_fee.col("fixed_tax_value"),
//						taxaPorPagamento.col("id_payment_form_matrix"),
//						taxaPorPagamento.col("name"),
//						taxaPorPagamento.col("maximum_installments"),
//						taxaPorPagamento.col("parcel_number"),
//						taxaPorPagamento.col("fixed_tax_percentual"),
//						taxaPorPagamento.col("parcel_tax_percentual"),
//						taxaPorPagamento.col("fixed_tax_value"));
//		join2.show();
//	}
	
	@GetMapping("/getConcorrentes")
	public void getConcorrentes(){
		Dataset<Row> concorrentes = datasetUtils.getConcorrentes();
		concorrentes.show();
	}

	@GetMapping("/getPopulacao")
	public void getPopulacao(){
		Dataset<Row> populacao = datasetUtils.getPopulacao();
		populacao.show();
	}
	
	/**
	 * fluxo_medio_de_pessoa
	 */
	@GetMapping("/fluxo_medio_de_pessoa")
	public void getPotencial(){
		Dataset<Row> potencial = datasetUtils.getPotencial();
		potencial.show();
	}
	
}
