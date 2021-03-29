package br.com.lacd.model.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.lacd.entities.Product;

public class ProductDeserializer extends JsonDeserializer<Product> {

	@Override
	public Product deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
	    JsonNode node = oc.readTree(jsonParser);

	    final String product = node.get("product").asText();
	    final Integer quantity = node.get("quantity").asInt();
	    final Double price = Double.parseDouble(node.get("price").asText().replace("$", ""));
	    final String type = node.get("type").asText();
	    final String industry  = node.get("industry").asText();
	    final String origin = node.get("origin").asText();

	    return new Product(product, quantity, price, type, industry, origin);
	}

}
