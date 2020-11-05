package com.DuelLinks.ComunicationMessages;

public class SnatchStealMessage extends Message{

    private int opponentManaUsed;
    private String cardNameUsed;
    private int cardNameStolen;
    private boolean firstTime;
    private String cardNameUsedPath;

    public SnatchStealMessage(int opponentManaUsed, String cardNameUsed,String cardNameUsedPath, int cardNameStolen, boolean firstTime) {
        this.cardNameUsed = cardNameUsed;
        this.opponentManaUsed = opponentManaUsed;
        this.cardNameStolen = cardNameStolen;
        this.firstTime = firstTime;
        this.cardNameUsedPath = cardNameUsedPath;
    }

    public SnatchStealMessage(){}

    public int getOpponentManaUsed() {
        return opponentManaUsed;
    }

    public void setOpponentManaUsed(int opponentManaUsed) {
        this.opponentManaUsed = opponentManaUsed;
    }

    public String getCardNameUsed() {
        return cardNameUsed;
    }

    public void setCardNameUsed(String cardName) {
        this.cardNameUsed = cardNameUsed;
    }

    public int getCardNameStolen() {
        return cardNameStolen;
    }

    public void setCardNameStolen(int cardNameStolen) {
        this.cardNameStolen = cardNameStolen;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public String getCardNameUsedPath() {
        return cardNameUsedPath;
    }

    public void setCardNameUsedPath(String cardNameUsedPath) {
        this.cardNameUsedPath = cardNameUsedPath;
    }

}
