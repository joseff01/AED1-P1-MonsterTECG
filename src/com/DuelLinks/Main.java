package com.DuelLinks;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

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

        JButton hostGameButton = new JButton("HostGame");
        hostGameButton.setBounds(475,425,300,50);

        JButton joinGameButton = new JButton("JoinGame");
        joinGameButton.setBounds(475,505,300,50);

        JLabel logoLabel = new JLabel(new ImageIcon("Images\\DuelLinksLogo.png"));
        logoLabel.setBounds(256,20,737,419);

        backgroundLabel.add(hostGameButton);
        backgroundLabel.add(joinGameButton);
        backgroundLabel.add(logoLabel);






        mainWindow.validate();
        mainWindow.repaint();



    }
}
