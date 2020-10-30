package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class Bar extends JLabel {
    boolean loose;
    boolean isEmpty;

    int vida;
    int mana;

    public Bar(ImageIcon color) {
        super(color);
        int vida = 1000;
        int mana = 1000;

    }
    public void looseVida(int valor, Bar x){
        if(x.getVida()-valor<=0){
            loose = true;
        }
        else{
            x.setVida(x.getVida()-valor);
            x.setBounds(x.getX(),x.getY(),x.getWidth(),getHeight()-valor);
        }
    }
    public void looseMana(int valor, Bar x){
        if(x.getMana()-valor<=0){
            isEmpty = true;
        }
        else{
            x.setMana(x.getMana()-valor);
            x.setBounds(x.getX(),x.getY(),x.getWidth(),getHeight()-valor);
        }
    }
    public void winVida(int valor, Bar x){
        if(x.getVida()+valor>=1000){
            x.setBounds(x.getX(),x.getY(),x.getWidth(),1000);
            x.setVida(1000);
        }
        else{
            x.setVida(x.getVida()+valor);
            x.setBounds(x.getX(),x.getY(),x.getWidth(),getHeight()+valor);
        }
    }
    public void winMana(int valor, Bar x){
        if(x.getMana()+valor<=0){
            isEmpty = true;
        }
        else{
            x.setMana(x.getMana()-valor);
            x.setBounds(x.getX(),x.getY()-valor,x.getWidth(),getHeight()-valor);
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