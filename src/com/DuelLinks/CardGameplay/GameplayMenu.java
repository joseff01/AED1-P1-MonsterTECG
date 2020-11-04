package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.AttackMessage;
import com.DuelLinks.ComunicationMessages.Message;
import com.DuelLinks.ComunicationMessages.SpellMessage;
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

    private JPanel mainPanel;

    private ServerSocket mySocket;

    private boolean pressed = false;

    public JButton finishTurnButton;
    JButton log;

    public Bar myLifeBar;
    public Bar enemyLifeBar;
    public Bar myManaBar;
    public Bar enemyManaBar;

    private int opponentSocketNum;

    public boolean flagUse = false;

    public boolean myTurn;

    private Card chosenCard;

    private JButton chosenLarge;

    public JLabel gameBackgroundLabel;

    private DoubleCircularList<Card> allCards = new DoubleCircularList<Card>();

    private DoubleCircularList<Card> myHand = new DoubleCircularList<Card>();

    private JLabel myDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    private Stack myDeck = new Stack(20);
    private int myDeckLength = 20;
    private JLabel myDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    private JLabel myDeckLengthLabel = new JLabel(String.valueOf(myDeckLength), SwingConstants.CENTER);

    private DoubleCircularList<JLabel> enemyHand = new DoubleCircularList<JLabel>();
    private int enemyHandXPosition = 955;

    private JLabel opponentDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    private int enemyDeckLength = 20;
    private JLabel enemyDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    private JLabel enemyDeckLengthLabel = new JLabel(String.valueOf(enemyDeckLength), SwingConstants.CENTER);

    private JButton cardBigLabel;

    private Message sendMessage;

    public volatile boolean closeCardFlag = false;

    public volatile boolean firstTurnFlag = true;

    public volatile boolean flagFightingSpirit = false;

    public volatile boolean flagDarkGrimoire = false;

    public volatile boolean flagMagicTriangle = false;

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
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < 22; i++) {
                String cardJsonString = scanner.nextLine();
                JsonMonsterCard JsonCard = objectMapper.readValue(cardJsonString, JsonMonsterCard.class);
                MonsterCard monsterCard = new MonsterCard(JsonCard);
                CardClick cardclick = new CardClick();
                monsterCard.addActionListener(cardclick);
                allCards.addLast(monsterCard);
            }
            for (int i = 0; i < 10; i++) {
                String cardJsonString = scanner.nextLine();
                JsonSpellCard JsonCard = objectMapper.readValue(cardJsonString, JsonSpellCard.class);
                SpellCard spellCard = new SpellCard(JsonCard);
                CardClick cardclick = new CardClick();
                spellCard.addActionListener(cardclick);
                allCards.addLast(spellCard);
            }
        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int i = 0;
        DoubleCircularList<Integer> integerSingleList = new DoubleCircularList<Integer>();

        while (i < 20) {
            int randomInt = random.nextInt(32);
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

        myHand.addLast(allCards.getValueAt(27));

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
        myManaBar.setBounds(85, 660, 55, 400);
        gameBackgroundLabel.add(myManaBar);

        enemyManaBar = new Bar(false, false);
        enemyManaBar.setBounds(1085, 0, 55, 100);
        gameBackgroundLabel.add(enemyManaBar);

        enemyLifeBar = new Bar(true, false);
        enemyLifeBar.setBounds(1160, 0, 55, 400);
        gameBackgroundLabel.add(enemyLifeBar);

        finishTurnButton = new JButton(new ImageIcon("Images\\endTurn.png"));
        finishTurnButton.setBounds(200, 300, 208, 83);

        log = new JButton(new ImageIcon("Images\\record.png"));
        log.setBounds(200, 420, 162, 51);
        gameBackgroundLabel.add(log);

        class EndTurnEvent implements ActionListener {

            GameplayMenu gameplayMenu;

            public EndTurnEvent(GameplayMenu gameplayMenu) {
                this.gameplayMenu = gameplayMenu;
            }


            @Override
            public void actionPerformed(ActionEvent i) {

                finishTurnButton.setEnabled(false);

                try {
                    Socket ClientSocket = new Socket("127.0.0.1", opponentSocketNum);
                    DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                    ObjectMapper objectMapper = new ObjectMapper();
                    String messageJson = objectMapper.writeValueAsString(sendMessage);
                    streamOutput.writeUTF(messageJson);
                    streamOutput.close();
                    sendMessage = null;
                    gameplayMenu.disableMyCards();
                    if (!(myTurn && firstTurnFlag)) {
                        enemyManaBar.gainMana(250, false);
                        addCardEnemyHand();
                    }
                    WaitingState waitingState = new WaitingState(mySocket, finishTurnButton, gameplayMenu);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        EndTurnEvent endTurnEvent = new EndTurnEvent(this);
        finishTurnButton.addActionListener(endTurnEvent);
        gameBackgroundLabel.add(finishTurnButton);

        if (!myTurn) {
            disableMyCards();
            finishTurnButton.setEnabled(false);
            WaitingState waitingState = new WaitingState(mySocket, finishTurnButton,this);

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
            opponentDiscardPile.setVisible(true);
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

    public void disableMyCards(){
        for (int i = 0; i < myHand.getLength(); i++) {
            myHand.getValueAt(i).setEnabled(false);
        }
    }

    public void enableMyCards(){
        for (int i = 0; i < myHand.getLength(); i++) {
            myHand.getValueAt(i).setEnabled(true);
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

    public void showBigCard(String cardStringPath){
        cardBigLabel.setIcon(new ImageIcon(cardStringPath));
        cardBigLabel.setVisible(true);
        JButton backButton = new JButton("Back");
        backButton.setBounds(890, 300, 110, 40);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBackgroundLabel.remove(backButton);
                cardBigLabel.setVisible(false);
                gameBackgroundLabel.revalidate();
                gameBackgroundLabel.repaint();
                closeCardFlag = true;
            }
        });
        backButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
        gameBackgroundLabel.add(backButton);
        gameBackgroundLabel.revalidate();
        gameBackgroundLabel.repaint();

    }

    public class CardClick implements ActionListener {

        JButton backButton;
        JButton useButton;

        @Override
        public void actionPerformed(ActionEvent e) {
            finishTurnButton.setEnabled(false);
            Card card = (Card) e.getSource();
            disableMyCards();
            useButton = new JButton(new ImageIcon("Images\\useCard.png"));
            if (flagUse == true){
                useButton.setEnabled(false);
            }
            useButton.setBorderPainted(false);
            useButton .setFocusPainted(false);
            useButton.setBounds(890, 300, 162, 51);
            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int manaRequirement = card.getManaRequirement();
                    if (flagDarkGrimoire) {
                        manaRequirement = manaRequirement * 2;
                    }
                    if (myManaBar.isEnough(manaRequirement)) {
                        flagDarkGrimoire = false;
                        if (card instanceof MonsterCard) {
                            if (!flagMagicTriangle) {
                                if (flagFightingSpirit) {
                                    flagFightingSpirit = false;
                                    AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage() + 200, manaRequirement, card.getLargeImageString(), card.getCardName());
                                    sendMessage = attackMessage;
                                    myManaBar.loseMana(manaRequirement, true);
                                    enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage() + 200, false);
                                    flagUse = true;
                                    removeCardMyHand(card);
                                    JButton chosencard = getChosenLarge();
                                    chosencard.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    gameBackgroundLabel.revalidate();
                                    gameBackgroundLabel.repaint();
                                    cardBigLabel.setVisible(false);
                                    finishTurnButton.setEnabled(true);
                                    enableMyCards();
                                } else {
                                    AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage(), manaRequirement, card.getLargeImageString(), card.getCardName());
                                    sendMessage = attackMessage;
                                    enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage(), false);
                                    myManaBar.loseMana(manaRequirement, true);
                                    flagUse = true;
                                    removeCardMyHand(card);
                                    JButton chosencard = getChosenLarge();
                                    chosencard.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    gameBackgroundLabel.revalidate();
                                    gameBackgroundLabel.repaint();
                                    cardBigLabel.setVisible(false);
                                    finishTurnButton.setEnabled(true);
                                    enableMyCards();
                                }
                            }
                        } else if (card instanceof SpellCard) {
                            myManaBar.loseMana(manaRequirement, true);
                            SpellMessage spellMessage = new SpellMessage(manaRequirement, card.getLargeImageString(), card.getCardName());
                            sendMessage = spellMessage;
                            if (card.getCardName().equals("Pot Of Greed")){
                                addCardMyHand();
                                addCardMyHand();
                            }

                            switch (card.getCardName())
                            {
                                case ("Fighting Spirit"):
                                    flagFightingSpirit = true;

                            }
                            flagUse = true;
                            removeCardMyHand(card);
                            JButton chosencard = getChosenLarge();
                            chosencard.setIcon(null);
                            setChosenCard(null);
                            gameBackgroundLabel.remove(backButton);
                            gameBackgroundLabel.remove(useButton);
                            gameBackgroundLabel.revalidate();
                            gameBackgroundLabel.repaint();
                            cardBigLabel.setVisible(false);
                            finishTurnButton.setEnabled(true);
                            enableMyCards();

                        }
                    }
                }
            });

            gameBackgroundLabel.add(useButton);
            backButton = new JButton(new ImageIcon("Images\\Back.png"));
            backButton.setBorderPainted(false);
            backButton.setFocusPainted(false);
            backButton.setBounds(890, 360, 162, 51);
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
                    finishTurnButton.setEnabled(true);
                    enableMyCards();
                }
            });
            gameBackgroundLabel.add(backButton);

            cardBigLabel.setIcon(card.getLargeImage());
            gameBackgroundLabel.revalidate();
            gameBackgroundLabel.repaint();
            cardBigLabel.setVisible(true);
            setChosenLarge(cardBigLabel);
            setChosenCard(card);
        }
    }
}

