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

    /**
     * At. Jose Antonio Retana
     * @return opponentManaUsed
     */
    public int getOpponentManaUsed() {
        return opponentManaUsed;
    }

    /**
     * At. Jose Antonio Retana
     * @param opponentManaUsed
     */
    public void setOpponentManaUsed(int opponentManaUsed) {
        this.opponentManaUsed = opponentManaUsed;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * At. Jose Antonio Retana
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public int getMyLifeGained() {
        return myLifeGained;
    }

    /**
     * At. Jose Antonio Retana
     * @param myLifeGained
     */
    public void setMyLifeGained(int myLifeGained) {
        this.myLifeGained = myLifeGained;
    }

}
