package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class MonsterCard extends Card{

    private int attackDamage;

    private int ManaRequirement;

    public MonsterCard(ImageIcon smallImg, ImageIcon largeImg, int AttackDamage, int manaReq){

        super(smallImg);

        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(null);

        this.setSmallImage(smallImg);
        this.setIcon(this.getSmallImage());

        this.setLargeImage(largeImg);

        this.attackDamage = AttackDamage;

        this.ManaRequirement = manaReq;

    }

    public MonsterCard(JsonMonsterCard jsonMonsterCard){

        super(new ImageIcon(jsonMonsterCard.getSmallImage()));

        this.attackDamage = jsonMonsterCard.getAttackDamage();
        this.ManaRequirement = jsonMonsterCard.getManaRequirement();
        this.setSmallImage(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonMonsterCard.getLargeImage()));
    }




}
