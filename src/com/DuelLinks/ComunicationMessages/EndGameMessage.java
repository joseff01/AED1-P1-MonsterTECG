package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje enviado cuando se terminó el juego, es convertido en json para ser transmitido
 * @author Mariana Navarro
 *
 */
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
