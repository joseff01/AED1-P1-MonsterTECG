package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.*;

/**
 * At. Jose Antonio Retana
 * Obtiene informacion del archivo Cards.Jason y almacena la informacion;
 */
public class JsonMonsterCard {

    private int attackDamage;

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    private boolean dragon;

    private String id;

    /**
     * @author Jose Antonio Retana
     * @param smallImg
     * @param largeImg
     * @param AttackDamage
     * @param manaReq
     * @param name
     * @param dragon
     * @param id
     */
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

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getSmallImage() {
        return smallImage;
    }

    /**
     * @author Jose Antonio Retana
     * @param smallImage
     */
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getLargeImage() {
        return largeImage;
    }

    /**
     * @author Jose Antonio Retana
     * @param largeImage
     */
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public int getManaRequirement() {
        return ManaRequirement;
    }

    /**
     * @author Jose Antonio Retana
     * @param manaRequirement
     */
    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * @author Jose Antonio Retana
     * @param attackDamage
     */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @author Jose Antonio Retana
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param dragon
     */
    public void setDragon(boolean dragon) {
        this.dragon = dragon;
    }

    /**
     * @author Jose Antonio Retana
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @author Jose Antonio Retana
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

}
