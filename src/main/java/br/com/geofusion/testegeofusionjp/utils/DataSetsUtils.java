package br.com.geofusion.testegeofusionjp.utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataSetsUtils {

    @Autowired
    private DataSetUtils utils;
    
	public void getBairros() {
		
		Dataset<Row> load = utils.readCSV("/Users/joaopaulo/Downloads/desafio/bairros.csv", ",");
				
		load.show();
	}
	
	
//	public static void getBairros(SparkContext context) {
//		Dataset<Row> ds_bairros_csv = DataSetUtils.ReadCSV("/Users/joaopaulo/Downloads/desafio/bairros.csv", context);
//		ds_bairros_csv.show();
//	}
	
	
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
