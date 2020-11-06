package com.DuelLinks.CardGameplay;

import com.DuelLinks.ComunicationMessages.EndGameMessage;
import com.DuelLinks.MainMenu.Main;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * At. Mariana Navarro Jiménez
 * Devuelve un objeto barra que contiene vida y mana
 * Tiene los metodos que reduce o aumenta la vida y el mana de la barra,
 * ademas detecta cuando el jugador gana o pierde la partida
 */
public class Bar extends JLabel {
    boolean lose;

    int vida;
    int mana;

    GameplayMenu gameplayMenu;

    /**
     *  * At. Mariana Navarro Jimenez
     * @param type
     * @param my
     * @param gameplayMenu
     */
    public Bar(boolean type,boolean my, GameplayMenu gameplayMenu) {
        this.gameplayMenu = gameplayMenu;

        if(type){
            super.setText("1000");
            super.setBackground(Color.GREEN);
            this.vida = 1000;
        }
        else{
            super.setText("250");
            this.mana = 250;
            super.setBackground(Color.CYAN);
        }

        super.setOpaque(true);
        super.setForeground(Color.DARK_GRAY);
        if(my){
            super.setVerticalAlignment(SwingConstants.TOP);
        }
        else{
            super.setVerticalAlignment(SwingConstants.BOTTOM);
        }


    }

    /**
     * At. Mariana Navarro Jimenez
     * @param valor
     * @return true o false
     */
    public boolean isEnough(int valor){
        if(this.getMana()-valor<0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * At. Mariana Navarro Jimenez
     * proboca que el jugador gane o pierda la partida al llegar a 0
     * @param valor
     * @param bottom
     */
    public void loseVida(int valor,boolean bottom){
        int temp = ((valor * 400)/1000);

        if(this.getVida()-valor<=0){
            if(this==gameplayMenu.enemyLifeBar) {
                gameplayMenu.finishTurnButton.setVisible(false);
                gameplayMenu.logButton.setVisible(false);
                gameplayMenu.hideMyCards();
                gameplayMenu.nextGame.setVisible(true);
                gameplayMenu.endResult.setText("You Won!");
                gameplayMenu.endGameMessageLabel.setVisible(true);
                gameplayMenu.endGameMessageLabel.revalidate();
                gameplayMenu.endGameMessageLabel.repaint();
                gameplayMenu.sendMessage = new EndGameMessage(false);
                gameplayMenu.nextGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Window win = SwingUtilities.getWindowAncestor(gameplayMenu.mainPanel);
                        win.dispose();
                    }
                });

            }else{
                gameplayMenu.finishTurnButton.setVisible(false);
                gameplayMenu.logButton.setVisible(false);
                gameplayMenu.hideMyCards();
                gameplayMenu.nextGame.setVisible(true);
                gameplayMenu.endResult.setText("You Lost.");
                gameplayMenu.endGameMessageLabel.setVisible(true);
                gameplayMenu.endGameMessageLabel.revalidate();
                gameplayMenu.endGameMessageLabel.repaint();
                gameplayMenu.sendMessage = new EndGameMessage(true);

            }

            try {
                Socket ClientSocket = new Socket("127.0.0.1", gameplayMenu.opponentSocketNum);
                DataOutputStream streamOutput = new DataOutputStream(ClientSocket.getOutputStream());
                ObjectMapper objectMapper = new ObjectMapper();
                String messageJson = objectMapper.writeValueAsString(gameplayMenu.sendMessage);
                streamOutput.writeUTF(messageJson);
                streamOutput.close();
                gameplayMenu.sendMessage = null;
                gameplayMenu.disableMyCards();

                WaitingState waitingState = new WaitingState(gameplayMenu.getMySocket(), gameplayMenu.finishTurnButton, gameplayMenu, gameplayMenu.opponentSocketNum);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(bottom){
            this.setVida(this.getVida()-valor);
            this.setBounds(this.getX(), this.getY()+temp,this.getWidth(),this.getHeight());
            this.setText(Integer.toString(getVida()));
        }
        else{
            this.setVida(this.getVida()-valor);
            this.setBounds(this.getX(), this.getY(),this.getWidth(),this.getHeight()-temp);
            this.setText(Integer.toString(getVida()));
        }
    }

    /**
     * At. Mariana Navarro Jimenez
     * @param valor
     * @param bottom
     */
    public void loseMana(int valor,boolean bottom){
        int temp = ((valor * 400)/1000);
        if(this.getMana()-valor<=0 && bottom){
            this.setBounds(this.getX(),800,this.getWidth(),getHeight());
            this.setMana(0);
            this.setText(Integer.toString(getMana()));
        }
        else if(this.getMana()-valor<=0 && !bottom){
            this.setBounds(this.getX(),getY(),this.getWidth(),0);
            this.setMana(0);
            this.setText(Integer.toString(getMana()));
        }
        else if (bottom){
            this.setBounds(this.getX(),this.getY()+temp,this.getWidth(),getHeight());
            this.setMana(this.getMana()-valor);
            this.setText(Integer.toString(getMana()));
        }
        else{
            this.setBounds(this.getX(), this.getY(),this.getWidth(),this.getHeight()-temp);
            this.setMana(this.getMana()-valor);
            this.setText(Integer.toString(getMana()));
        }

    }

    /**
     * At. Mariana Navarro Jimenez
     * @param valor
     * @param bottom
     */
    public void gainLife(int valor,boolean bottom){
        int temp = ((valor * 400)/1000);
        if(this.getVida()+valor>=1000 && bottom){
            this.setBounds(this.getX(), 360, this.getWidth(), this.getHeight());
            this.setVida(1000);
            this.setText("1000");
        }
        else if(this.getVida() + valor >= 1000 && !bottom) {
            this.setBounds(this.getX(),this.getY(),this.getWidth(),400);
            this.setVida(1000);
            this.setText("1000");
        }
        else if(bottom){
            this.setVida(this.getVida()+valor);
            this.setBounds(this.getX(),this.getY()-temp,this.getWidth(),getHeight());
            this.setText(Integer.toString(getVida()));
        }
        else{
            this.setVida(this.getVida()+valor);
            this.setBounds(this.getX(),this.getY(),this.getWidth(),getHeight()+temp);
            this.setText(Integer.toString(getVida()));
        }
    }

    /**
     * At. Mariana Navarro Jimenez
     * @param valor
     * @param bottom
     */

    public void gainMana(int valor,boolean bottom){
        int temp = ((valor * 400)/1000);

        if(this.getMana()+valor>=1000 && bottom){
            this.setBounds(this.getX(), 360, this.getWidth(), this.getHeight());
            this.setMana(1000);
            this.setText("1000");
        }
        else if(this.getMana() + valor>= 1000 && !bottom) {
            this.setBounds(this.getX(),this.getY(),this.getWidth(),400);
            this.setMana(1000);
            this.setText("1000");
        }
        else if(bottom){
            this.setMana(this.getMana()+valor);
            this.setBounds(this.getX(),this.getY()-temp,this.getWidth(),this.getHeight());
            this.setText(Integer.toString(getMana()));
        }
        else{
            this.setMana(this.getMana()+valor);
            this.setBounds(this.getX(),this.getY(),this.getWidth(),getHeight()+temp);
            this.setText(Integer.toString(getMana()));
        }
    }

    /**
     * At. Mariana Navarro Jimenez
     * @return
     */
    public int getVida() {
        return vida;
    }

    /**
     * At. Mariana Navarro Jimenez
     * @param vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * At. Mariana Navarro Jimenez
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * At. Mariana Navarro Jimenez
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

}