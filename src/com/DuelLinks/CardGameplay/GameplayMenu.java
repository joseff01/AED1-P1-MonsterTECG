package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameplayMenu {

    JPanel mainPanel;

    ServerSocket mySocket;

    Socket opponentSocket;

    boolean myTurn;

    public GameplayMenu(JPanel mainPanel, ServerSocket mySocket, Socket opponentSocket, boolean myTurn) {

        this.mainPanel = mainPanel;
        this.mySocket = mySocket;
        this.opponentSocket = opponentSocket;
        this.myTurn = myTurn;

        finishTurnButton = new JButton("Finish Turn");
        finishTurnButton.setBounds(600,450,150,50);
        finishTurnButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));

        class EndTurnEvent implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent i){

                finishTurnButton.setEnabled(false);

                try {

                    DataOutputStream streamOutput = new DataOutputStream(opponentSocket.getOutputStream());
                    streamOutput.writeUTF("Future Info");
                    WaitingState waitingState = new WaitingState(mySocket,finishTurnButton);

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        }

        EndTurnEvent endTurnEvent = new EndTurnEvent();
        finishTurnButton.addActionListener(endTurnEvent);
        mainPanel.add(finishTurnButton);

        if (!myTurn){

            finishTurnButton.setEnabled(false);
            WaitingState waitingState = new WaitingState(mySocket,finishTurnButton);

        }

        mainPanel.validate();
        mainPanel.repaint();


    }

    JButton finishTurnButton;
}
