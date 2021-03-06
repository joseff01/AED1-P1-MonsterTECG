package com.DuelLinks.ComunicationMessages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttackMessage.class, name = "AttackMessage"),
        @JsonSubTypes.Type(value = WildMonsterMessage.class, name = "A Wild Monster Appears!"),
        @JsonSubTypes.Type(value = SpellMessage.class, name = "SpellMessage"),
        @JsonSubTypes.Type(value = TrapMessage.class, name = "TrapMessage"),
        @JsonSubTypes.Type(value = EyeOfTruthMessage.class, name = "EyeOfTruthMessage"),
        @JsonSubTypes.Type(value = MagesFortressMessage.class, name = "MagesFortressMessage"),
        @JsonSubTypes.Type(value = MirrorForceMessage.class, name = "MirrorForceMessage"),
        @JsonSubTypes.Type(value = WrathOfTheStarDragonsMessage.class, name = "WrathOfTheStarDragonsMessage"),
        @JsonSubTypes.Type(value = LifeRegenerationMessage.class, name = "LifeRegenerationMessage"),
        @JsonSubTypes.Type(value = SnatchStealMessage.class, name = "SnatchStealMessage"),
        @JsonSubTypes.Type(value = CurseOfAnubisMessage.class, name = "Curse Of Anubis"),
        @JsonSubTypes.Type(value = EndGameMessage.class, name = "EndGameMessage"),
        @JsonSubTypes.Type(value = ShadowOfEyesMessage.class, name = "ShadowOfEyesMessage"),
        @JsonSubTypes.Type(value = SpellBindingMessage.class, name = "SpellBindingMessage")
})

/**
 * Clase común de todos los mensages utilizados, para qeu todos pertenezcan a la misma familia y que
 * se puedan almacenar en conjunto.
 * @authors Jose Retana
 */
public abstract class Message {}
