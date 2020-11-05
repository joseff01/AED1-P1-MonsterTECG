package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class JsonMonsterCard {

    private int attackDamage;

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    private boolean dragon;

    private String id;

    public JsonMonsterCard(String smallImg, String largeImg, int AttackDamage, int manaReq, String name, boolean dragon, String id){

        this.attackDamage = AttackDamage;

        this.largeImage = largeImg;

        this.ManaRequirement = manaReq;

        this.smallImage = smallImg;

        this.name = name;

        this.dragon = dragon;

        this.id = id;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDragon() {
        return dragon;
    }

    public void setDragon(boolean dragon) {
        this.dragon = dragon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
