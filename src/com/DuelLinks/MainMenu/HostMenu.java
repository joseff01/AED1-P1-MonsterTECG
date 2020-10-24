package com.DuelLinks.MainMenu;

import com.DuelLinks.CardGameplay.GameplayMenu;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HostMenu implements Runnable{

    JLabel socketLabel;

    JPanel mainPanel;

    ServerSocket listenSocket;

    Socket clientSocket;



    public HostMenu(JLabel socketLabel,JPanel mainPanel){

        this.mainPanel = mainPanel;

        this.socketLabel = socketLabel;

        setListenSocket();
        socketLabel.setText("My Socket: " + ListenSocketNum);

        Thread ParallelThread = new Thread(this);

        ParallelThread.start();

    }

    private void setListenSocket(){
        try{
            listenSocket = new ServerSocket(ListenSocketNum);
        } catch (IOException e) {
            ListenSocketNum++;
            setListenSocket();
        }
    }

    public void setClientSocket(String ip, int socket){
        try {
            clientSocket = new Socket(ip, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        boolean start = false;

        String startGameFlagJSON;

        while(!start) {
            try {

                Socket EntrySocket = listenSocket.accept();

                DataInputStream StreamInput = new DataInputStream(EntrySocket.getInputStream());

                startGameFlagJSON = (String) StreamInput.readUTF();

                ObjectMapper objectMapper = new ObjectMapper();

                StartGameFlag startGameFlag = objectMapper.readValue(startGameFlagJSON,StartGameFlag.class);

                setClientSocket(startGameFlag.getIp(),startGameFlag.getSocket());

                start = true;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        mainPanel.removeAll();
        mainPanel.validate();
        mainPanel.repaint();

        GameplayMenu gameplayMenu = new GameplayMenu(mainPanel,listenSocket,clientSocket,true);

    }

    int ListenSocketNum = 10000;

    public int getListenSocketNum() {
        return ListenSocketNum;
    }

}
