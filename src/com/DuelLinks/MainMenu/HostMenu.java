package com.DuelLinks.MainMenu;

import com.DuelLinks.CardGameplay.GameplayMenu;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase creada para que reciba el mensaje de iniciación del juego de parte
 * del jugador que se quiere unir
 *
 * @Author Jose Retana
 */

public class HostMenu implements Runnable{

    JLabel socketLabel;

    JPanel mainPanel;

    ServerSocket listenSocket;

    Socket clientSocket;

    DataOutputStream outputStream;

    /**
     *
     * @param socketLabel
     * @param mainPanel
     * @authors Jose Retana
     *
     */
    public HostMenu(JLabel socketLabel,JPanel mainPanel){

        this.mainPanel = mainPanel;

        this.socketLabel = socketLabel;

        setListenSocket();
        socketLabel.setText("My Socket: " + ListenSocketNum);

        Thread ParallelThread = new Thread(this);

        ParallelThread.start();

    }

    /**
     * @authors Jose Retana
     */
    private void setListenSocket(){
        try{
            listenSocket = new ServerSocket(ListenSocketNum);
        } catch (IOException e) {
            ListenSocketNum++;
            setListenSocket();
        }
    }

    /**
     * @Authors: Jose Retana
     */
    @Override
    public void run() {

        boolean start = false;

        String startGameFlagJSON;

        StartGameFlag startGameFlag = null;

        try {

                Socket EntrySocket = listenSocket.accept();

                DataInputStream StreamInput = new DataInputStream(EntrySocket.getInputStream());

                startGameFlagJSON = (String) StreamInput.readUTF();

                ObjectMapper objectMapper = new ObjectMapper();

                startGameFlag = objectMapper.readValue(startGameFlagJSON,StartGameFlag.class);

                EntrySocket.close();


        } catch (IOException e) {
                e.printStackTrace();
        }

        mainPanel.removeAll();
        mainPanel.validate();
        mainPanel.repaint();

        GameplayMenu gameplayMenu = new GameplayMenu(mainPanel,listenSocket,startGameFlag.getSocket(),true);

    }

    int ListenSocketNum = 10000;

}
