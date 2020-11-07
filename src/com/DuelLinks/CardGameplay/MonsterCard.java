package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * At. Jose Antonio Retana
 * Obtiene informacion del archivo Cards.Jason y almacena la informacion;
 */
public class MonsterCard extends Card{

    private int attackDamage;

    private boolean dragon;

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public boolean isDragon() {
        return dragon;
    }

    /**
     * @author Jose Antonio Retana
     * @param jsonMonsterCard
     */
    public MonsterCard(JsonMonsterCard jsonMonsterCard) {

        super(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setDisabledIcon(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.attackDamage = jsonMonsterCard.getAttackDamage();
        this.setManaRequirement(jsonMonsterCard.getManaRequirement());
        this.setSmallImage(new ImageIcon(jsonMonsterCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonMonsterCard.getLargeImage()));
        this.setLargeImageString(jsonMonsterCard.getLargeImage());
        this.setCardNameString(jsonMonsterCard.getName());
        this.dragon = jsonMonsterCard.isDragon();
        this.setId(Integer.parseInt(jsonMonsterCard.getId()));

    }

    /**
     * @author Jose Antonio Retana
     * @param smallImage
     * @param largeImage
     * @param largeImageString
     * @param cardName
     * @param attackDamage
     * @param dragon
     * @param manaRequirement
     * @param id
     */
    public MonsterCard(ImageIcon smallImage,ImageIcon largeImage, String largeImageString, String cardName, int attackDamage, boolean dragon, int manaRequirement, int id) {
        super(smallImage);
        this.setDisabledIcon(smallImage);
        this.attackDamage = attackDamage;
        this.setManaRequirement(manaRequirement);
        this.setSmallImage(smallImage);
        this.setLargeImage(largeImage);
        this.setLargeImageString(largeImageString);
        this.setCardNameString(cardName);
        this.dragon = dragon;
        this.setId(id);
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public MonsterCard createCopyCard(){
        return new MonsterCard(this.getSmallImage(),this.getLargeImage(),this.getLargeImageString(),this.getCardName(),this.getAttackDamage(),this.isDragon(),this.getManaRequirement(),this.getId());
    }

}

