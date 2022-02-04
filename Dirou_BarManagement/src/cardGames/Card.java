/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardGames;

/**
 *
 * @author thibo
 */

/** Une carte avec une hauteur et un symbole */
public class Card {
    
    public final String faceName,suit;
    
    public static String [] suitList = {"spade","heart","diamond","club"};
    public static String [] faceNameList = {"7","8","9","10","jack","queen","king","ace"};
    
    /* Constructeur */
    
    public Card(String suit,String faceName){
        this.suit = suit;
        this.faceName=faceName;
    }   
    
    @Override
    public String toString(){
        return faceName+" of "+suit;
    }    
    
}
