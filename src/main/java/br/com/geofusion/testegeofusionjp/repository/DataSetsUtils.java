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
		 Dataset<Row> readCSV = utils.readCSV("/Users/joaopaulo/Downloads/desafio/bairros.csv", ",");
		
		 readCSV.createOrReplaceTempView("bairros");	
		 
		 Dataset<Row> bairrosOrganizado = sparkSession.sql("select codigo as codigo_bairros,nome,municipio,uf,area from bairros");
		 
		 return bairrosOrganizado;
	}
	
	public Dataset<Row> getConcorrentes() {
		return utils.readCSV("/Users/joaopaulo/Downloads/desafio/concorrentes.csv", ",");
	}

	public Dataset<Row> getPotencial() {
		return utils.readCSV("/Users/joaopaulo/Downloads/desafio/potencial.csv", ",");
	}
	
	public Dataset<Row> getPopulacao() {
		return utils.readJSON("/Users/joaopaulo/Downloads/desafio/populacao.json");
	}
	
	public Dataset<Row> getEventoDeFluxo() {
		return utils.readCSV("/Users/joaopaulo/Downloads/desafio/eventos_de_fluxo.csv", ",");
	}
	
	
//	/**
//	 * retorn o DATASET  DO CSV ACCOUNT PAYMENT FEE
//	 * @param SparkContext
//	 * @return Dataset<AccountPaymentFee>
//	 */
//	public static Dataset<Row> get_account_payment_fee(SparkContext context) {
//		Dataset<Row> ds_account_payment_fee_csv = DataSetUtils.ReadCSV("/Users/joaopaulo/Downloads/Datafiles/account_payment_fee.csv", context);
//		ds_account_payment_fee_csv.createOrReplaceTempView("account_payment_fee");	
//
//		Dataset<Row> ds_account_payment_fee = SessionSparkUtil.getSession(context).sql("select "
//				+ "id as id_account_payment_fee, account_id, payment_form_id, fixed, antecipation_percentage, one_parcel, between_two_and_six_parcels, more_than_seven_parcels "
//				+ "from account_payment_fee");
//				//.as(Encoders.bean(AccountPaymentFee.class));
//
//		return ds_account_payment_fee;
//	}
	
	 
}
