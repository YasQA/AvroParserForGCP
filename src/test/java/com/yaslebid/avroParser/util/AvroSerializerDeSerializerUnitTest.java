//package com.yaslebid.avroParser.util;
//
//import com.yaslebid.avro.Client;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.Assert.*;
//
//
//public class AvroSerializerDeSerializerUnitTest {
//
//    AvroSerializer serializer;
//    AvroDeserializer deSerializer;
//    Client client;
//
//    @Before
//    public void setUp() throws Exception {
//        serializer = new AvroSerializer();
//        deSerializer = new AvroDeserializer();
//
//        client = Client.newBuilder()
//                .setId(33)
//                .setName("John")
//                .setPhone("111-222-3333")
//                .setAddress("NY, Street 1")
//                .build();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//
//    @Test
//    public void WhenSerializedUsingBinaryEncoder_thenObjectGetsSerialized() {
//        byte[] data = serializer.serializeClientBinary(client);
//        assertTrue(Objects.nonNull(data));
//        assertTrue(data.length > 0);
//    }
//
//    @Test
//    public void WhenDeserializeUsingBinaryecoder_thenActualAndExpectedObjectsAreEqual() {
//        byte[] data = serializer.serializeClientBinary(client);
//        Client actualClient = deSerializer.deSerializeClientBinary(data);
//        assertEquals(actualClient, client);
//    }
//
//}