package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje de ataque el cual se convierte en un json para se transmitido
 * @author Jose Retana
 */
public class AttackMessage extends Message{
    int myDamageTaken;
    int opponentManaUsed;
    String bigCardImageUsed;
    String cardName;

    /**
     * @param myDamageTaken
     * @param opponentManaUsed
     * @param bigCardImageUsed
     * @param cardName
     */
    public AttackMessage(int myDamageTaken, int opponentManaUsed, String bigCardImageUsed, String cardName) {
        this.myDamageTaken = myDamageTaken;
        this.opponentManaUsed = opponentManaUsed;
        this.bigCardImageUsed = bigCardImageUsed;
        this.cardName = cardName;
    }

    public AttackMessage(){}

    public int getMyDamageTaken() {
        return myDamageTaken;
    }

    public void setMyDamageTaken(int myDamageTaken) {
        this.myDamageTaken = myDamageTaken;
    }

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
