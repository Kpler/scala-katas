while read -r json_obj; do

    echo "$(echo "$json_obj" | jq -r ."$KEY_FIELD")|$json_obj"

done