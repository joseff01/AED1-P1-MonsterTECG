package com.DuelLinks.ComunicationMessages;

public class LifeRegenerationMessage extends Message{
    int opponentManaUsed;
    int myLifeGained;
    String cardName;

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
