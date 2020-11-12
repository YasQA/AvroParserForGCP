package com.yaslebid.avroParser;

import com.yaslebid.avroParser.util.AvroToBigQuery;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvroParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroParserApplication.class, args);

		PipelineOptions pipelineOptions = PipelineOptionsFactory.fromArgs(args).withValidation().create();
		AvroToBigQuery.avroToBigQueryTransfer(pipelineOptions);
	}
}
