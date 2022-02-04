/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardGames;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author thibo
 */


/**
 *  Le jeu de belote est initialisé avec 1 table et 2 team de 2 joueur
 *  
 */

public final class BeloteGame {  
    
    /* Listes de carte : */
    public Stack<Card> deck = new Stack();
    public static ArrayList<Card> allCards = new ArrayList<>();
    public ArrayList<Card> team1WonCards = new ArrayList<>();
    public ArrayList<Card> team2WonCards = new ArrayList<>();
    public ArrayList<Card> cardsOnTable = new ArrayList<>();
    public int[] cardsOnTableValues = new int[4];  
    
    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;
    
    /* Joueurs : */
    
    public ArrayList<Player> playerOrder = new ArrayList<>();
    public ArrayList<Player> team1Players = new ArrayList<>();
    public ArrayList<Player> team2Players = new ArrayList<>();
    public Team team1;
    public Team team2;
    
    /* Autres Variables : */
    
    public int [] totalScore=new int[2];
    public int [] roundScore=new int[2];
    
    public String trump;
    public Player tookTrump;
    
    public int firstPlayer;
    
    public int bonusTeam1;
    public int bonusTeam2;
    
    public static Map<String,Integer> trumpValues = new HashMap<>();
    public static Map<String,Integer> notTrumpValues = new HashMap<>();
    
    public Team winners;
    
    /* Constructeur : */
    
    public BeloteGame(bar.Table table,Team team1,Team team2){
        
        for(int i=0;i<4;i++){
            for(int j=0;j<8;j++){
                allCards.add(new Card(Card.suitList[i],Card.faceNameList[j]));
            }
        }        
        shuffle();
        
        this.player1= new Player(table.peopleSitting.get(0),1,0);
        this.player2= new Player(table.peopleSitting.get(1),2,1);
        this.player3= new Player(table.peopleSitting.get(2),3,0);
        this.player4= new Player(table.peopleSitting.get(3),4,1);
        
        this.playerOrder.add(this.player1);
        this.playerOrder.add(this.player2);
        this.playerOrder.add(this.player3);
        this.playerOrder.add(this.player4);
        
        this.team1Players.add(this.player1);
        this.team1Players.add(this.player3);
        this.team2Players.add(this.player2);
        this.team2Players.add(this.player4);
        
        this.team1=team1;
        this.team2=team2;
        
        this.totalScore[0]=0;
        this.totalScore[1]=0;
        this.roundScore[0]=0;
        this.roundScore[1]=0;
        this.firstPlayer=1;
        this.trump="none";
        
    }    
    
    /** Fonction principale du jeu : */
    
    public void playGame(){
        while(this.totalScore[0]<=1010 && this.totalScore[1]<=1010){
            while(this.trump.equals("none")){   
                resetRound();
                Distribution();
            }
            
            
            bonnusChecking();
            for(int i=0;i<8;i++){
                playMove();
            }
            this.roundScore[this.playerOrder.get(this.firstPlayer-1).team]+=10;
            roundScoring();
            this.trump="none";
            

        }
        endGame();
    }
    
    /** Etape 1 : initialisation des Variables du Round */
    
    public void resetRound(){
        this.deck.clear();
        this.team1WonCards.clear();
        this.team2WonCards.clear();
        this.player1.hand.clear();
        this.player2.hand.clear();
        this.player3.hand.clear();
        this.player4.hand.clear();
        this.roundScore[0]=0;
        this.roundScore[1]=0;
        this.bonusTeam1=0;
        this.bonusTeam2=0;
        shuffle();
        this.trump="none";
    }
        
    
    /** Etape 2 : Distribution */
    
    public void Distribution(){
        for(int i=0;i<4;i++){
            distribute(this.playerOrder.get(i),3);
        }
        for(int i=0;i<4;i++){
            distribute(this.playerOrder.get(i),2);
        }
            
        for(int i=0;i<4;i++){
            if(this.playerOrder.get(i).acceptTrump(this.deck.peek().suit)){
                this.trump=this.deck.peek().suit;
                this.tookTrump=this.playerOrder.get(i);
                distribute(this.playerOrder.get(i),3);
                this.firstPlayer=this.playerOrder.get(i).id;
                switchOrder();
                break;
            }  
        }
        if(trump.equals("none")){
            for(int i=0;i<4;i++){
                this.trump=this.playerOrder.get(i).chooseTrump();
                if(trump.equals("none")==false){
                    this.tookTrump=this.playerOrder.get(i);
                    distribute(this.playerOrder.get(i),3);
                    this.firstPlayer=this.playerOrder.get(i).id;
                    switchOrder();
                    break;
                }
            }
        }
        for(int i=1;i<4;i++){
            distribute(this.playerOrder.get(i),3);
        }
    
    }
    
    /** Etape 3 : Les joueurs jouent leurs cartes */
    
    public void playMove(){
        this.cardsOnTable.add(this.playerOrder.get(0).play(cardsOnTable, trump));
        this.cardsOnTable.add(this.playerOrder.get(1).play(cardsOnTable,trump));
        this.cardsOnTable.add(this.playerOrder.get(2).play(cardsOnTable,trump));
        this.cardsOnTable.add(this.playerOrder.get(3).play(cardsOnTable,trump));
        moveWinner();
                
    }
    
    /** Etape 4 : Calcul du score du round */
    
