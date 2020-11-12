package com.yaslebid.avroParser.util;

import com.yaslebid.avroParser.config.*;
import com.google.api.services.bigquery.model.TableRow;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.AvroIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Watch;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;

public class AvroToBigQuery {
    public static void avroToBigQueryTransfer(PipelineOptions pipelineOptions) {
        Pipeline pipeline = Pipeline.create(pipelineOptions);

        PCollection<GenericRecord> avroData = pipeline.apply("Read Avro files",
                AvroIO.readGenericRecords(Config.AVRO_SCHEMA).from(Config.BUCKET_ID + "*.avro")
                .watchForNewFiles(
                        Duration.standardSeconds(20),
                        Watch.Growth.afterTimeSinceNewOutput(Duration.standardHours(1))));

        PCollection<TableRow> customerAllFields =
                avroData.apply(ParDo.of(new DoFn<GenericRecord, TableRow>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                             GenericRecord avroRow = c.element();
                             TableRow tableRow = new TableRow()
                                    .set("id", avroRow.get("id"))
                                    .set("name", avroRow.get("name").toString())
                                    .set("phone", avroRow.get("phone").toString())
                                    .set("address", avroRow.get("address").toString());
                             c.output(tableRow);
                    }
                }));

        PCollection<TableRow> customerMandatoryFields =
                avroData.apply(ParDo.of(new DoFn<GenericRecord, TableRow>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        GenericRecord avroRow = c.element();
                        TableRow tableRow = new TableRow()
                                .set("id", avroRow.get("id"))
                                .set("name", avroRow.get("name").toString());
                        c.output(tableRow);
                    }
                }));

        customerAllFields.apply("Write to BigQuery All fields",
                    BigQueryIO.writeTableRows()
                        .to(Config.CUSTOMER_TABLE_REFERENCE)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_NEVER));

        customerMandatoryFields.apply("Write to BigQuery only Mandatory fields",
                BigQueryIO.writeTableRows()
                        .to(Config.CUSTOMER_MANDATORY_TABLE_REFERENCE)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_NEVER));

        pipeline.run().waitUntilFinish();
    }
}
