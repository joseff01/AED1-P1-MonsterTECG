package com.DuelLinks.MainMenu;

import com.DuelLinks.CardGameplay.GameplayMenu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        ServerSocket mySocket;

        Socket opponentSocket;

        // mainWindow Setup
        JFrame mainWindow = new JFrame();

        mainWindow.setSize(1250,800);
        mainWindow.setLocation(50,50);
        mainWindow.setTitle("Yu-Gi-Oh! Duel Links V0.1");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);

        // mainPanel Setup
        JPanel mainPanel = new JPanel();
        mainWindow.add(mainPanel);

        // backgroundLabel Setup

        JLabel backgroundLabel = new JLabel(new ImageIcon("Images\\backgroundLabelimg.png"));
        backgroundLabel.setLayout(null);
        mainPanel.add(backgroundLabel);

        JButton hostGameButton = new JButton("Host Game");
        hostGameButton.setBounds(475,425,300,50);
        hostGameButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));

        class HostGameEvent implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e){

                backgroundLabel.removeAll();

                String ip = "127.0.0.1";

                JLabel ipLabel = new JLabel("My Ip: " + ip);
                ipLabel.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
                ipLabel.setForeground(Color.WHITE);
                ipLabel.setBounds(500,250,500,100);
                backgroundLabel.add(ipLabel);

                JLabel socketLabel = new JLabel("My Socket: ");
                socketLabel.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
                socketLabel.setForeground(Color.WHITE);
                socketLabel.setBounds(500,300,500,100);
                backgroundLabel.add(socketLabel);

                JLabel waitingLabel = new JLabel("Waiting For Players...");
                waitingLabel.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
                waitingLabel.setForeground(Color.WHITE);
                waitingLabel.setBounds(500,350,500,100);
                backgroundLabel.add(waitingLabel);

                HostMenu hostMenu = new HostMenu(socketLabel,mainPanel);
                
                mainWindow.validate();
                mainWindow.repaint();

            }
        }

        HostGameEvent hostGame = new HostGameEvent();
        hostGameButton.addActionListener(hostGame);

        JButton enterGameButton = new JButton("Join Game");
        enterGameButton.setBounds(475,505,300,50);
        enterGameButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));

        class EnterGameMenuEvent implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e){

                backgroundLabel.removeAll();

                String ip = "127.0.0.1";

                JLabel ipLabel = new JLabel("Host Ip:");
                ipLabel.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
                ipLabel.setForeground(Color.WHITE);
                ipLabel.setBounds(450,250,500,100);
                backgroundLabel.add(ipLabel);

                JLabel socketLabel = new JLabel("Host Socket:");
                socketLabel.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));
                socketLabel.setForeground(Color.WHITE);
                socketLabel.setBounds(450,325,500,100);
                backgroundLabel.add(socketLabel);

                JTextField ipText = new JTextField();
                ipText.setBounds(650,290,250,25);
                ipText.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,22));
                backgroundLabel.add(ipText);

                JTextField socketText = new JTextField();
                socketText.setBounds(650,365,250,25);
                socketText.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,22));
                backgroundLabel.add(socketText);

                JButton joinGameButton = new JButton("Join");
                joinGameButton.setBounds(600,450,150,50);
                joinGameButton.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,25));

                JoinMenu joinMenu = new JoinMenu();

                class JoinGameEvent implements ActionListener{

                    @Override
                    public void actionPerformed(ActionEvent i){

                        try{

                            if (! ipText.getText().equals("127.0.0.1")){
                                return;
                            }

                            try{
                                if (Integer.parseInt(socketText.getText()) < 10000) {
                                    return;
                                }

                            } catch (NumberFormatException numberFormatException) {
                                return;
                            }

                            Socket clientSocket = new Socket(ipText.getText(),Integer.parseInt(socketText.getText()));

                            StartGameFlag startGameFlag = new StartGameFlag("127.0.0.1", joinMenu.getListenSocketNum());

                            DataOutputStream streamOutput = new DataOutputStream(clientSocket.getOutputStream());

                            ObjectMapper objectMapper = new ObjectMapper();
                            String startGameFlagJSON = objectMapper.writeValueAsString(startGameFlag);

                            streamOutput.writeUTF(startGameFlagJSON);

                            streamOutput.close();

                            mainPanel.removeAll();
                            mainPanel.validate();
                            mainPanel.repaint();

                            GameplayMenu gameplayMenu = new GameplayMenu(mainPanel,joinMenu.getListenSocket(),Integer.parseInt(socketText.getText()),false);




                        } catch (IOException unknownHostException) {
                            unknownHostException.printStackTrace();
                        }

                    }

                }

                JoinGameEvent joinGame = new JoinGameEvent();
                joinGameButton.addActionListener(joinGame);
                backgroundLabel.add(joinGameButton);

                mainWindow.validate();
                mainWindow.repaint();

            }
        }

        EnterGameMenuEvent EnterGameMenu = new EnterGameMenuEvent();
        enterGameButton.addActionListener(EnterGameMenu);

        JLabel logoLabel = new JLabel(new ImageIcon("Images\\DuelLinksLogo.png"));
        logoLabel.setBounds(256,20,737,419);

        backgroundLabel.add(hostGameButton);
        backgroundLabel.add(enterGameButton);
        backgroundLabel.add(logoLabel);


        mainWindow.validate();
        mainWindow.repaint();




    }

}
