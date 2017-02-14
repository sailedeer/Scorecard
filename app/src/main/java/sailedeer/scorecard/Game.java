package sailedeer.scorecard;

import java.util.ArrayList;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game {

    enum GameType
    {
        Individual,
        Vegas,
        Useless,
    }

    Player[] players;
    ArrayList<Game> subGames;
    Course course;
    GameType gameType;
    int[][] scores;

    public Game(Course _course, GameType _gameType)
    {
        course = _course;
        gameType = _gameType;

        //populate players arraylist for this game

        //generate scores multidimensional array
        //to populate gridview
    }
}
