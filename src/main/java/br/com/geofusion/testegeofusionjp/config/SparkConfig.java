package br.com.geofusion.testegeofusionjp.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf()
    			.set("spark.driver.allowMultipleContexts", "true")
                .setAppName("Desafio Geo")
    			.setMaster("local");

        return sparkConf;
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession
                .builder()
                .sparkContext(javaSparkContext().sc())
                .appName("Java Geo SQL")
                .getOrCreate();
    }
}
