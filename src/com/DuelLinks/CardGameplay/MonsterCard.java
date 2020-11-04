package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MonsterCard extends Card{

    private int attackDamage;

    public MonsterCard(ImageIcon smallImg, ImageIcon largeImg, int AttackDamage, int manaReq){

        super(smallImg);

        this.attackDamage = AttackDamage;
        this.setManaRequirement(manaReq);
        this.setSmallImage(smallImg);
        this.setLargeImage(largeImg);
        this.setAttackDone(attackDamage);


    }

    public MonsterCard(JsonMonsterCard jsonMonsterCard){

        super(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setDisabledIcon(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.attackDamage = jsonMonsterCard.getAttackDamage();
        this.setManaRequirement(jsonMonsterCard.getManaRequirement());
        this.setSmallImage(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonMonsterCard.getLargeImage()));
        this.setAttackDone(attackDamage);


    }

}

