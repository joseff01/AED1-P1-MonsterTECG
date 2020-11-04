package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MonsterCard extends Card{

    private int attackDamage;

    public int getAttackDamage() {
        return attackDamage;
    }

    public MonsterCard(JsonMonsterCard jsonMonsterCard){

        super(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setDisabledIcon(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.attackDamage = jsonMonsterCard.getAttackDamage();
        this.setManaRequirement(jsonMonsterCard.getManaRequirement());
        this.setSmallImage(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonMonsterCard.getLargeImage()));
        this.setLargeImageString(jsonMonsterCard.getLargeImage());
        this.setCardNameString(jsonMonsterCard.getName());


    }

}

