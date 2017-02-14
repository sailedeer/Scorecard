package sailedeer.scorecard;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Player {
    String name;
    int handicapIndex;
    int courseHandicap;

    public Player(String _name, int _handicapIndex)
    {
        handicapIndex = _handicapIndex;
        name = _name;
    }
}
