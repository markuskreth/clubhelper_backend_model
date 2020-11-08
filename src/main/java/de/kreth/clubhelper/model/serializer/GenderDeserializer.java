package de.kreth.clubhelper.model.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import de.kreth.clubhelper.model.data.Gender;

@JsonComponent
public class GenderDeserializer extends JsonDeserializer<Gender> {

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext ctxt)
	    throws IOException, JsonProcessingException {

	TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
	TextNode gender = (TextNode) treeNode.get("gender");
	Gender result;

	if (gender.canConvertToInt()) {
	    result = Gender.valueOf(gender.asInt());
	} else {
	    result = Gender.valueOf(gender.asText());
	}
	return result;
    }

}
