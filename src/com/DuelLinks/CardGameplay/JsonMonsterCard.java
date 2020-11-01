package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class JsonMonsterCard {

    private int attackDamage;

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    public JsonMonsterCard(String smallImg, String largeImg, int AttackDamage, int manaReq){

        this.attackDamage = AttackDamage;

        this.largeImage = largeImg;

        this.ManaRequirement = manaReq;

        this.smallImage = smallImg;

    }

    public JsonMonsterCard(){
        super();
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public int getManaRequirement() {
        return ManaRequirement;
    }

    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}
