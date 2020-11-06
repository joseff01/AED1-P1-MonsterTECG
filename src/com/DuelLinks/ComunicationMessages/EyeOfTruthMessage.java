package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje de la carta trampa Eye of Truth,el cual se convierte en un json para se transmitido
 * @author Jose Retana
 */
public class EyeOfTruthMessage extends Message{

    int opponentManaUsed;
    int opponentLifeLost;
    String cardName;

    /**
     *
     * @param opponentManaUsed
     * @param opponentLifeLost
     * @param cardName
     */
    public EyeOfTruthMessage(int opponentManaUsed, int opponentLifeLost, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
        this.opponentLifeLost = opponentLifeLost;
    }

    public EyeOfTruthMessage(){}

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

    public int getOpponentLifeLost() {
        return opponentLifeLost;
    }

    public void setOpponentLifeLost(int opponentLifeLost) {
        this.opponentLifeLost = opponentLifeLost;
    }
}
