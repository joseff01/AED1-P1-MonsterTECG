package com.DuelLinks.ComunicationMessages;
/**
 * Mensaje de la carta trampa Life Regeneration,el cual se convierte en un json para se transmitido
 * @author Jose Retana
 */
public class LifeRegenerationMessage extends Message{
    int opponentManaUsed;
    int myLifeGained;
    String cardName;

    /**
     *
     * @param opponentManaUsed
     * @param myLifeGained
     * @param cardName
     */
    public LifeRegenerationMessage(int opponentManaUsed, int myLifeGained, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
        this.myLifeGained = myLifeGained;
    }

    public LifeRegenerationMessage(){}

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

    public int getMyLifeGained() {
        return myLifeGained;
    }

    public void setMyLifeGained(int myLifeGained) {
        this.myLifeGained = myLifeGained;
    }

}
