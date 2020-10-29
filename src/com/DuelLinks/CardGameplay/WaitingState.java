package com.DuelLinks.CardGameplay;

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

    public WaitingState(ServerSocket mySocket, JButton finishTurnButton){

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

                String startGameFlagJSON = (String) StreamInput.readUTF();

                //ObjectMapper objectMapper = new ObjectMapper();

                //StartGameFlag startGameFlag = objectMapper.readValue(startGameFlagJSON,StartGameFlag.class);


            } catch (IOException e) {
                e.printStackTrace();
            }

            finishTurnButton.setEnabled(true);


    }
}
