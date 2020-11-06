package com.DuelLinks.ComunicationMessages;

public class EndGameMessage extends Message{

    boolean winner;

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


    public EndGameMessage(boolean won) {
        this.winner = won;
    }

    public EndGameMessage() {
    }
}
