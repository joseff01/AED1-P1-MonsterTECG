package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.*;
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
                    gameplayMenu.myLifeBar.loseVida(attackMessage.getMyDamageTaken(), true);
                    gameplayMenu.enemyManaBar.loseMana(attackMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                    gameplayMenu.opponentDiscardPile.setVisible(true);
                } else if (objectMapper.readValue(messageReceived,Message.class) instanceof SpellMessage){
                    SpellMessage spellMessage = (SpellMessage) objectMapper.readValue(messageReceived, Message.class);
                    gameplayMenu.showBigCard(spellMessage.getBigCardImageUsed());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;
                    if (spellMessage.getCardName().equals("Dark World Grimoire")){
                        gameplayMenu.flagDarkGrimoire = true;
                    } else if (spellMessage.getCardName().equals("Magic Triangle Of The Ice Barrier")){
                        gameplayMenu.flagMagicTriangle = true;
                    }else if(spellMessage.getCardName().equals("Pot Of Greed")){
                        gameplayMenu.addCardEnemyHand();
                        gameplayMenu.addCardEnemyHand();
                    }
                    gameplayMenu.enemyManaBar.loseMana(spellMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                    gameplayMenu.opponentDiscardPile.setVisible(true);
                } else if (objectMapper.readValue(messageReceived,Message.class) instanceof TrapMessage){
                    TrapMessage trapMessage = (TrapMessage) objectMapper.readValue(messageReceived,Message.class);
                    if (trapMessage.getCardName().equals("The Eye Of Truth")){
                        gameplayMenu.flagTrapEyeOfTruth = true;
                    }
                    gameplayMenu.addOneEnemyTrapCard();
                    gameplayMenu.enemyManaBar.loseMana(trapMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                } else if (objectMapper.readValue(messageReceived,Message.class) instanceof EyeOfTruthMessage){
                    EyeOfTruthMessage trapActivatedMessage = (EyeOfTruthMessage) objectMapper.readValue(messageReceived,Message.class);
                    gameplayMenu.removeOneMyTrapCard();
                    gameplayMenu.enemyLifeBar.loseVida(trapActivatedMessage.getOpponentLifeLost(), false);
                    gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                    gameplayMenu.opponentDiscardPile.setVisible(true);
                }

                gameplayMenu.flagUse = false;
                gameplayMenu.enableMyCards();
                if (!(!gameplayMenu.myTurn && gameplayMenu.firstTurnFlag)){
                    gameplayMenu.addCardMyHand();
                    gameplayMenu.myManaBar.gainMana(250, true);
                }
                gameplayMenu.firstTurnFlag = false;
                gameplayMenu.finishTurnButton.setEnabled(true);
                gameplayMenu.gameBackgroundLabel.validate();
                gameplayMenu.gameBackgroundLabel.repaint();

                EntrySocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            finishTurnButton.setEnabled(true);


    }
}
