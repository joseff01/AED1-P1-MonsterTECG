package com.DuelLinks.ComunicationMessages;

public class AttackMessage {
    int myDamageTaken;
    int opponentManaUsed;
    String bigCardImageUsed;

    public AttackMessage(int myDamageTaken, int opponentManaUsed, String bigCardImageUsed) {
        this.myDamageTaken = myDamageTaken;
        this.opponentManaUsed = opponentManaUsed;
        this.bigCardImageUsed = bigCardImageUsed;
    }

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

}