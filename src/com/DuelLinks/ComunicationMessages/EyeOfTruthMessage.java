package com.DuelLinks.ComunicationMessages;

public class EyeOfTruthMessage extends Message{

    int opponentManaUsed;
    int opponentLifeLost;
    String cardName;

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