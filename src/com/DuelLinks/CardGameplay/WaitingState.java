package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.AttackMessage;
import com.DuelLinks.ComunicationMessages.Message;
import com.DuelLinks.ComunicationMessages.SpellMessage;
import com.DuelLinks.MainMenu.StartGameFlag;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WaitingState implements Runnable{

    ServerSocket mySocket;

    JButton finishTurnButton;

    GameplayMenu gameplayMenu;

    public WaitingState(ServerSocket mySocket, JButton finishTurnButton, GameplayMenu gameplayMenu){

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
                if (objectMapper.readValue(messageReceived,Message.class) instanceof AttackMessage) {
                    AttackMessage attackMessage = (AttackMessage) objectMapper.readValue(messageReceived, Message.class);
                    gameplayMenu.showBigCard(attackMessage.getBigCardImageUsed());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;
                    gameplayMenu.myLifeBar.looseVida(attackMessage.getMyDamageTaken(), true);
                    gameplayMenu.enemyManaBar.looseMana(attackMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                } else if (objectMapper.readValue(messageReceived,Message.class) instanceof SpellMessage){
                    SpellMessage spellMessage = (SpellMessage) objectMapper.readValue(messageReceived, Message.class);
                    System.out.println(spellMessage.getCardName());
                    gameplayMenu.showBigCard(spellMessage.getBigCardImageUsed());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;
                    if (spellMessage.getCardName().equals("Dark World Grimoire")){
                        gameplayMenu.flagDarkGrimoire = true;
                        System.out.println(gameplayMenu.flagDarkGrimoire);
                    }
                    gameplayMenu.enemyManaBar.looseMana(spellMessage.getOpponentManaUsed(), gameplayMenu.enemyManaBar, false);

                }

                gameplayMenu.flagUse = false;
                gameplayMenu.enableMyCards();
                if (!(!gameplayMenu.myTurn && gameplayMenu.firstTurnFlag)){
                    gameplayMenu.addCardMyHand();
                    gameplayMenu.myManaBar.winMana(250, true);
                }
                gameplayMenu.firstTurnFlag = false;
                gameplayMenu.finishTurnButton.setEnabled(true);
                gameplayMenu.gameBackgroundLabel.validate();
                gameplayMenu.gameBackgroundLabel.repaint();

                EntrySocket.close();

                //ObjectMapper objectMapper = new ObjectMapper();

                //StartGameFlag startGameFlag = objectMapper.readValue(startGameFlagJSON,StartGameFlag.class);


            } catch (IOException e) {
                e.printStackTrace();
            }

            finishTurnButton.setEnabled(true);


    }
}
