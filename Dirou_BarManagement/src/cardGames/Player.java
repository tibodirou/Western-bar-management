/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardGames;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author thibo
 */

/** Joueur réalisant ses propres choix lors d'une parie de belotte */
public class Player {
    int id;
    int team;
    public people.Client person;
    public ArrayList<Card> hand = new ArrayList<>();
    Random random = new Random();

    /* Constructeur : */
    
    public Player(people.Client person,int id,int team){
        this.person=person;
        this.id =id;
        this.team=team;
    }
    
    
    /* 3 fonctions générales : */
    
    /** Accepte ou non la carte en haut du paquet
    *
    * 3 sous fonction pour 3 différents niveaux d'inéligence du joueur
     * @param trump
     * @return 
    */
    public boolean acceptTrump(String trump){
        if(this.person.intelligenceLevel==0){
            return badAcceptTrump(trump);
        }
        if(this.person.intelligenceLevel==1){
            return averageAcceptTrump(trump);
        }
        else{
            return goodAcceptTrump(trump);
        }
    }
    
    /** Choisi aléatoirement un atout
     * @return  */
    public String chooseTrump(){
        int rdm = random.nextInt(5);
        switch(rdm){
                case 1:
                    return "heart";
                case 2:
                    return "spade";
                case 3:
                    return "club";
                case 4:
                    return "diamond"; 
                default:
                    return "none";
        }
    }
    
    /** Choix de la carte à jouer : 
    *
    * 3 sous fonction pour 3 différents niveaux d'inéligence du joueur
     * @param cardsOnTable
     * @param trump
     * @return 
    */
    public Card play(ArrayList<Card> cardsOnTable, String trump){
        if(this.person.intelligenceLevel==0){
            return badPlay(cardsOnTable, trump);
        }
        if(this.person.intelligenceLevel==1){
            return averagePlay(cardsOnTable, trump);
        }
        else{
            return goodPlay(cardsOnTable, trump);
        }    
    }
    
    
    
    
    
   /**       Les 3 fonctions play sont identiques 
    *
    *  -Regarde si une carte à deja été posée sur la table 
    *  si oui : une carte est choisie au hasard dans sa main
    *  si non : 
    *  -Regarde si il possede une carte ayant le meme symbole que la 1ere carte posée sur la table
    *  si oui : une carte du meme symbole est choisie dans sa main
    *  si non : il choisi une carte au hasard dans sa main
     * @param cardsOnTable
     * @param trump
     * @return 
    */  
    public Card badPlay(ArrayList<Card> cardsOnTable, String trump){
       Card badChoice;
       if(cardsOnTable.isEmpty()){
           int rdm = random.nextInt(this.hand.size());
           badChoice=this.hand.get(rdm);
           this.hand.remove(badChoice);
           return badChoice;
       }
       else{
           for(int i=0;i<this.hand.size();i++){
               if(this.hand.get(i).suit.equals(cardsOnTable.get(0).suit)){
                   badChoice=this.hand.get(i);
                   this.hand.remove(badChoice);
                   return badChoice;
               }
           }  
        }
       int rdm = random.nextInt(this.hand.size());
       badChoice=this.hand.get(rdm);
       this.hand.remove(badChoice);
       return badChoice;
    }
    public Card averagePlay(ArrayList<Card> cardsOnTable, String trump){
       Card badChoice;
       if(cardsOnTable.isEmpty()){
           int rdm = random.nextInt(this.hand.size());
           badChoice=this.hand.get(rdm);
           this.hand.remove(badChoice);
           return badChoice;
       }
       else{
           for(int i=0;i<this.hand.size();i++){
               if(this.hand.get(i).suit.equals(cardsOnTable.get(0).suit)){
                   badChoice=this.hand.get(i);
                   this.hand.remove(badChoice);
                   return badChoice;
               }
           }  
        }
       int rdm = random.nextInt(this.hand.size());
       badChoice=this.hand.get(rdm);
       this.hand.remove(badChoice);
       return badChoice;
    }
    public Card goodPlay(ArrayList<Card> cardsOnTable, String trump){
       Card badChoice;
       if(cardsOnTable.isEmpty()){
           int rdm = random.nextInt(this.hand.size());
           badChoice=this.hand.get(rdm);
           this.hand.remove(badChoice);
           return badChoice;
       }
       else{
           for(int i=0;i<this.hand.size();i++){
               if(this.hand.get(i).suit.equals(cardsOnTable.get(0).suit)){
                   badChoice=this.hand.get(i);
                   this.hand.remove(badChoice);
                   return badChoice;
               }
           }  
        }
       for(int i=0;i<this.hand.size();i++){
           if(hand.get(i).suit.equals(trump)){
              badChoice=this.hand.get(i);
              this.hand.remove(badChoice);
              return badChoice; 
           }
       }
       int rdm = random.nextInt(this.hand.size());
       badChoice=this.hand.get(rdm);
       this.hand.remove(badChoice);
       return badChoice;
    }
    
    
    
    
    /* Acceptation de l'atout : */
    
    /** Accepte au hasard
     * @param trump
     * @return  */
    public boolean badAcceptTrump(String trump){
        int rdm= random.nextInt(2);
        return rdm==1;
    }
    
    /** Accepte si il possede au moins 2 cartes du meme symbole dans sa main
     * @param trump
     * @return  */
    public boolean averageAcceptTrump(String trump){
        int cardsInTrump =0;
        for(int i=0;i<5;i++){
            if(this.hand.get(i).suit.equals(trump)){
                cardsInTrump+=1;
            }
        }
        return cardsInTrump>=2 ;
    }
    
    /** Accepte si il possede une des cartes maitresse de l'atout (Valet ou 9)
     * @param trump
     * @return  */
    public boolean goodAcceptTrump(String trump){
        int goodCardsInTrump =0;
        for(int i=0;i<5;i++){
            if(this.hand.get(i).suit.equals(trump) && (this.hand.get(i).faceName.equals("jack")||this.hand.get(i).faceName.equals("9"))){
                goodCardsInTrump+=1;
            }
        }
        return goodCardsInTrump>=1;
    }
    
    
}
