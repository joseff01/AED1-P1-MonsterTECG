package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

public class MonsterCard extends Card{

    int attackDamage;

    public MonsterCard(ImageIcon smallImg, ImageIcon largeImg, int AttackDamage, int manaReq){

        super(smallImg);

        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(null);

        this.setSmallImage(smallImg);
        this.setIcon(this.getSmallImage());

        this.setLargeImage(largeImg);

        this.attackDamage = AttackDamage;

        this.setManaRequirement(manaReq);

    }



}
