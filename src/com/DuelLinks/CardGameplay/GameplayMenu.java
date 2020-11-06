package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.*;
import com.DuelLinks.LinearDataStructures.DoubleCircularList.DoubleCircularList;
import com.DuelLinks.LinearDataStructures.DoubleList.DoubleList;
import com.DuelLinks.LinearDataStructures.Stack.Stack;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class GameplayMenu {

    public JPanel mainPanel;

    private ServerSocket mySocket;

    private boolean pressed = false;

    public JButton finishTurnButton;

    public JButton logButton;

    public Bar myLifeBar;
    public Bar enemyLifeBar;
    public Bar myManaBar;
    public Bar enemyManaBar;

    public JLabel endGameMessageLabel;

    public int opponentSocketNum;

    public boolean flagUse = false;

    public boolean myTurn;

    private Card chosenCard;

    private JButton chosenLarge;

    public JLabel gameBackgroundLabel;

    public DoubleCircularList<Card> allCards = new DoubleCircularList<Card>();

    public DoubleCircularList<Card> myHand = new DoubleCircularList<Card>();

    private JLabel myDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    private Stack myDeck = new Stack(20);
    private int myDeckLength = 20;
    private JLabel myDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    private JLabel myDeckLengthLabel = new JLabel(String.valueOf(myDeckLength), SwingConstants.CENTER);

    public int amountMyTrapCards = 0;
    public JLabel myTrapCards = new  JLabel(new ImageIcon("Images\\cpAtras.png"));
    public JLabel myTrapLengthLabel = new JLabel(String.valueOf(amountMyTrapCards), SwingConstants.CENTER);


    private DoubleCircularList<JLabel> enemyHand = new DoubleCircularList<JLabel>();
    private int enemyHandXPosition = 955;

    public JLabel opponentDiscardPile = new JLabel(new ImageIcon("Images\\cpAtras.png"));

    private int enemyDeckLength = 20;
    private JLabel enemyDeckLabel = new JLabel(new ImageIcon("Images\\cpAtras.png"));
    private JLabel enemyDeckLengthLabel = new JLabel(String.valueOf(enemyDeckLength), SwingConstants.CENTER);

    public int amountEnemyTrapCards = 0;
    private JLabel enemyTrapCards = new  JLabel(new ImageIcon("Images\\cpAtras.png"));
    private JLabel enemyTrapLengthLabel = new JLabel(String.valueOf(amountEnemyTrapCards), SwingConstants.CENTER);

    public DoubleList<String> logList = new DoubleList<String>("The game has started!\n");

    private JButton cardBigLabel;

    public Message sendMessage;

    public volatile boolean closeCardFlag = false;

    public volatile boolean firstTurnFlag = true;

    public volatile boolean flagScapegoat = false;

    public volatile boolean flagMessengerOfPeace = false;

    public volatile boolean flagFightingSpirit = false;

    public volatile boolean flagDarkGrimoire = false;

    public volatile boolean flagWildMonster = false;
    boolean onlyMonsters = false;
    int wildNum = 0;
    int suma = 0;
    String card1 = null;
    String imageUsed1 = null;
    String card2 = null;
    String imageUsed2 = null;


    public volatile boolean flagCurseOfAnubis = false;

    public volatile boolean flagLetMeIn = false;

    public volatile boolean flagSpellbinding = false;

    public volatile boolean flagTrapEyeOfTruth = false;

    public volatile boolean flagTrapLifeRegeneration = false;

    public volatile boolean flagTrapMagesFortress = false;

    public volatile boolean flagTrapMirrorForce = false;

    public volatile boolean flagTrapWrathOfTheStarDragons = false;

    public volatile boolean flagTrapShadowEye = false;

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

        this.endGameMessageLabel = new JLabel();
        endGameMessageLabel.setBounds(500,300,400,400);
        endGameMessageLabel.setVisible(false);
        gameBackgroundLabel.add(endGameMessageLabel);

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
            for (int i = 0; i < 8; i++){
                String cardJsonString = scanner.nextLine();
                JsonTrapCard JsonCard = objectMapper.readValue(cardJsonString, JsonTrapCard.class);
                TrapCard trapCard = new TrapCard(JsonCard);
                CardClick cardclick = new CardClick();
                trapCard.addActionListener(cardclick);
                allCards.addLast(trapCard);
            }
        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int i = 0;
        DoubleCircularList<Integer> integerSingleList = new DoubleCircularList<Integer>();

        while (i < 20) {
            int randomInt = random.nextInt(40);
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

        myTrapCards.setLayout(null);
        myTrapCards.setBounds(735, 390, 120, 175);
        myTrapLengthLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        myTrapLengthLabel.setForeground(Color.WHITE);
        myTrapLengthLabel.setBounds(40, 77, 40, 20);
        myTrapCards.setVisible(false);
        myTrapCards.add(myTrapLengthLabel);
        gameBackgroundLabel.add(myTrapCards);

        enemyTrapCards.setLayout(null);
        enemyTrapCards.setBounds(465, 185, 120, 175);
        enemyTrapLengthLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
        enemyTrapLengthLabel.setForeground(Color.WHITE);
        enemyTrapLengthLabel.setBounds(40, 77, 40, 20);
        enemyTrapCards.setVisible(false);
        enemyTrapCards.add(enemyTrapLengthLabel);
        gameBackgroundLabel.add(enemyTrapCards);

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

        addCardEnemyHand();
        addCardEnemyHand();
        addCardEnemyHand();
        addCardEnemyHand();

        myLifeBar = new Bar(true, true, this);
        myLifeBar.setBounds(10, 360, 55, 400);
        gameBackgroundLabel.add(myLifeBar);

        myManaBar = new Bar(false, true, this);
        myManaBar.setBounds(85, 660, 55, 400);
        gameBackgroundLabel.add(myManaBar);

        enemyManaBar = new Bar(false, false, this);
        enemyManaBar.setBounds(1085, 0, 55, 100);
        gameBackgroundLabel.add(enemyManaBar);

        enemyLifeBar = new Bar(true, false, this);
        enemyLifeBar.setBounds(1160, 0, 55, 400);
        gameBackgroundLabel.add(enemyLifeBar);

        logButton = new JButton(new ImageIcon("Images\\record.png"));
        logButton.setBounds(200, 420, 162, 51);
        gameBackgroundLabel.add(logButton);

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableMyCards();
                finishTurnButton.setEnabled(false);
                logButton.setEnabled(false);
                JFrame logFrame = new JFrame();
                logFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        enableMyCards();
                        finishTurnButton.setEnabled(true);
                        logButton.setEnabled(true);
                    }
                });
                logFrame.setSize(500,500);
                logFrame.setLocation(50,50);
                logFrame.setTitle("Event Log");
                logFrame.setResizable(true);
                logFrame.setVisible(true);
                logFrame.setLayout(new BorderLayout());

                JPanel logPanel = new JPanel();
                logPanel.setLayout(new BorderLayout());
                JTextArea logTextArea = new JTextArea();
                logTextArea.setEditable(false);
                logTextArea.setLineWrap(true);
                JScrollPane jScrollPane = new JScrollPane(logTextArea);

                for (int i = 0; i < logList.getLength(); i++){
                    logTextArea.append(logList.getValueAt(i));
                }

                logFrame.add(logPanel, BorderLayout.CENTER);
                logPanel.add(jScrollPane,BorderLayout.CENTER);

            }
        });

        finishTurnButton = new JButton(new ImageIcon("Images\\endTurn.png"));
        finishTurnButton.setBounds(200, 300, 208, 83);

        class EndTurnEvent implements ActionListener {

            GameplayMenu gameplayMenu;

            public EndTurnEvent(GameplayMenu gameplayMenu) {
                this.gameplayMenu = gameplayMenu;
            }


            @Override
            public void actionPerformed(ActionEvent i) {
                if(flagWildMonster&&onlyMonsters){
                    flagWildMonster = false;
                    onlyMonsters = true;
                    WildMonsterMessage wildMonsterMessage = new WildMonsterMessage(suma,card1,card2,null,imageUsed1,imageUsed2,null);
                    sendMessage = wildMonsterMessage;
                }
                if(flagMessengerOfPeace){
                    flagMessengerOfPeace= false;
                }

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
                    flagMagicTriangle = false;
                    WaitingState waitingState = new WaitingState(mySocket, finishTurnButton, gameplayMenu, opponentSocketNum);

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
            WaitingState waitingState = new WaitingState(mySocket, finishTurnButton,this, opponentSocketNum);

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
        if (myHand.getLength() < 8) {
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

    public void addSpecificCardMyHand(Card card) {
        if (myHand.getLength() < 8) {
            if (myDeckLength > 0) {
                myHand.addLast(card);
                this.displayMyHand();
            }
        }
    }

    public void addSpecificCardMyHand(Card card,boolean addActionListener) {
        if (myHand.getLength() < 8) {
            if (myDeckLength > 0) {
                if (addActionListener){
                    card.addActionListener(new CardClick());
                }
                myHand.addLast(card);
                this.displayMyHand();
            }
        }
    }

    public void removeCardMyHand(Card card) {
        this.removeMyHand();
        myHand.deleteValue(card);
        this.displayMyHand();
        if (!(card instanceof TrapCard)){
            myDiscardPile.setVisible(true);
        }
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

    public void addOneMyTrapCard(){
        amountMyTrapCards++;
        myTrapLengthLabel.setText(String.valueOf(amountMyTrapCards));
        myTrapCards.setVisible(true);
    }

    public void removeOneMyTrapCard(){
        if (amountMyTrapCards == 1){
            amountMyTrapCards--;
            myTrapCards.setVisible(false);
        } else if (amountMyTrapCards > 1){
            amountMyTrapCards--;
            myTrapLengthLabel.setText(String.valueOf(amountMyTrapCards));
        }
    }

    public void addOneEnemyTrapCard(){
        amountEnemyTrapCards++;
        enemyTrapLengthLabel.setText(String.valueOf(amountEnemyTrapCards));
        enemyTrapCards.setVisible(true);
    }

    public void removeOneEnemyTrapCard(){
        if (amountEnemyTrapCards == 1){
            amountEnemyTrapCards--;
            enemyTrapCards.setVisible(false);
        } else if (amountEnemyTrapCards > 1){
            amountEnemyTrapCards--;
            enemyTrapLengthLabel.setText(String.valueOf(amountMyTrapCards));
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

    public void startSnatchStealWaitingState(){
        SnatchStealWaitingState snatchStealWaitingState = new SnatchStealWaitingState(mySocket, finishTurnButton, this);
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
            if (flagWildMonster){
                onlyMonsters = true;
            }
            useButton.setBounds(890, 300, 162, 51);
            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int manaRequirement = card.getManaRequirement();
                    boolean isBigger = false;

                    if (flagDarkGrimoire) {
                        manaRequirement = manaRequirement * 2;
                    }
                    if (flagMessengerOfPeace && card instanceof MonsterCard) {
                        if (((MonsterCard) card).getAttackDamage() >= 100) {
                            isBigger = true;
                        }
                    }

                    if (myManaBar.isEnough(manaRequirement)) {
                        flagDarkGrimoire = false;
                        if (flagSpellbinding) {
                            if(flagCurseOfAnubis){
                                removeOneMyTrapCard();
                                removeOneEnemyTrapCard();
                                flagSpellbinding = false;
                                flagCurseOfAnubis = false;
                                flagUse = true;
                                JButton x = getChosenLarge();
                                sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                x.setIcon(null);
                                setChosenCard(null);
                                gameBackgroundLabel.remove(backButton);
                                gameBackgroundLabel.remove(useButton);
                                gameBackgroundLabel.revalidate();
                                gameBackgroundLabel.repaint();
                                cardBigLabel.setVisible(false);
                                finishTurnButton.setEnabled(true);
                                enableMyCards();


                            }else{
                                sendMessage = new SpellBindingMessage(manaRequirement, "Spellbinding Circle");
                                 logList.addLast("The enemy trap card Spellbinding Circle has been activated!\n");
                                myManaBar.loseMana(manaRequirement, true);
                                flagSpellbinding = false;
                                flagUse = true;
                                removeCardMyHand(card);
                                JButton chosencard = getChosenLarge();
                                chosencard.setIcon(null);
                                setChosenCard(null);
                                gameBackgroundLabel.remove(backButton);
                                gameBackgroundLabel.remove(useButton);
                                cardBigLabel.setVisible(false);
                                finishTurnButton.setEnabled(true);
                                enableMyCards();
                                addOneMyTrapCard();
                                gameBackgroundLabel.revalidate();
                                gameBackgroundLabel.repaint();
                            }

                        } else if (card instanceof MonsterCard) {
                            if (flagTrapWrathOfTheStarDragons) {
                                if (flagCurseOfAnubis) {
                                    sendMessage = new CurseOfAnubisMessage("Curse Of Anubis", "Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                    removeOneMyTrapCard();
                                    removeOneEnemyTrapCard();
                                    flagTrapWrathOfTheStarDragons = false;
                                    flagCurseOfAnubis = false;
                                    JButton x = getChosenLarge();
                                    x.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    gameBackgroundLabel.revalidate();
                                    gameBackgroundLabel.repaint();
                                    cardBigLabel.setVisible(false);
                                    finishTurnButton.setEnabled(true);
                                    flagUse = true;
                                    enableMyCards();
                                    return;
                                } else if (((MonsterCard) card).isDragon()) {
                                    flagTrapWrathOfTheStarDragons = false;
                                    if (!flagWildMonster) {
                                        sendMessage = new WrathOfTheStarDragonsMessage(manaRequirement, "Wrath of The Star Dragons");
                                        logList.addLast("The enemy trap card Wrath of The Star Dragons has been activated!\n");
                                    }
                                    flagUse = true;
                                    removeCardMyHand(card);
                                    JButton chosencard = getChosenLarge();
                                    chosencard.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    cardBigLabel.setVisible(false);
                                    showBigCard("Images\\BigCards\\Trap\\WrathStarBc.png");
                                    myManaBar.loseMana(manaRequirement, true);
                                    finishTurnButton.setEnabled(true);
                                    removeOneEnemyTrapCard();
                                    enableMyCards();
                                    return;
                                }
                            }if (flagTrapLifeRegeneration) {
                                if(flagCurseOfAnubis) {
                                    sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                    removeOneMyTrapCard();
                                    removeOneEnemyTrapCard();
                                    flagTrapLifeRegeneration = false;
                                    flagCurseOfAnubis = false;
                                    JButton x = getChosenLarge();
                                    flagUse = true;
                                    x.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    gameBackgroundLabel.revalidate();
                                    gameBackgroundLabel.repaint();
                                    cardBigLabel.setVisible(false);
                                    finishTurnButton.setEnabled(true);
                                    enableMyCards();
                                    return;
                                }else {
                                    flagTrapLifeRegeneration = false;
                                    if(!flagWildMonster) {
                                         logList.addLast("The enemy trap card Life Regeneration has been activated!\n");
                                        sendMessage = new LifeRegenerationMessage(manaRequirement, ((MonsterCard) card).getAttackDamage(), "Life Regeneration");
                                    }
                                    flagUse = true;
                                    removeCardMyHand(card);
                                    JButton chosencard = getChosenLarge();
                                    chosencard.setIcon(null);
                                    setChosenCard(null);
                                    gameBackgroundLabel.remove(backButton);
                                    gameBackgroundLabel.remove(useButton);
                                    cardBigLabel.setVisible(false);
                                    showBigCard("Images\\BigCards\\Trap\\LifeRegeBc.png");
                                    enemyLifeBar.gainLife(((MonsterCard) card).getAttackDamage(), false);
                                    myManaBar.loseMana(manaRequirement, true);
                                    finishTurnButton.setEnabled(true);
                                    removeOneEnemyTrapCard();
                                    enableMyCards();
                                    return;
                                }
                            }if (flagTrapMirrorForce) {
                                if(flagCurseOfAnubis) {
                                    sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                    removeOneMyTrapCard();
                                    removeOneEnemyTrapCard();
                                    flagTrapMirrorForce = false;
                                    flagCurseOfAnubis = false;
                                    flagUse = true;
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
                                    return;
                                }else {
                                    flagTrapMirrorForce = false;
                                    if(!flagWildMonster) {
                                        logList.addLast("The enemy trap card Mirror Force has been activated!\n");
                                        sendMessage = new MirrorForceMessage(manaRequirement, ((MonsterCard) card).getAttackDamage(), "Mirror Force");
                                    }
                                    showBigCard("Images\\BigCards\\Trap\\MirrorBc.png");
                                    myLifeBar.loseVida(((MonsterCard) card).getAttackDamage(), true);
                                    myManaBar.loseMana(manaRequirement, true);
                                    finishTurnButton.setEnabled(true);
                                    removeOneEnemyTrapCard();
                                    enableMyCards();
                                    return;
                                }
                            } if (!flagMagicTriangle) {
                                if (!isBigger) {
                                    if (flagFightingSpirit || flagScapegoat || flagWildMonster) {
                                        if (flagFightingSpirit) {
                                            flagUse = true;
                                            flagFightingSpirit = false;
                                            enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage() + 200, false);
                                            if(!flagWildMonster) {
                                                AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage() + 200, manaRequirement, card.getLargeImageString(), card.getCardName());
                                                sendMessage = attackMessage;
                                                logList.addLast("You have attacked the enemy with " + card.getCardName() + " for " + (((MonsterCard) card).getAttackDamage() + 200) + " and lost " + manaRequirement + " mana\n");
                                            }
                                            myManaBar.loseMana(manaRequirement, true);
                                            removeCardMyHand(card);
                                        } else if (flagScapegoat) {
                                            flagUse = true;
                                            flagScapegoat = false;
                                            if (((MonsterCard) card).getAttackDamage() - 100 > 0) {
                                                enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage() - 100, false);
                                                if(!flagWildMonster) {
                                                    AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage(), manaRequirement, card.getLargeImageString(), card.getCardName());
                                                    sendMessage = attackMessage;
                                                    logList.addLast("You have attacked the enemy with " + card.getCardName() + " for " + ((MonsterCard) card).getAttackDamage() + " and lost " + manaRequirement + " mana \n");
                                                }
                                                myManaBar.loseMana(manaRequirement, true);
                                                removeCardMyHand(card);
                                            }
                                        } else if (flagWildMonster) {
                                            if (wildNum==0) {
                                                enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage(), false);
                                                wildNum++;
                                                removeCardMyHand(card);
                                                card1 = card.getCardName();
                                                imageUsed1 = card.getLargeImageString();
                                                suma = ((MonsterCard) card).getAttackDamage();
                                            } else if (wildNum==1) {
                                                enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage(), false);
                                                wildNum++;
                                                removeCardMyHand(card);
                                                card2 = card.getCardName();
                                                imageUsed2 = card.getLargeImageString();
                                                suma = suma + ((MonsterCard) card).getAttackDamage();
                                            }else{
                                                enemyLifeBar.loseVida(((MonsterCard) card).getAttackDamage(), false);
                                                suma = suma + ((MonsterCard) card).getAttackDamage();
                                                System.out.println(suma);
                                                WildMonsterMessage wildMonsterMessage = new WildMonsterMessage(suma,card1,card2,card.getCardName(),imageUsed1,imageUsed2,card.getLargeImageString());
                                                sendMessage = wildMonsterMessage;
                                                wildNum = 0;
                                                suma = 0;
                                                flagWildMonster = false;
                                                flagUse = true;
                                                removeCardMyHand(card);
                                                imageUsed2 = null;
                                                imageUsed1 = null;
                                                card1 = null;
                                                card2 = null;
                                                onlyMonsters = false;
                                            }

                                        }
                                        if(flagTrapShadowEye){
                                            if(enemyLifeBar.getVida()<=300){
                                                if(flagCurseOfAnubis){
                                                    sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                                    removeOneMyTrapCard();
                                                    removeOneEnemyTrapCard();
                                                    flagTrapShadowEye = false;
                                                    flagCurseOfAnubis = false;
                                                    JButton x = getChosenLarge();
                                                    x.setIcon(null);
                                                    setChosenCard(null);
                                                    flagUse = true;
                                                    gameBackgroundLabel.remove(backButton);
                                                    gameBackgroundLabel.remove(useButton);
                                                    gameBackgroundLabel.revalidate();
                                                    gameBackgroundLabel.repaint();
                                                    cardBigLabel.setVisible(false);
                                                    finishTurnButton.setEnabled(true);
                                                    enableMyCards();}
                                                else {
                                                    flagTrapShadowEye = false;
                                                    myManaBar.loseMana(1000, true);
                                                    sendMessage = new ShadowOfEyesMessage(manaRequirement, "Shadow Of Eyes");
                                                    AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage(), manaRequirement, card.getLargeImageString(), card.getCardName());
                                                    sendMessage = attackMessage;
                                                }
                                            }
                                        }
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
                                        if(flagTrapShadowEye){
                                            if(enemyLifeBar.getVida()<=300){
                                                if(flagCurseOfAnubis) {
                                                    flagUse = true;
                                                    sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                                    removeOneMyTrapCard();
                                                    removeOneEnemyTrapCard();
                                                    flagTrapShadowEye = false;
                                                    flagCurseOfAnubis = false;
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
                                                else {
                                                    flagTrapShadowEye = false;
                                                    myManaBar.loseMana(1000, true);
                                                    sendMessage = new ShadowOfEyesMessage(manaRequirement, "Shadow Of Eyes");
                                                    AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage(), manaRequirement, card.getLargeImageString(), card.getCardName());
                                                    sendMessage = attackMessage;
                                                }
                                            }
                                        }
                                        if(!flagWildMonster) {
                                            AttackMessage attackMessage = new AttackMessage(((MonsterCard) card).getAttackDamage(), manaRequirement, card.getLargeImageString(), card.getCardName());
                                            sendMessage = attackMessage;
                                        }
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
                                        logList.addLast("You have attacked the enemy with " + card.getCardName() + " for " + ((MonsterCard) card).getAttackDamage() + " and lost " + manaRequirement + " mana \n");
                                    }
                                }
                            }
                        } else if (card instanceof SpellCard) {
                            if(!onlyMonsters) {
                                if (flagTrapEyeOfTruth) {
                                    if (flagCurseOfAnubis) {
                                        sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                        removeOneMyTrapCard();
                                        flagUse = true;
                                        removeOneEnemyTrapCard();
                                        flagTrapEyeOfTruth = false;
                                        flagCurseOfAnubis = false;
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
                                    } else {
                                        logList.addLast("The enemy trap card The Eye Of Truth has been activated!\n");
                                        flagTrapEyeOfTruth = false;
                                        sendMessage = new EyeOfTruthMessage(manaRequirement, 150, "The Eye Of Truth");
                                        flagUse = true;
                                        removeCardMyHand(card);
                                        JButton chosencard = getChosenLarge();
                                        chosencard.setIcon(null);
                                        setChosenCard(null);
                                        gameBackgroundLabel.remove(backButton);
                                        gameBackgroundLabel.remove(useButton);
                                        cardBigLabel.setVisible(false);
                                        showBigCard("Images\\BigCards\\Trap\\EyeTruthBc.png");
                                        myLifeBar.loseVida(150, true);
                                        myManaBar.loseMana(manaRequirement, true);
                                        finishTurnButton.setEnabled(true);
                                        removeOneEnemyTrapCard();
                                        enableMyCards();
                                        logList.addLast("You have lost 150 life points and wasted "+ card.getManaRequirement()+" mana\n");
                                    }

                                } else if (flagTrapMagesFortress) {
                                    if (flagCurseOfAnubis) {
                                        flagUse = true;
                                        sendMessage = new CurseOfAnubisMessage("Curse Of Anubis","Images\\BigCards\\Trap\\CurseAnubisBc.png");
                                        flagTrapMagesFortress = false;
                                        flagCurseOfAnubis = false;
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
                                        removeOneMyTrapCard();
                                        removeOneEnemyTrapCard();
                                    } else {
                                        logList.addLast("The enemy trap card Mage's Fortress has been activated!\n");
                                        flagTrapMagesFortress = false;
                                        sendMessage = new MagesFortressMessage(manaRequirement, "Mage's Fortress");
                                        removeCardMyHand(card);
                                        JButton chosencard = getChosenLarge();
                                        chosencard.setIcon(null);
                                        setChosenCard(null);
                                        gameBackgroundLabel.remove(backButton);
                                        gameBackgroundLabel.remove(useButton);
                                        cardBigLabel.setVisible(false);
                                        showBigCard("Images\\BigCards\\Trap\\MageFortBc.png");
                                        myManaBar.loseMana(manaRequirement, true);
                                        finishTurnButton.setEnabled(true);
                                        removeOneEnemyTrapCard();
                                        enableMyCards();
                                        logList.addLast("You have wasted "+ card.getManaRequirement()+" mana\n");
                                    }

                                } else {
                                    myManaBar.loseMana(manaRequirement, true);
                                    SpellMessage spellMessage = new SpellMessage(manaRequirement, card.getLargeImageString(), card.getCardName());
                                    sendMessage = spellMessage;

                                    if (card.getCardName().equals("Fighting Spirit")) {
                                         logList.addLast("You have activated the spell card " + card.getCardName() + " and lost " + card.getManaRequirement() + " mana\n");
                                        flagFightingSpirit = true;
                                    } else if (card.getCardName().equals("Pot Of Greed")) {
                                        logList.addLast("You have activated the spell card " + card.getCardName() + " and lost " + card.getManaRequirement() + " mana\n");
                                        logList.addLast("You now have two extra cards!\n");
                                        addCardMyHand();
                                        addCardMyHand();
                                     } else if (card.getCardName().equals("Remove Trap")){
                                    flagTrapWrathOfTheStarDragons = false;
                                    flagTrapMagesFortress = false;
                                    flagTrapLifeRegeneration = false;
                                    flagTrapEyeOfTruth = false;
                                    flagCurseOfAnubis= false;
                                    flagTrapMirrorForce = false;
                                    flagSpellbinding = false;
                                    amountEnemyTrapCards = 0;
                                    flagTrapShadowEye = false;
                                    enemyTrapLengthLabel.setText(String.valueOf(amountEnemyTrapCards));
                                    enemyTrapCards.setVisible(false);
                                    logList.addLast("All the set enemy trap cards disappeared!\n");
                                    logList.addLast("You have activated the spell card " + card.getCardName() + " and lost " + card.getManaRequirement() + " mana\n");
                                    } else if (card.getCardName().equals("Poison Of The Old Man")) {
                                        logList.addLast("You have activated the spell card " + card.getCardName() + " and lost " + card.getManaRequirement() + " mana\n");
                                        Random random = new Random();
                                        int randomInt = random.nextInt(6);
                                        myLifeBar.gainLife((randomInt + 1) * 50, true);
                                        logList.addLast("You have gained " + ((randomInt + 1) * 50) + " life points!\n");
                                    } else if (card.getCardName().equals("A Wild Monster Appears!")) {
                                        flagWildMonster = true;
                                    } else if (card.getCardName().equals("Snatch Steal")) {
                                        try {
                                             logList.addLast("You have activated the spell card " + card.getCardName() + " and lost " + card.getManaRequirement() + " mana\n");
                                            Socket ClientSocket = new Socket("127.0.0.1", opponentSocketNum);
                                            DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                                            ObjectMapper objectMapper = new ObjectMapper();
                                            SnatchStealMessage snatchStealMessage = new SnatchStealMessage(0, "Snatch Steal", card.getLargeImageString(), 0, true);
                                            String messageJson = objectMapper.writeValueAsString(snatchStealMessage);
                                            streamOutput.writeUTF(messageJson);
                                            streamOutput.close();

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

                                            startSnatchStealWaitingState();

                                            return;

                                        } catch (IOException j) {
                                            j.printStackTrace();
                                        }
                                    }else{
                                        logList.addLast("You have activated the spell card " + spellMessage.getCardName() + " using " + spellMessage.getOpponentManaUsed() + " mana\n");
                                    }
                                }
                                if (!flagLetMeIn) {
                                    flagUse = true;
                                }
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
                                logList.addLast("You have lost 150 life points and wasted "+ card.getManaRequirement()+" mana\n");

                            }else if (flagTrapMagesFortress) {
                                logList.addLast("The enemy trap card Mage's Fortress has been activated!\n");
                                flagTrapMagesFortress = false;
                                sendMessage = new MagesFortressMessage(manaRequirement, "Mage's Fortress");
                                flagUse = true;
                                  removeCardMyHand(card);
                                JButton chosencard = getChosenLarge();
                                chosencard.setIcon(null);
                                setChosenCard(null);
                                gameBackgroundLabel.remove(backButton);
                                gameBackgroundLabel.remove(useButton);
                                cardBigLabel.setVisible(false);
                                showBigCard("Images\\BigCards\\Trap\\MageFortBc.png");
                                myManaBar.loseMana(manaRequirement, true);
                                finishTurnButton.setEnabled(true);
                                removeOneEnemyTrapCard();
                                enableMyCards();
                            }
                        }else if (card instanceof TrapCard) {
                            if (!onlyMonsters) {
                                System.out.println(card.getCardName());
                                if (card.getCardName().equals("Curse Of Anubis")) {
                                    System.out.println("flag funciona");
                                    flagCurseOfAnubis = true;
                                    flagLetMeIn = true;
                                    removeOneMyTrapCard();
                                    removeOneEnemyTrapCard();
                                }
                                myManaBar.loseMana(manaRequirement, true);
                                TrapMessage trapMessage = new TrapMessage(manaRequirement, card.getLargeImageString(), card.getCardName());
                                sendMessage = trapMessage;
                                if (!flagLetMeIn) {
                                    flagUse = true;
                                }
                                removeCardMyHand(card);
                                JButton chosencard = getChosenLarge();
                                chosencard.setIcon(null);
                                setChosenCard(null);
                                gameBackgroundLabel.remove(backButton);
                                gameBackgroundLabel.remove(useButton);
                                cardBigLabel.setVisible(false);
                                finishTurnButton.setEnabled(true);
                                enableMyCards();
                                logList.addLast("You have set the trap card " + card.getCardName() + " on the battlefield and lost " + card.getManaRequirement() + " mana\n");

                                addOneMyTrapCard();
                                gameBackgroundLabel.revalidate();
                                gameBackgroundLabel.repaint();
                            }

                        }
                    }
                }

            });

            gameBackgroundLabel.add(useButton);
            backButton = new JButton(new ImageIcon("Images\\Back.png"));
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
