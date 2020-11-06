package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje de la mayoría de cartas trampa,el cual se convierte en un json para se transmitido
 * @author Jose Retana
 */
public class TrapMessage extends Message{

    int opponentManaUsed;
    String bigCardImageUsed;
    String cardName;

    /**
     *
     * @param opponentManaUsed
     * @param bigCardImageUsed
     * @param cardName
     */
    public TrapMessage(int opponentManaUsed, String bigCardImageUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
        this.bigCardImageUsed = bigCardImageUsed;
    }

    public TrapMessage(){}

    public int getOpponentManaUsed() {
        return opponentManaUsed;
    }

    public void setOpponentManaUsed(int opponentManaUsed) {
        this.opponentManaUsed = opponentManaUsed;
    }

    public String getBigCardImageUsed() {
        return bigCardImageUsed;
    }

    public void setBigCardImageUsed(String bigCardImageUsed) {
        this.bigCardImageUsed = bigCardImageUsed;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

}
