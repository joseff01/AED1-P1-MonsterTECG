package com.DuelLinks.CardGameplay;

import javax.swing.*;

public class Bar extends JLabel {
    boolean loose;
    boolean isEmpty;

    int vida;
    int mana;

    public Bar(ImageIcon color) {
        super(color);
        this.vida = 500;
        this.mana = 1000;

    }
    public void looseVida(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);

        if(barTemp.getVida()-valor<=0){
            loose = true;
        }
        else if(flag){
            barTemp.setVida(barTemp.getVida()-temp);
            barTemp.setBounds(barTemp.getX(), barTemp.getY(),barTemp.getWidth(),barTemp.getHeight()-temp);
        }
        else{
            barTemp.setVida(barTemp.getVida()-temp);
            barTemp.setBounds(barTemp.getX(), barTemp.getY()+temp,barTemp.getWidth(),barTemp.getHeight());
        }
    }

    public void looseMana(int valor, Bar barTemp,boolean flag){
        int temp = ((valor * 400)/1000);
        if(barTemp.getMana()-temp<=0){
            isEmpty = true;
        }
        else if (flag){
            barTemp.setMana(barTemp.getMana()-temp);
            barTemp.setBounds(barTemp.getX(),barTemp.getY(),barTemp.getWidth(),getHeight()-temp);
        }
        else{
            barTemp.setVida(barTemp.getMana()-temp);
            barTemp.setBounds(barTemp.getX(), barTemp.getY()+temp,barTemp.getWidth(),barTemp.getHeight());
        }
    }
    public void winVida(int valor, Bar x,boolean flag){
        int temp = ((valor * 400)/1000);

        if(x.getVida()+temp>=1000 && flag){
            x.setBounds(x.getX(),x.getY(),x.getWidth(),1000);
            x.setVida(1000);
        }
        else if(x.getVida() + temp >= 1000 && !flag) {
            x.setBounds(x.getX(), 360, x.getWidth(), x.getHeight());
            x.setVida(1000);
        }
        else if(flag){
            x.setVida(x.getVida()+temp);
            x.setBounds(x.getX(),x.getY(),x.getWidth(),getHeight()+temp);
        }
        else{
            x.setVida(x.getVida()+temp);
            x.setBounds(x.getX(),x.getY()+temp,x.getWidth(),getHeight());
        }
    }

    public void winMana(int valor, Bar x,boolean flag){
        int temp = ((valor * 400)/1000);

        if(x.getMana()+temp>=1000 && flag){
            x.setBounds(x.getX(),x.getY(),x.getWidth(),1000);
            x.setMana(1000);
        }
        else if(x.getMana() + temp >= 1000 && !flag) {
            x.setBounds(x.getX(), 360, x.getWidth(), x.getHeight());
            x.setMana(1000);
        }
        else if(flag){
            x.setMana(x.getMana()+temp);
            x.setBounds(x.getX(),x.getY(),x.getWidth(),getHeight()+temp);
        }
        else{
            x.setMana(x.getMana()+temp);
            x.setBounds(x.getX(),x.getY()+temp,x.getWidth(),getHeight());
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