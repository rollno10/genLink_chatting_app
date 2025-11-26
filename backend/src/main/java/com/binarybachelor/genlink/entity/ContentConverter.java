package com.binarybachelor.genlink.entity;

import com.binarybachelor.genlink.messageContent.Content;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.binarybachelor.genlink.messageContent.TextContent;
import com.binarybachelor.genlink.messageContent.ImageContent;
import com.binarybachelor.genlink.messageContent.EmojiContent;

@Converter(autoApply = true)
public class ContentConverter implements AttributeConverter<Content, String> {

    private final ObjectMapper mapper;

    public ContentConverter(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(Content attribute) {
        if (attribute == null) return null;
        try {
            ObjectNode node = mapper.valueToTree(attribute);
            node.put("messageType", attribute.getMessageType().name());
            return mapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new IllegalStateException("Error converting Content to JSON", e);
        }
    }


    @Override
    public Content convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            JsonNode node = mapper.readTree(dbData);
            
            String type = node.has("messageType") ? node.get("messageType").asText() : null;
            if ("TEXT".equals(type)) {
                return mapper.treeToValue(node, TextContent.class);
            } else if ("IMAGE".equals(type)) {
                return mapper.treeToValue(node, ImageContent.class);
            } else if ("EMOJI".equals(type)) {
                return mapper.treeToValue(node, EmojiContent.class);
            }
            throw new IllegalArgumentException("Unknown content type: " + type);
        } catch (Exception e) {
            throw new IllegalStateException("Error converting JSON to Content", e);
        }
    }

}
