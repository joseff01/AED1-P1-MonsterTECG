package com.DuelLinks.ComunicationMessages;
/**
 * Mensaje de la carta trampa Mages Fortress,el cual se convierte en un json para se transmitido
 * @author Jose Retana
 */
public class MagesFortressMessage extends Message{

    int opponentManaUsed;
    String cardName;

    /**
     *
     * @param opponentManaUsed
     * @param cardName
     */
    public MagesFortressMessage(int opponentManaUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
    }

    public MagesFortressMessage(){}

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
