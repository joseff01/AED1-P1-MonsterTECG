package com.DuelLinks.ComunicationMessages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttackMessage.class, name = "AttackMessage"),
        @JsonSubTypes.Type(value = EndTurnMessage.class,name = "EndTurnMessage")
})
public abstract class Message {
}
