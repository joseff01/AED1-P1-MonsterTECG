package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameplayMenu {

    JPanel mainPanel;

    ServerSocket mySocket;

    int opponentSocketNum;

    boolean myTurn;

    JLabel gameBackgroundLabel;

    int x = 165;

    public GameplayMenu(JPanel mainPanel, ServerSocket mySocket, int opponentSocketNum, boolean myTurn) {

        this.mainPanel = mainPanel;
        this.mySocket = mySocket;
        this.opponentSocketNum = opponentSocketNum;
        this.myTurn = myTurn;

        gameBackgroundLabel = new JLabel(new ImageIcon("Images\\FondoJuego.png"));
        gameBackgroundLabel.setLayout(null);
        mainPanel.add(gameBackgroundLabel);

        finishTurnButton = new JButton("Finish Turn");
        finishTurnButton.setBounds(870,520,170,50);
        finishTurnButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,18));

        MonsterCard testCard = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard2 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard3 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard4 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard5 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard6 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard7 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        MonsterCard testCard8 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        this.addCardToHand(testCard);
        this.addCardToHand(testCard2);
        this.addCardToHand(testCard3);
        this.addCardToHand(testCard4);
        this.addCardToHand(testCard5);
        this.addCardToHand(testCard6);
        this.addCardToHand(testCard7);
        this.addCardToHand(testCard8);


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

    public void addCardToHand(Card card){

        card.setBounds(x,580,120,175);
        x = x + 135;
        gameBackgroundLabel.add(card);


    }

    public void removeCardFromHand(Card card){

        gameBackgroundLabel.remove(card);



    }

    JButton finishTurnButton;
}
