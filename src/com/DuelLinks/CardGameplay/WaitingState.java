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

                if (objectMapper.readValue(messageReceived,Message.class) instanceof AttackMessage) {
                    AttackMessage attackMessage = (AttackMessage) objectMapper.readValue(messageReceived, Message.class);
                    gameplayMenu.showBigCard(attackMessage.getBigCardImageUsed());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;
                    gameplayMenu.myLifeBar.loseVida(attackMessage.getMyDamageTaken(), true);
                    gameplayMenu.enemyManaBar.loseMana(attackMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardEnemyHand();
                } else if (objectMapper.readValue(messageReceived,Message.class) instanceof SpellMessage){
                    SpellMessage spellMessage = (SpellMessage) objectMapper.readValue(messageReceived, Message.class);
                    gameplayMenu.showBigCard(spellMessage.getBigCardImageUsed());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;

                    switch(spellMessage.getCardName()){
                        case("Dark World Grimoire"):
                            gameplayMenu.flagDarkGrimoire = true;
                        case("Magic Triangle Of The Ice Barrier"):
                            gameplayMenu.flagMagicTriangle = true;
                        case("Pot Of Greed"):
                            gameplayMenu.addCardEnemyHand();
                            gameplayMenu.addCardEnemyHand();
                        case("Scapegoat"):
                            gameplayMenu.flagScapegoat = true;
                        case("Messanger Of Peace"):
                            gameplayMenu.flagMessengerOfPeace = true;
                    }
                    gameplayMenu.enemyManaBar.loseMana(spellMessage.getOpponentManaUsed(), false);
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
