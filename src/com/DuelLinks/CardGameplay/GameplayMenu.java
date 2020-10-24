package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameplayMenu {

    JPanel mainPanel;

    ServerSocket mySocket;

    Socket opponentSocket;

    public GameplayMenu(JPanel mainPanel, ServerSocket listenSocket, Socket clientSocket, boolean FirstTurn) {
        this.mainPanel = mainPanel;
        mySocket = listenSocket;
        opponentSocket = clientSocket;

    }
}
