package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje de la carta trampa Shadow of Eyes,el cual se convierte en un json para se transmitido
 * @author Mariana Navarro
 */
public class ShadowOfEyesMessage extends Message{
    int opponentManaUsed;
    String cardName;

    /**
     *
     * @param opponentManaUsed
     * @param cardName
     */
    public ShadowOfEyesMessage(int opponentManaUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
    }
    public ShadowOfEyesMessage(){}

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
