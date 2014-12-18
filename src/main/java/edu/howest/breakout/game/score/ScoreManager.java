package edu.howest.breakout.game.score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas15v on 18/12/14.
 */
public class ScoreManager {

    private List<Player> players;

    public ScoreManager(){
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player... players){
        for (Player player : players)
            this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder();
        for (Player player : players) {
            returnstring.append(player.toString());
            returnstring.append(" ");
        }
        return returnstring.toString();
    }
}
