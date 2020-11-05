package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class Bar extends JLabel {
    boolean loose;

    int vida;
    int mana;

    public Bar(boolean type,boolean my) {
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
    public boolean isEnough(int valor){
        if(this.getMana()-valor<0){
            return false;
        }
        else{
            return true;
        }
    }
    public void loseVida(int valor,boolean bottom){
        int temp = ((valor * 400)/1000);

        if(this.getVida()-valor<=0){
            loose = true;
        }
        else if(bottom){
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}