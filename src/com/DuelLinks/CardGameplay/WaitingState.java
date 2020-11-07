package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.*;
import com.DuelLinks.MainMenu.Main;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author Jose Antonio Retana y Mariana Navarro Jimenez
 * Se crea cuando el programa espera a que el oponente termine su turno o envie un mensaje
 */
public class WaitingState implements Runnable {

    ServerSocket mySocket;

    JButton finishTurnButton;

    GameplayMenu gameplayMenu;

    int opponentSocketNum;

    Card snatchedCard;

    /**
     * @author Jose Antonio Retana
     * Se crea un thread que espera a la accion del oponente
     * @param mySocket
     * @param finishTurnButton
     * @param gameplayMenu
     * @param opponentSocketNum
     */

    public WaitingState(ServerSocket mySocket, JButton finishTurnButton, GameplayMenu gameplayMenu, int opponentSocketNum) {

        this.gameplayMenu = gameplayMenu;

        this.mySocket = mySocket;

        this.finishTurnButton = finishTurnButton;

        this.opponentSocketNum = opponentSocketNum;

        Thread ParallelThread = new Thread(this);

        ParallelThread.start();

    }

    /**
     * @author Jose Antonio Retana y Mariana Navarro Jimenez
     * run implementado del metodo Runnable, utilizado para esperar a aceptar la informacion obtenida del scoekt del oponente.
     * Al aceptar la informacion retorna al gameplay menu el efecto de la informacion obtenida.
     */
    @Override
    public void run() {

        try {
            Socket EntrySocket = mySocket.accept();

            DataInputStream StreamInput = new DataInputStream(EntrySocket.getInputStream());

            ObjectMapper objectMapper = new ObjectMapper();

            String messageReceived = (String) StreamInput.readUTF();


            if (objectMapper.readValue(messageReceived, Message.class) instanceof AttackMessage) {
                AttackMessage attackMessage = (AttackMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.showBigCard(attackMessage.getBigCardImageUsed());
                while (!gameplayMenu.closeCardFlag) {
                }
                gameplayMenu.closeCardFlag = false;
                gameplayMenu.myLifeBar.loseVida(attackMessage.getMyDamageTaken(), true);
                gameplayMenu.enemyManaBar.loseMana(attackMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
                gameplayMenu.logList.addLast("You have been attacked by the enemy with " + attackMessage.getCardName() + " using " + attackMessage.getOpponentManaUsed() + " mana and dealing " + attackMessage.getMyDamageTaken() + " damage!\n");

            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof SpellMessage) {
                SpellMessage spellMessage = (SpellMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.showBigCard(spellMessage.getBigCardImageUsed());
                while (!gameplayMenu.closeCardFlag) {
                }
                gameplayMenu.closeCardFlag = false;

                if (spellMessage.getCardName().equals("Dark World Grimoire")) {
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.flagDarkGrimoire = true;
                } else if (spellMessage.getCardName().equals("Magic Triangle Of The Ice Barrier")) {
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.flagMagicTriangle = true;
                } else if (spellMessage.getCardName().equals("Pot Of Greed")) {
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.logList.addLast("He has added two extra cards to his hand\n");
                    gameplayMenu.addCardEnemyHand();
                    gameplayMenu.addCardEnemyHand();
                } else if (spellMessage.getCardName().equals("Scapegoat")) {
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.flagScapegoat = true;
                } else if (spellMessage.getCardName().equals("Messanger Of Peace")) {
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.flagMessengerOfPeace = true;
                } else if (spellMessage.getCardName().equals("Remove Trap")){
                    gameplayMenu.amountMyTrapCards = 0;
                    gameplayMenu.myTrapLengthLabel.setText(String.valueOf(gameplayMenu.amountMyTrapCards));
                    gameplayMenu.myTrapCards.setVisible(false);
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                    gameplayMenu.logList.addLast("All your set trap cards have vanished!?!\n");


                } else{
                    gameplayMenu.logList.addLast("The enemy has activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                }
                gameplayMenu.enemyManaBar.loseMana(spellMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof SnatchStealMessage) {
                SnatchStealMessage snatchStealMessage = (SnatchStealMessage) objectMapper.readValue(messageReceived, Message.class);
                if (snatchStealMessage.isFirstTime()) {
                    EntrySocket.close();

                    Random random = new Random();
                    Card stolenCard = gameplayMenu.myHand.getValueAt(random.nextInt(gameplayMenu.myHand.getLength()));
                    this.snatchedCard = stolenCard;
                    SnatchStealMessage snatchStealMessageReturn = new SnatchStealMessage(0, null, null, stolenCard.getId(), true);

                    Socket ClientSocket = new Socket("127.0.0.1", opponentSocketNum);
                    DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                    String messageJson = objectMapper.writeValueAsString(snatchStealMessageReturn);
                    streamOutput.writeUTF(messageJson);
                    streamOutput.close();
                    run();
                } else if (!snatchStealMessage.isFirstTime()) {
                    gameplayMenu.showBigCard(snatchStealMessage.getCardNameUsedPath());
                    while (!gameplayMenu.closeCardFlag) {
                    }
                    gameplayMenu.closeCardFlag = false;
                    gameplayMenu.enemyManaBar.loseMana(snatchStealMessage.getOpponentManaUsed(), false);
                    gameplayMenu.removeCardMyHand(snatchedCard);
                    gameplayMenu.opponentDiscardPile.setVisible(true);
                    gameplayMenu.logList.addLast("The enemy has stolen the card " + snatchedCard.getCardName() + " and has lost " + snatchStealMessage.getOpponentManaUsed() + "\n");
                }
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof TrapMessage) {
                TrapMessage trapMessage = (TrapMessage) objectMapper.readValue(messageReceived, Message.class);
                if (trapMessage.getCardName().equals("The Eye Of Truth")) {
                    gameplayMenu.flagTrapEyeOfTruth = true;
                } else if (trapMessage.getCardName().equals("Life Regeneration")) {
                    gameplayMenu.flagTrapLifeRegeneration = true;
                } else if (trapMessage.getCardName().equals("Mage's Fortress")) {
                    gameplayMenu.flagTrapMagesFortress = true;
                } else if (trapMessage.getCardName().equals("Mirror Force")) {
                    gameplayMenu.flagTrapMirrorForce = true;
                } else if (trapMessage.getCardName().equals("Wrath of The Star Dragons")) {
                    gameplayMenu.flagTrapWrathOfTheStarDragons = true;
                } else if (trapMessage.getCardName().equals("Spellbinding Circle")) {
                    gameplayMenu.flagSpellbinding = true;
                } else if (trapMessage.getCardName().equals("Shadow Of Eyes")) {
                    gameplayMenu.flagTrapShadowEye = true;
                }
                gameplayMenu.addOneEnemyTrapCard();
                gameplayMenu.enemyManaBar.loseMana(trapMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.logList.addLast("The enemy has set a trap card and lost " + trapMessage.getOpponentManaUsed() + " mana\n");

            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof EyeOfTruthMessage) {
                EyeOfTruthMessage trapActivatedMessage = (EyeOfTruthMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost " + trapActivatedMessage.getOpponentLifeLost() + " life points and lost "+ trapActivatedMessage.getOpponentManaUsed() +"mana\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.enemyLifeBar.loseVida(trapActivatedMessage.getOpponentLifeLost(), false);
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof LifeRegenerationMessage) {
                LifeRegenerationMessage trapActivatedMessage = (LifeRegenerationMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost "+ trapActivatedMessage.getOpponentManaUsed() +" mana, and you have gained "+ trapActivatedMessage.getMyLifeGained() +" life points\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.myLifeBar.gainLife(trapActivatedMessage.getMyLifeGained(), true);
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof SpellBindingMessage) {
                SpellBindingMessage trapActivatedMessage = (SpellBindingMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost "+ trapActivatedMessage.getOpponentManaUsed() +" mana\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof MagesFortressMessage) {
                MagesFortressMessage trapActivatedMessage = (MagesFortressMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost "+ trapActivatedMessage.getOpponentManaUsed() +" mana\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof MirrorForceMessage) {
                MirrorForceMessage trapActivatedMessage = (MirrorForceMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost " + trapActivatedMessage.getOpponentLifeLost() + " life points and lost "+ trapActivatedMessage.getOpponentManaUsed() +"mana\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.enemyLifeBar.loseVida(trapActivatedMessage.getOpponentLifeLost(), false);
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof WrathOfTheStarDragonsMessage) {
                WrathOfTheStarDragonsMessage trapActivatedMessage = (WrathOfTheStarDragonsMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost "+ trapActivatedMessage.getOpponentManaUsed() +" mana\n");
                gameplayMenu.removeOneMyTrapCard();
                gameplayMenu.enemyManaBar.loseMana(trapActivatedMessage.getOpponentManaUsed(), false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof ShadowOfEyesMessage) {
                ShadowOfEyesMessage trapActivatedMessage = (ShadowOfEyesMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.logList.addLast("The enemy has activated your trap card " + trapActivatedMessage.getCardName()
                        + " and lost "+ trapActivatedMessage.getOpponentManaUsed() +" mana\n");
                gameplayMenu.enemyManaBar.loseMana(1000, false);
                gameplayMenu.removeCardEnemyHand();
                gameplayMenu.opponentDiscardPile.setVisible(true);
            }else if (objectMapper.readValue(messageReceived, Message.class) instanceof EndGameMessage) {
                EndGameMessage endGameMessage = (EndGameMessage) objectMapper.readValue(messageReceived,Message.class);
                if(endGameMessage.isWinner()){
                    gameplayMenu.finishTurnButton.setVisible(false);
                    gameplayMenu.logButton.setVisible(false);
                    gameplayMenu.hideMyCards();
                    gameplayMenu.nextGame.setVisible(true);
                    gameplayMenu.endResult.setText("You Won!");
                    gameplayMenu.endGameMessageLabel.setVisible(true);
                    gameplayMenu.endGameMessageLabel.revalidate();
                    gameplayMenu.endGameMessageLabel.repaint();
                }else{
                    gameplayMenu.finishTurnButton.setVisible(false);
                    gameplayMenu.logButton.setVisible(false);
                    gameplayMenu.hideMyCards();
                    gameplayMenu.nextGame.setVisible(true);
                    gameplayMenu.endResult.setText("You Lost.");
                    gameplayMenu.endGameMessageLabel.setVisible(true);
                    gameplayMenu.endGameMessageLabel.revalidate();
                    gameplayMenu.endGameMessageLabel.repaint();
                }
                gameplayMenu.nextGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Window win = SwingUtilities.getWindowAncestor(gameplayMenu.mainPanel);
                        win.dispose();
                    }
                });
            } else if (objectMapper.readValue(messageReceived, Message.class) instanceof WildMonsterMessage) {
                WildMonsterMessage wildMonsterMessage = (WildMonsterMessage ) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.showBigCard(wildMonsterMessage.getImage1());
                if(wildMonsterMessage.getImage2()==null){
                    while(!gameplayMenu.closeCardFlag){}
                    gameplayMenu.closeCardFlag = false;
            

                    gameplayMenu.myLifeBar.loseVida(wildMonsterMessage.getMyDamageTaken(),true);
                    gameplayMenu.opponentDiscardPile.setVisible(true);

                }else if(wildMonsterMessage.getImage3()==null) {
                    while (!gameplayMenu.closeCardFlag) {
                    }
                    gameplayMenu.closeCardFlag = false;

                    gameplayMenu.showBigCard(wildMonsterMessage.getImage2());
                    while (!gameplayMenu.closeCardFlag) {}
                    gameplayMenu.closeCardFlag = false;

                    gameplayMenu.myLifeBar.loseVida(wildMonsterMessage.getMyDamageTaken(), true);
                    gameplayMenu.opponentDiscardPile.setVisible(true);
                    gameplayMenu.removeCardEnemyHand();

                    if (wildMonsterMessage.getImage2() == null) {
                        while (!gameplayMenu.closeCardFlag) {
                        }
                        gameplayMenu.closeCardFlag = false;

                        gameplayMenu.myLifeBar.loseVida(wildMonsterMessage.getMyDamageTaken(), true);
                        gameplayMenu.opponentDiscardPile.setVisible(true);
                    }
                }
                else {
                    while (!gameplayMenu.closeCardFlag) {
                    }
                    gameplayMenu.closeCardFlag = false;

                    gameplayMenu.showBigCard(wildMonsterMessage.getImage2());
                    while (!gameplayMenu.closeCardFlag) {
                    }
                    gameplayMenu.closeCardFlag = false;

                    gameplayMenu.showBigCard(wildMonsterMessage.getImage3());
                    while (!gameplayMenu.closeCardFlag) {
                    }
                    gameplayMenu.closeCardFlag = false;

                    gameplayMenu.myLifeBar.loseVida(wildMonsterMessage.getMyDamageTaken(), true);
                    gameplayMenu.opponentDiscardPile.setVisible(true);

                    gameplayMenu.removeCardEnemyHand();
                    gameplayMenu.removeCardEnemyHand();
                }
            }else if (objectMapper.readValue(messageReceived, Message.class) instanceof CurseOfAnubisMessage) {
                CurseOfAnubisMessage curseOfAnubis = (CurseOfAnubisMessage) objectMapper.readValue(messageReceived, Message.class);
                gameplayMenu.showBigCard(curseOfAnubis.getImageAnubis());
                while (!gameplayMenu.closeCardFlag) {
                }
                gameplayMenu.closeCardFlag = false;
            }

            gameplayMenu.flagUse = false;
            gameplayMenu.enableMyCards();

            if (!(!gameplayMenu.myTurn && gameplayMenu.firstTurnFlag)) {
                gameplayMenu.addCardMyHand();
                gameplayMenu.myManaBar.gainMana(250, true);
            }
            gameplayMenu.firstTurnFlag = false;
            gameplayMenu.finishTurnButton.setEnabled(true);
            gameplayMenu.gameBackgroundLabel.validate();
            gameplayMenu.gameBackgroundLabel.repaint();

            EntrySocket.close();

        } catch(IOException e){
                e.printStackTrace();
        }

            finishTurnButton.setEnabled(true);


        }
    }


