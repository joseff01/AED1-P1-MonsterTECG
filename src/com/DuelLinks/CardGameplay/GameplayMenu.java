package com.DuelLinks.CardGameplay;

import com.DuelLinks.LinearDataStructures.DoubleCircularList.DoubleCircularList;
import com.DuelLinks.LinearDataStructures.Stack.Stack;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class GameplayMenu {

    JPanel mainPanel;

    ServerSocket mySocket;

    boolean pressed = false;

    JButton finishTurnButton;

    Bar myLifeBar;
    Bar enemyLifeBar;
    Bar myManaBar;
    Bar enemyManaBar;

    int opponentSocketNum;

    boolean flagUse = false;

    boolean myTurn;

    Card chosenCard;

    JButton chosenLarge;

    JLabel gameBackgroundLabel;

    DoubleCircularList<Card> allCards = new DoubleCircularList<Card>();

    DoubleCircularList<Card> myHand = new DoubleCircularList<Card>();

    JLabel myDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    Stack myDeck = new Stack(20);
    int myDeckLength = 20;
    JLabel myDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    JLabel myDeckLengthLabel = new JLabel(String.valueOf(myDeckLength), SwingConstants.CENTER);

    DoubleCircularList<JLabel> enemyHand = new DoubleCircularList<JLabel>();
    int enemyHandXPosition = 955;

    JLabel opponentDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    int enemyDeckLength = 20;
    JLabel enemyDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    JLabel enemyDeckLengthLabel = new JLabel(String.valueOf(enemyDeckLength), SwingConstants.CENTER);

    public JButton cardBigLabel;

    public GameplayMenu(JPanel mainPanel, ServerSocket mySocket, int opponentSocketNum, boolean myTurn) {

        this.mainPanel = mainPanel;
        this.mySocket = mySocket;
        this.opponentSocketNum = opponentSocketNum;
        this.myTurn = myTurn;

        gameBackgroundLabel = new JLabel(new ImageIcon("Images\\FondoJuego.png"));
        gameBackgroundLabel.setLayout(null);
        mainPanel.add(gameBackgroundLabel);

        this.cardBigLabel = new JButton();
        cardBigLabel.setBounds(450, 5, 414, 600);
        cardBigLabel.setVisible(false);
        cardBigLabel.setBorderPainted(false);
        cardBigLabel.setFocusPainted(false);
        gameBackgroundLabel.add(cardBigLabel);

        try {
            Scanner scanner = new Scanner(new File("json\\Cards.json"));
            for (int i = 0; i < 22; i++) {
                String cardJsonString = scanner.nextLine();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonMonsterCard JsonCard = objectMapper.readValue(cardJsonString, JsonMonsterCard.class);
                MonsterCard monsterCard = new MonsterCard(JsonCard);
                CardClick cardclick = new CardClick();
                monsterCard.addActionListener(cardclick);

                allCards.addLast(monsterCard);
            }
        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int i = 0;
        DoubleCircularList<Integer> integerSingleList = new DoubleCircularList<Integer>();

        while (i < 20) {
            int randomInt = random.nextInt(22);
            if (!integerSingleList.isValue(randomInt)) {
                integerSingleList.addLast(randomInt);
                myDeck.push(allCards.getValueAt(randomInt));
                i++;
            }
        }

        myDeckLabel.setLayout(null);
        myDeckLabel.setBounds(465, 390, 120, 175);
        myDeckLengthLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        myDeckLengthLabel.setForeground(Color.WHITE);
        myDeckLengthLabel.setBounds(40, 77, 40, 20);
        myDeckLabel.add(myDeckLengthLabel);
        gameBackgroundLabel.add(myDeckLabel);

        enemyDeckLabel.setLayout(null);
        enemyDeckLabel.setBounds(735, 185, 120, 175);
        enemyDeckLengthLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        enemyDeckLengthLabel.setForeground(Color.WHITE);
        enemyDeckLengthLabel.setBounds(40, 77, 40, 20);
        enemyDeckLabel.add(enemyDeckLengthLabel);
        gameBackgroundLabel.add(enemyDeckLabel);

        myDiscardPile.setBounds(595, 390, 120, 175);
        myDiscardPile.setVisible(false);
        gameBackgroundLabel.add(myDiscardPile);

        opponentDiscardPile.setBounds(605, 185, 120, 175);
        opponentDiscardPile.setVisible(false);
        gameBackgroundLabel.add(opponentDiscardPile);

        addCardMyHand();
        addCardMyHand();
        addCardMyHand();
        addCardMyHand();
        addCardMyHand();
        addCardMyHand();
        addCardMyHand();
        addCardMyHand();

        addCardEnemyHand();
        addCardEnemyHand();
        addCardEnemyHand();
        addCardEnemyHand();

        myLifeBar = new Bar(true, true);
        myLifeBar.setBounds(10, 360, 55, 400);
        gameBackgroundLabel.add(myLifeBar);

        myManaBar = new Bar(false, true);
        myManaBar.setBounds(85, 360, 55, 400);
        gameBackgroundLabel.add(myManaBar);

        enemyManaBar = new Bar(false, false);
        enemyManaBar.setBounds(1085, 0, 55, 400);
        gameBackgroundLabel.add(enemyManaBar);

        enemyLifeBar = new Bar(true, false);
        enemyLifeBar.setBounds(1160, 0, 55, 400);
        gameBackgroundLabel.add(enemyLifeBar);

        finishTurnButton = new JButton("Finish Turn");
        finishTurnButton.setBounds(870, 520, 170, 50);
        finishTurnButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));

        class EndTurnEvent implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent i) {

                finishTurnButton.setEnabled(false);

                try {

                    Socket ClientSocket = new Socket("127.0.0.1", opponentSocketNum);
                    DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                    streamOutput.writeUTF("Future Info");
                    streamOutput.close();

                    WaitingState waitingState = new WaitingState(mySocket, finishTurnButton);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        EndTurnEvent endTurnEvent = new EndTurnEvent();
        finishTurnButton.addActionListener(endTurnEvent);
        gameBackgroundLabel.add(finishTurnButton);


        if (!myTurn) {

            finishTurnButton.setEnabled(false);
            WaitingState waitingState = new WaitingState(mySocket, finishTurnButton);

        }

        mainPanel.validate();
        mainPanel.repaint();

    }

    private void removeMyHand() {
        if (!myHand.isEmpty()) {
            int length = myHand.getLength();
            for (int i = 0; i < length; i++) {
                gameBackgroundLabel.remove(myHand.getValueAt(i));
            }
        }
    }

    private void displayMyHand() {
        if (!myHand.isEmpty()) {
            int x = 165;
            int length = myHand.getLength();

            for (int i = 0; i < length; i++) {
                myHand.getValueAt(i).setBounds(x, 575, 120, 175);
                gameBackgroundLabel.add(myHand.getValueAt(i));
                x = x + 135;
            }
        }
    }

    public void addCardMyHand() {
        if (myHand.getLength() <= 8) {
            if (myDeckLength > 0) {
                myHand.addLast((Card) myDeck.pop());
                myDeckLength--;
                myDeckLengthLabel.setText(String.valueOf(myDeckLength));
                this.displayMyHand();
                if (myDeckLength == 0) {
                    gameBackgroundLabel.remove(myDeckLabel);
                }
            }
        }
    }

    public void removeCardMyHand(Card card) {
        this.removeMyHand();
        myHand.deleteValue(card);
        this.displayMyHand();
        myDiscardPile.setVisible(true);
    }

    public void removeCardEnemyHand() {
        if (!enemyHand.isEmpty()) {
            gameBackgroundLabel.remove(enemyHand.getLast());
            enemyHand.deleteLast();
            enemyHandXPosition = enemyHandXPosition + 135;
        }
    }

    public void addCardEnemyHand() {
        if (enemyHand.getLength() <= 8) {
            if (enemyDeckLength > 0) {
                JLabel enemyCard = new JLabel(new ImageIcon("Images\\cpAtras.png"));
                enemyCard.setBounds(enemyHandXPosition, 5, 120, 175);
                enemyHand.addLast(enemyCard);
                gameBackgroundLabel.add(enemyCard);
                enemyHandXPosition = enemyHandXPosition - 135;
                enemyDeckLength--;
                enemyDeckLengthLabel.setText(String.valueOf(enemyDeckLength));
                if (enemyDeckLength == 0) {
                    gameBackgroundLabel.remove(enemyDeckLabel);
                }
            }
        }
    }


    public JButton getCardBigLabel() {
        return cardBigLabel;
    }

    public void setCardBigLabel(JButton cardBigLabel) {
        this.cardBigLabel = cardBigLabel;
    }


    public JButton getChosenLarge() {
        return chosenLarge;
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public void setChosenLarge(JButton chosenLarge) {
        this.chosenLarge = chosenLarge;
    }

    public class CardClick implements ActionListener {

        JButton backButton;
        JButton useButton;

        @Override
        public void actionPerformed(ActionEvent e) {
            finishTurnButton.setEnabled(false);
            Card x = (Card) e.getSource();
            int length = myHand.getLength();
            for (int i = 0; i < length; i++) {
                myHand.getValueAt(i).setEnabled(false);
            }
            useButton = new JButton("Use Card");
            if (flagUse == true){
                useButton.setEnabled(false);
            }
            useButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
            useButton.setBounds(890, 350, 110, 40);
            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!flagUse){
                        if(myManaBar.isEnough(x.getManaRequirement(),myManaBar,true)){
                            flagUse =true;
                            enemyLifeBar.looseVida(x.getAttackDone(), enemyLifeBar, false);
                            removeCardMyHand(x);
                            JButton chosencard = getChosenLarge();
                            chosencard.setIcon(null);
                            setChosenCard(null);
                            gameBackgroundLabel.remove(backButton);
                            gameBackgroundLabel.remove(useButton);
                            gameBackgroundLabel.revalidate();
                            gameBackgroundLabel.repaint();
                            cardBigLabel.setVisible(false);
                            finishTurnButton.setVisible(true);
                            for (int i = 0; i < length; i++) {
                                myHand.getValueAt(i).setEnabled(true);
                            }
                        }
                        else{
                            return;
                        }
                    }
                    else{
                        return;
                    }
                }
            });
            gameBackgroundLabel.add(useButton);

            backButton = new JButton("Back");
            backButton.setBounds(890, 300, 110, 40);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton x = getChosenLarge();
                    x.setIcon(null);
                    setChosenCard(null);
                    gameBackgroundLabel.remove(backButton);
                    gameBackgroundLabel.remove(useButton);
                    gameBackgroundLabel.revalidate();
                    gameBackgroundLabel.repaint();
                    cardBigLabel.setVisible(false);
                    finishTurnButton.setVisible(true);
                    for (int i = 0; i < length; i++) {
                        myHand.getValueAt(i).setEnabled(true);
                    }
                }
            });
            backButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
            gameBackgroundLabel.add(backButton);

            cardBigLabel.setIcon(x.getLargeImage());
            gameBackgroundLabel.revalidate();
            gameBackgroundLabel.repaint();
            cardBigLabel.setVisible(true);
            setChosenLarge(cardBigLabel);
            setChosenCard(x);
        }
    }
}







