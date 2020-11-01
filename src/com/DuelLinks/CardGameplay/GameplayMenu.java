package com.DuelLinks.CardGameplay;

import com.DuelLinks.LinearDataStructures.DoubleCircularList.DoubleCircularList;
import com.DuelLinks.LinearDataStructures.SingleList.SingleList;
import com.DuelLinks.LinearDataStructures.Stack.Stack;
import com.DuelLinks.MainMenu.StartGameFlag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.swing.interop.SwingInterOpUtils;

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

    Bar vidaBar;
    Bar vidaBar2;
    Bar manaBar;
    Bar manaBar2;


    int opponentSocketNum;

    boolean myTurn;

    JLabel gameBackgroundLabel;

    DoubleCircularList<Card> allCards = new DoubleCircularList<Card>();

    DoubleCircularList<Card> myHand = new DoubleCircularList<Card>();

    Stack Deck = new Stack(22);

    public GameplayMenu(JPanel mainPanel, ServerSocket mySocket, int opponentSocketNum, boolean myTurn) {

        this.mainPanel = mainPanel;
        this.mySocket = mySocket;
        this.opponentSocketNum = opponentSocketNum;
        this.myTurn = myTurn;

        try {
            Scanner scanner = new Scanner(new File("json\\Cards.json"));
            for(int i = 0; i < 22; i++){
                String cardJsonString = scanner.nextLine();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonMonsterCard JsonCard = objectMapper.readValue(cardJsonString, JsonMonsterCard.class);
                MonsterCard monsterCard = new MonsterCard(JsonCard);
                allCards.addLast(monsterCard);
            }
        } catch (FileNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int i = 0;
        DoubleCircularList<Integer> integerSingleList= new DoubleCircularList<Integer>();

        while(i < 20){
            int randomInt = random.nextInt(22);
            if (!integerSingleList.isValue(randomInt)){
                integerSingleList.addLast(randomInt);
                integerSingleList.print();
                System.out.println(randomInt);
                Deck.push(allCards.getValueAt(randomInt));
                i++;
            }
        }

        myHand.addLast((Card) Deck.pop());
        myHand.addLast((Card) Deck.pop());
        myHand.addLast((Card) Deck.pop());
        myHand.addLast((Card) Deck.pop());

        gameBackgroundLabel = new JLabel(new ImageIcon("Images\\FondoJuego.png"));
        gameBackgroundLabel.setLayout(null);
        mainPanel.add(gameBackgroundLabel);

        vidaBar = new Bar(new ImageIcon("Images\\Green.png"));
        vidaBar.setBounds(10, 360,75,400);
        gameBackgroundLabel.add(vidaBar);

        manaBar = new Bar(new ImageIcon("Images\\Cyan.png"));
        manaBar.setBounds(85, 360,75,400);
        gameBackgroundLabel.add(manaBar);

        manaBar2 = new Bar(new ImageIcon("Images\\Cyan.png"));
        manaBar2.setBounds(1085, 0,75,400);
        gameBackgroundLabel.add(manaBar2);

        vidaBar2 = new Bar(new ImageIcon("Images\\Green.png"));
        vidaBar2.setBounds(1160, 0,75,400);
        gameBackgroundLabel.add(vidaBar2);

        finishTurnButton = new JButton("Finish Turn");
        finishTurnButton.setBounds(870,520,170,50);
        finishTurnButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,18));

        displayMyHand();

        class EndTurnEvent implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent i){

                finishTurnButton.setEnabled(false);

                try {

                    Socket ClientSocket = new Socket("127.0.0.1",opponentSocketNum);
                    DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                    streamOutput.writeUTF("Future Info");
                    streamOutput.close();

                    WaitingState waitingState = new WaitingState(mySocket,finishTurnButton);

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        }

        EndTurnEvent endTurnEvent = new EndTurnEvent();
        finishTurnButton.addActionListener(endTurnEvent);
        gameBackgroundLabel.add(finishTurnButton);


        if (!myTurn){

            finishTurnButton.setEnabled(false);
            WaitingState waitingState = new WaitingState(mySocket,finishTurnButton);

        }

        mainPanel.validate();
        mainPanel.repaint();


    }


    public void removeMyHand(){
        if (!myHand.isEmpty()) {
            int length = myHand.getLength();
            for (int i = 0; i < length; i++) {
                gameBackgroundLabel.remove(myHand.getValueAt(i));
            }
        }
    }

    public void displayMyHand(){
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

    JButton finishTurnButton;
}
