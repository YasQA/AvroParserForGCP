package com.yaslebid.avroParser.util;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class ClientSchemaBuilder {

    public static Schema createClientAvroSchema() {

    Schema clientSchema = SchemaBuilder.record("Client")
            .namespace("com.yaslebid.avroParser")
            .fields().requiredLong("id").requiredString("name").optionalString("phone").optionalString("address")
            .endRecord();

        return clientSchema;
    }
}