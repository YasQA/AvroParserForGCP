package com.yaslebid.avroParser.config;

import com.google.api.services.bigquery.model.TableReference;
import com.yaslebid.avroParser.util.ClientSchemaBuilder;
import org.apache.avro.Schema;

public class Config {
    public static final String PROJECT_ID = "avroparserapplication";
    public static final String DATABASE_ID = "CustomerDataset";
    public static final String TABLE_CUSTOMER = "Customer";
    public static final String TABLE_CUSTOMER_MANDATORY = "CustomerMandatory";
    public static final String BUCKET_ID = "gs://yl_avro_bucket/";

    public static final Schema AVRO_SCHEMA = ClientSchemaBuilder.createClientAvroSchema();

    public static final TableReference CUSTOMER_TABLE_REFERENCE
            = new TableReference()
                .setProjectId(Config.PROJECT_ID)
                .setDatasetId(Config.DATABASE_ID)
                .setTableId(Config.TABLE_CUSTOMER);

    public static final TableReference CUSTOMER_MANDATORY_TABLE_REFERENCE
            = new TableReference()
            .setProjectId(Config.PROJECT_ID)
            .setDatasetId(Config.DATABASE_ID)
            .setTableId(Config.TABLE_CUSTOMER_MANDATORY);
}
