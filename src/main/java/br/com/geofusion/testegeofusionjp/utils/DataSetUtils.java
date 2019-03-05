package br.com.geofusion.testegeofusionjp.utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSetUtils {

    @Autowired
    private SparkSession sparkSession;
    
	public Dataset<Row> readCSV(String path, String delimiter) {
				
		return sparkSession.read().format("csv")
				.option("header", "true")
				.option("delimiter", delimiter)
				.load(path);
	}
	
	public Dataset<Row> readJSON(String path) {
		return sparkSession
				.read().format("json")
				.option("header", "true")
				.load(path);
	}
	
}
