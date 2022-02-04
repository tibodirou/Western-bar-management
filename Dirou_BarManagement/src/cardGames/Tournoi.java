/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardGames;
import bar.BarElements;
import java.util.ArrayList;
import people.ConsoleColors;
/**
 *
 * @author thibo
 */


/** un Tournoi prend une liste de teams de 2 en entrée */
public class Tournoi {
    
    public ArrayList <Team> teamsList= new ArrayList<>(); 
    public Team winningTeam;
    
    
    public Tournoi(ArrayList<Team> teams){
        this.teamsList=teams;      
    }
    
    /** déroulement du tournoi */
    public void generateGames(){
        annonceTournament();
        
        makeParticipantSit(teamsList.get(0),teamsList.get(1)) ;
        BeloteGame newGame1 = new BeloteGame(BarElements.table1,teamsList.get(0),teamsList.get(1));
        annoncematch(teamsList.get(0),teamsList.get(1));
        newGame1.playGame();
        newGame1.winners.points+=1;
        makeParticipantGetUp(teamsList.get(0),teamsList.get(1));
        
        makeParticipantSit(teamsList.get(0),teamsList.get(2)) ;
        BeloteGame newGame2 = new BeloteGame(BarElements.table1,teamsList.get(0),teamsList.get(2));
        annoncematch(teamsList.get(0),teamsList.get(2));
        newGame2.playGame();
        newGame2.winners.points+=1;
        makeParticipantGetUp(teamsList.get(0),teamsList.get(2));
        
        makeParticipantSit(teamsList.get(1),teamsList.get(2)) ;
        BeloteGame newGame3 = new BeloteGame(BarElements.table1,teamsList.get(1),teamsList.get(2));
        annoncematch(teamsList.get(1),teamsList.get(2));
        newGame3.playGame();
        newGame3.winners.points+=1;
        makeParticipantGetUp(teamsList.get(1),teamsList.get(2));
        
        declareWinner();
        
        annonceEndTournament();
        
    }
    /** les participants s'assoient à la table 1
     * @param team1
     * @param team2 */
    public void makeParticipantSit(Team team1,Team team2){
        team1.member1.sitTable(BarElements.table1);
        team2.member1.sitTable(BarElements.table1);
        team1.member2.sitTable(BarElements.table1);
        team2.member2.sitTable(BarElements.table1);   
    }
    /** fait se relever les joueurs pour laisser la place au suivant
     * @param team1
     * @param team2 */
    public void makeParticipantGetUp(Team team1,Team team2){
        team1.member1.leaveTable(BarElements.table1);
        team2.member1.leaveTable(BarElements.table1);
        team1.member2.leaveTable(BarElements.table1);
        team2.member2.leaveTable(BarElements.table1);
    }
    
    /** Le barman annonce le match à venir
     * @param team1
     * @param team2 */
    public void annoncematch(Team team1,Team team2){
        BarElements.theBartender.talk("This match will oppose "+team1.member1.name+" and "+team1.member2.name+" against "+team2.member1.name+" and "+team2.member2.name);
    }
    
    /** Déclaration */
    public void annonceTournament(){
        System.out.println("\n"+ ConsoleColors.RED_BOLD + "                           A Tournament started ! \n");
    }
    /** Déclaration */
    public void annonceEndTournament(){
        System.out.println("\n"+ ConsoleColors.RED_BOLD + "                           The Tournament is over ! \n");
    }
    
    /** Les gagnants sont déclarés puis récopensés */
    public void declareWinner(){
        int max=0;
        int idmax=0;
        for(int i=0;i<3;i++){
            if(teamsList.get(i).points>max){
                max=teamsList.get(i).points;
                idmax=i;
            }
        }
        winningTeam=teamsList.get(idmax);
        if(max==1){
            BarElements.theBartender.talk("No winners ! It's an equality between the 3 teams");
            return;
        }
        BarElements.theBartender.talk(winningTeam.member1.name+" and "+winningTeam.member2.name+" won that tournament with "+winningTeam.points+" wins, good job !");
        winningTeam.member1.popularity+=100;
        winningTeam.member2.popularity+=100;
        winningTeam.member1.money+=50;
        winningTeam.member2.money+=50;
        winningTeam.member1.shout();
        winningTeam.member2.shout();
        BarElements.teamsList.clear();
    }
    
}
