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
	TextNode gender;
	if (treeNode instanceof TextNode) {
	    gender = ((TextNode) treeNode);
	} else {
	    gender = (TextNode) treeNode.get("gender");
	}
	String asText = gender.asText();
	if ("null".equals(asText)) {
	    return null;
	}
	return Gender.valueOf(asText);
    }

}
