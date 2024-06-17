import json
import sys

import avro.schema
from avro.schema import SchemaFromJSONData
from avro_json_serializer import AvroJsonSerializer

if __name__ == '__main__':
    avro_schema_filename = sys.argv[1]
    with open(avro_schema_filename) as avro_schema_file:
        avro_schema_dict = json.loads(avro_schema_file.read())
        avro_schema = SchemaFromJSONData(avro_schema_dict, avro.schema.Names())
        serializer = AvroJsonSerializer(avro_schema)
        for json_object_str in sys.stdin:
            avro_message_dict = json.loads(json_object_str.rstrip())
            print(serializer.to_json(avro_message_dict))
