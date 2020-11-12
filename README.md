# AvroParserForGCP
AvroParser Application to proceed *.avro data files into BigQuery tables (on GCP using DataFlow)

Application (GCP DataFlow job) looking for new *.avro files in specified bucket and proceed data from new files into 2 tables in BigQuery.

To run locally:
1) set at least command line argument --tempLocation=xxx to run application locally by LocalRunner. And provide --runner=dataflow --project=avroparserapplication --region=xxx (to run application remoutly, starting dataflow job on GCP)
2) Change data in Config.java file to your values (like DATABASE_ID etc.)

'resources' folder contains examples of *.avro files for selected schema in .txt format and corresponding *.avro file (received using avro-tool-xxx.jar) 

