/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;
import java.util.ArrayList;
import people.*;
/**
 *
 * @author thibo
 */

/**
 * Tables in the bar, the are 3 tables instanced in BarElements.
 * Each table has 4 emplacement for 1 Clients.
 */
public class Table {
    
    public ArrayList <Client> peopleSitting = new ArrayList <> (); 
    public int numberOfpeopleSitting; 
    
    /* Constructeur */
    public Table(){
        this.numberOfpeopleSitting=0;
        
    }

    public boolean checkTableFull(){
        return this.peopleSitting.size()==4;
    }
    public void addClient(Client client){
        if(checkTableFull()==true){
            System.out.println("Someone is aldready sitting here");
            return;
        }
        peopleSitting.add(client);
        numberOfpeopleSitting+=1;
    } 
    public void removeClient(Client client){
        peopleSitting.remove(client);
        numberOfpeopleSitting-=1;
    } 
    
}
