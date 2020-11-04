package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class Bar extends JLabel {
    boolean loose;
    boolean isEmpty;

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
    public boolean isEnough(int valor, Bar barMana, boolean flagMana){
        System.out.println(barMana.getMana());
        System.out.println(barMana.getMana()-valor);
        if(barMana.getMana()-valor<=0){
            System.out.println("hi");
            return false;
        }
        else{
            System.out.println("mby");
            looseMana(valor,barMana,flagMana);
            return true;
        }

    }
    public void looseVida(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);

        if(barTemp.getVida()-valor<=0){
            loose = true;
        }
        else if(flag){
            barTemp.setVida(barTemp.getVida()-valor);
            barTemp.setBounds(barTemp.getX(), barTemp.getY()+temp,barTemp.getWidth(),barTemp.getHeight());
            barTemp.setText(Integer.toString(getVida()));
        }
        else{
            barTemp.setVida(barTemp.getVida()-valor);
            barTemp.setBounds(barTemp.getX(), barTemp.getY(),barTemp.getWidth(),barTemp.getHeight()-temp);
            barTemp.setText(Integer.toString(getVida()));
        }
    }

    public void looseMana(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);
        if (!flag){
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),getHeight()-temp);
        }
        else{
            barTemp.setBounds(barTemp.getX(), barTemp.getY()+temp,barTemp.getWidth(),barTemp.getHeight());
        }
        barTemp.setMana(barTemp.getMana()-valor);
        barTemp.setText(Integer.toString(getMana()));
    }
    public void winVida(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);

        if(barTemp.getVida()+temp>=1000 && flag){
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),1000);
            barTemp.setVida(1000);
            barTemp.setText("1000");
        }
        else if(barTemp.getVida() + temp >= 1000 && !flag) {
            barTemp.setBounds(barTemp.getX(), 360, barTemp.getWidth(), barTemp.getHeight());
            barTemp.setVida(1000);
            barTemp.setText("1000");
        }
        else if(flag){
            barTemp.setVida(barTemp.getVida()+valor);
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),getHeight()+temp);
            barTemp.setText(Integer.toString(getVida()));
        }
        else{
            barTemp.setVida(barTemp.getVida()+valor);
            barTemp.setBounds(barTemp.getX(),barTemp.getY()-temp,barTemp.getWidth(),getHeight());
            barTemp.setText(Integer.toString(getVida()));
        }
    }

    public void winMana(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);

        if(barTemp.getMana()+temp>=1000 && flag){
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),1000);
            barTemp.setMana(1000);
            barTemp.setText("1000");
        }
        else if(barTemp.getMana() + temp >= 1000 && !flag) {
            barTemp.setBounds(barTemp.getX(), 360, barTemp.getWidth(), 1000);
            barTemp.setMana(1000);
            barTemp.setText("1000");
        }
        else if(flag){
            barTemp.setMana(barTemp.getMana()+valor);
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),barTemp.getHeight()+temp);
            barTemp.setText(Integer.toString(getMana()));
        }
        else{
            barTemp.setMana(barTemp.getMana()+valor);
            barTemp.setBounds(barTemp.getX(),barTemp.getY()-temp,barTemp.getWidth(),getHeight());
            barTemp.setText(Integer.toString(getMana()));
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