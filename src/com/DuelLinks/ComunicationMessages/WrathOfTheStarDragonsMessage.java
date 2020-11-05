package com.DuelLinks.ComunicationMessages;

public class WrathOfTheStarDragonsMessage extends Message{
    int opponentManaUsed;
    String cardName;

    public WrathOfTheStarDragonsMessage(int opponentManaUsed, String cardName) {
        this.cardName = cardName;
        this.opponentManaUsed = opponentManaUsed;
    }

    public WrathOfTheStarDragonsMessage(){}

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
