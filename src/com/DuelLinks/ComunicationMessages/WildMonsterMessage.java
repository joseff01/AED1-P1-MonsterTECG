package com.DuelLinks.ComunicationMessages;
/**
 * Mensaje de la carta de hechizo A Wild Monster Appears,el cual se convierte en un json para se transmitido
 * @author Mariana Navarro
 */
public class WildMonsterMessage extends Message{

    String enemy1;
    String image1;
    String enemy2;
    String image2;
    String enemy3;
    String image3;

    int myDamageTaken;

    /**
     *
     * @param myDamageTaken
     * @param enemy1
     * @param enemy2
     * @param enemy3
     * @param image1
     * @param image2
     * @param image3
     */
    public WildMonsterMessage(int myDamageTaken, String enemy1,String enemy2,String enemy3,String image1,String image2,String image3) {
        this.myDamageTaken = myDamageTaken;
        this.enemy1 = enemy1;
        this.image1 = image1;
        this.enemy2 = enemy2;
        this.image2 = image2;
        this.enemy3 = enemy1;
        this.image3 = image3;

    }

    public WildMonsterMessage(){}
    public String getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(String enemy1) {
        this.enemy1 = enemy1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getEnemy2() {
        return enemy2;
    }

    public void setEnemy2(String enemy2) {
        this.enemy2 = enemy2;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getEnemy3() {
        return enemy3;
    }

    public void setEnemy3(String enemy3) {
        this.enemy3 = enemy3;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public int getMyDamageTaken() {
        return myDamageTaken;
    }

    public void setMyDamageTaken(int myDamageTaken) {
        this.myDamageTaken = myDamageTaken;
    }


}
