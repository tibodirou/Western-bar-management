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

/** Une equipe de 2 joueur membre 1 et membre 2 */
public class Team {
    public people.Client member1;
    public people.Client member2;
    public int points;
    
    public Team(people.Client member1,people.Client member2){
        this.member1=member1;
        this.member2=member2;
        this.points=0;
    }
}