    public void roundScoring(){
        
        
        for(int i = 0; i<this.team1WonCards.size();i++){
            if(this.team1WonCards.get(i).suit.equals(trump)){
                this.roundScore[0]+=trumpValues.get(this.team1WonCards.get(i).faceName);
            }
            else{
                this.roundScore[0]+=notTrumpValues.get(this.team1WonCards.get(i).faceName);
            }
        }
        for(int i = 0; i<this.team2WonCards.size();i++){
            if(this.team2WonCards.get(i).suit.equals(trump)){
                this.roundScore[1]+=trumpValues.get(this.team2WonCards.get(i).faceName);
            }
            else{
                this.roundScore[1]+=notTrumpValues.get(this.team2WonCards.get(i).faceName);
            }
        }

        if(this.roundScore[this.tookTrump.team]>82){
            if(this.roundScore[this.tookTrump.team]==162){
               System.out.println("Capot !");
               this.roundScore[this.tookTrump.team]+=90;
            }
            System.out.println("Team "+ (this.tookTrump.team+1)+" won that round with a score of " + this.roundScore[0]+ " to " + this.roundScore[1]);
        }
        else{
            this.roundScore[1-this.tookTrump.team]=160;
            this.roundScore[this.tookTrump.team]=0;
            System.out.println("Team "+ (1-this.tookTrump.team+1)+" won that round with a score of " +this.roundScore[0]+ " to " + this.roundScore[1]); 
        }
        
        this.roundScore[0]+=this.bonusTeam1;
        this.roundScore[1]+=this.bonusTeam2;
        
        
        totalScoring();
    }
    
    
    

    /* Sous fonctions : */
    
    /** Mélange du paquet de carte */
    public final void shuffle(){
        for(int i=0;i<32;i++){
            deck.push(allCards.get(i));
        }
        Collections.shuffle(deck);
    }
    
    /** Mise à jour du score total de la partie */
    public void totalScoring(){
        this.totalScore[0]+=this.roundScore[0];
        this.totalScore[1]+=this.roundScore[1];
    }
    
    /** Designation du vainqueur */
    public void endGame(){
        if(this.totalScore[0]>=this.totalScore[1]){
            System.out.println(this.team1Players.get(0).person.name+" and " +this.team1Players.get(1).person.name+ " won that game with a score of "+ this.totalScore[0]+" to " +this.totalScore[1]);
            this.winners=team1;
        }
        else{
            
            System.out.println(this.team2Players.get(0).person.name+" and " +this.team2Players.get(1).person.name+ " won that game with a score of "+ this.totalScore[0]+" to " +this.totalScore[1]);
            this.winners=team2;
        }       
    }
    
    /** Changement d'ordre (réalisé entre chaque partie, lorsqu'un joueur choisi l'atout et lorsque qu'un joueur remport un pli */
    public void switchOrder(){
        switch(this.firstPlayer){
            case 1:
                this.playerOrder.set(0, player1);
                this.playerOrder.set(1, player2);
                this.playerOrder.set(2, player3);
                this.playerOrder.set(3, player4);
                break;
            case 2:
                this.playerOrder.set(3, player1);
                this.playerOrder.set(0, player2);
                this.playerOrder.set(1, player3);
                this.playerOrder.set(2, player4);
                break;
            case 3:
                this.playerOrder.set(2, player1);
                this.playerOrder.set(3, player2);
                this.playerOrder.set(0, player3);
                this.playerOrder.set(1, player4);
                break;
            case 4:
                this.playerOrder.set(1, player1);
                this.playerOrder.set(2, player2);
                this.playerOrder.set(3, player3);
                this.playerOrder.set(0, player4);
                break;
        }   
    }
    
    /** Distribution de carte dans la main du joueur
     * @param player
     * @param howMany */
    public void distribute(Player player,int howMany){
        for(int i=0;i<howMany;i++){
            player.hand.add(this.deck.pop());
        }

    }
    
    /** détermine le gagnant aprés que les 4 joueurs ont posé leurs cartes */
        public void moveWinner(){
        int max=0;
        int idMax=0;
        for(int i=0;i<4;i++){
            if(this.cardsOnTable.get(i).suit.equals(trump)){
                this.cardsOnTableValues[i]=trumpValues.get(this.cardsOnTable.get(i).faceName);
                if(this.cardsOnTableValues[i]>max){
                    max=this.cardsOnTableValues[i];
                    idMax=i;
                }
            }
            else{
               this.cardsOnTableValues[i]=notTrumpValues.get(this.cardsOnTable.get(i).faceName);  
               if(this.cardsOnTableValues[i]>max){
                    max=this.cardsOnTableValues[i];
                    idMax=i;
                }
            }
        }
        if(this.playerOrder.get(idMax).team==1){
            for(int i=0;i<4;i++){
                this.team1WonCards.add(this.cardsOnTable.get(i));  
            }
        }
        else{
            for(int i=0;i<4;i++){
                this.team2WonCards.add(this.cardsOnTable.get(i));  
            }
        }
        this.cardsOnTable.clear();
        this.firstPlayer=this.playerOrder.get(idMax).id;
        switchOrder();
    }
    
    
    
    
    
    
    
    
    
    
    
    /** Recherche de bonnus dans les mains des joueurs apres la distribution */
    public void bonnusChecking(){
        
    }
    
}
    
    
    
    
    

