package edu.howest.breakout.game.info;

/**
 * Created by thomas on 04/11/2014.
 */
public class GameProperties {
    private String naam;
    private int SnelheidBal;
    private int SnelheidPallet;
    private int LengtePallet;
    private int Levens;
    private int ScoreMultiplier;
    private int AantalRijen;

    public final static GameProperties BASIC_PROPERTIES = new GameProperties("test", 5,5,5,5,1,3);

    public GameProperties(String naam, int snelheidBal, int snelheidPallet, int lengtePallet, int levens, int scoreMultiplier, int aantalRijen) {
        this.naam = naam;
        this.SnelheidBal = snelheidBal;
        this.SnelheidPallet = snelheidPallet;
        this.LengtePallet = lengtePallet;
        this.Levens = levens;
        this.ScoreMultiplier = scoreMultiplier;
        this.AantalRijen = aantalRijen;
    }

    public String getNaam() {
        return naam;
    }

    public int getSnelheidBal() {
        return SnelheidBal;
    }

    public int getSnelheidPallet() {
        return SnelheidPallet;
    }

    public int getLengtePallet() {
        return LengtePallet;
    }

    public int getLevens() {
        return Levens;
    }

    public int getScoreMultiplier() {
        return ScoreMultiplier;
    }

    public int getAantalRijen() {
        return AantalRijen;
    }
}
