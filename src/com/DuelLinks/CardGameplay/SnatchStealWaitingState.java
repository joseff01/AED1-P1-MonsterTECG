package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.Message;
import com.DuelLinks.ComunicationMessages.SnatchStealMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * At. Jose Antonio Retana
 *
 */
public class SnatchStealWaitingState implements Runnable {

    ServerSocket mySocket;

    JButton finishTurnButton;

    GameplayMenu gameplayMenu;

    /**
     * At. Jose Antonio Retana
     * Espera a recivir un mensaje enviado por el oponente la carta del mismo, y le devuelve un mensaje de confirmación.
     * @param mySocket
     * @param finishTurnButton
     * @param gameplayMenu
     */
    public SnatchStealWaitingState(ServerSocket mySocket, JButton finishTurnButton, GameplayMenu gameplayMenu) {

        this.gameplayMenu = gameplayMenu;

        this.mySocket = mySocket;

        this.finishTurnButton = finishTurnButton;

        Thread ParallelThread = new Thread(this);

        ParallelThread.start();

    }

    @Override
    public void run() {
        try {
            Socket EntrySocket = mySocket.accept();

            DataInputStream StreamInput = new DataInputStream(EntrySocket.getInputStream());

            ObjectMapper objectMapper = new ObjectMapper();

            String messageReceived = (String) StreamInput.readUTF();

            System.out.println(messageReceived);

            SnatchStealMessage snatchStealMessage = (SnatchStealMessage) objectMapper.readValue(messageReceived, Message.class);
            Card card = gameplayMenu.allCards.getValueAt(snatchStealMessage.getCardNameStolen());

            gameplayMenu.showBigCard(card.getLargeImageString());
            while (!gameplayMenu.closeCardFlag) {}
            gameplayMenu.closeCardFlag = false;

            if (card instanceof MonsterCard){
                MonsterCard monsterCard = ((MonsterCard) card).createCopyCard();
                gameplayMenu.addSpecificCardMyHand(monsterCard, true);
            } else if (card instanceof SpellCard){
                SpellCard spellCard = ((SpellCard) card).createCopyCard();
                gameplayMenu.addSpecificCardMyHand(spellCard, true);
            } else if (card instanceof TrapCard){
                TrapCard trapCard = ((TrapCard) card).createCopyCard();
                gameplayMenu.addSpecificCardMyHand(trapCard, true);
            }
            gameplayMenu.finishTurnButton.setEnabled(true);

            gameplayMenu.sendMessage = new SnatchStealMessage(50,"Snatch Steal","Images\\BigCards\\Spell\\Steal.png",0,false);

            gameplayMenu.enableMyCards();

            EntrySocket.close();

            gameplayMenu.logList.addLast("You have stolen the card "+ card.getCardName() + "from the enemy!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
