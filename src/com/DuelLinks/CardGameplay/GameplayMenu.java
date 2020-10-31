package com.DuelLinks.CardGameplay;

import com.DuelLinks.LinearDataStructures.DoubleCircularList.DoubleCircularList;

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

    Bar vidaBar;
    Bar vidaBar2;
    Bar manaBar;
    Bar manaBar2;


    int opponentSocketNum;

    boolean myTurn;

    JLabel gameBackgroundLabel;

    DoubleCircularList<Card> myHand = new DoubleCircularList<Card>();

    public GameplayMenu(JPanel mainPanel, ServerSocket mySocket, int opponentSocketNum, boolean myTurn) {

        this.mainPanel = mainPanel;
        this.mySocket = mySocket;
        this.opponentSocketNum = opponentSocketNum;
        this.myTurn = myTurn;

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


        MonsterCard testCard = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        myHand.addLast(testCard);
        MonsterCard testCard2 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        myHand.addLast(testCard2);
        MonsterCard testCard3 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        myHand.addLast(testCard3);
        MonsterCard testCard4 = new MonsterCard(new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),new ImageIcon("Images\\SmallCards\\MonsterCP\\cpBlueDragon.png"),1,1);
        myHand.addLast(testCard4);

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
