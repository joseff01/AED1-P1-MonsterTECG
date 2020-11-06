package com.DuelLinks.ComunicationMessages;
/**
 * Mensaje de la carta de hechizo SpellBinding Circle,el cual se convierte en un json para se transmitido
 * @author Mariana Navarro
 */
public class SpellBindingMessage extends Message{
    int opponentManaUsed;
    String cardName;
    public SpellBindingMessage(int opponentManaUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
    }
    public SpellBindingMessage(){}

    public int getOpponentManaUsed() {
        return opponentManaUsed;
    }

    public void setOpponentManaUsed(int opponentManaUsed) {
        this.opponentManaUsed = opponentManaUsed;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

}
