package Chess;

import java.io.Serializable;

public class GameData implements Serializable {
    String player1;
    String player2;
    Boolean winner; //true ha a player1 győzött, false ha a player2

    int time = 0;
    int lepesszam = 0;

    public Boolean getWinner(){return winner;}
    public String getPlayer1(){return player1;}
    public String getPlayer2(){return player2;}
    public int getTime(){return time;}
    public int getLepesszam() {return lepesszam;}

    public GameData(String player1, String player2, int time, Boolean winner, int lepesszam){
        this.player1 = player1;
        this.player2 = player2;
        this.time = time;
        this.winner = winner;
        this.lepesszam = lepesszam;
    }
}
