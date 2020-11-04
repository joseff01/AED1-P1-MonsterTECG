package com.DuelLinks.ComunicationMessages;

public class SpellMessage extends Message{

    int opponentManaUsed;
    String bigCardImageUsed;
    String cardName;

    public SpellMessage(int opponentManaUsed, String bigCardImageUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
        this.bigCardImageUsed = bigCardImageUsed;
    }

    public SpellMessage(){}

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
