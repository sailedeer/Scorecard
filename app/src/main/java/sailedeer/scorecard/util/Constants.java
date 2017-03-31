package sailedeer.scorecard.util;

/**
 * Created by Eli on 3/27/2017.
 */

public final class Constants {
    private Constants(){}   //mak private to prevent any instance

    // add actions
    public static final String ADD_PLAYER = "sailedeer.scorecard.util.ADD_PLAYER";
    public static final String ADD_GAME = "sailedeer.scorecard.util.ADD_GAME";
    public static final String ADD_COURSE = "sailedeer.scorecard.util.ADD_COURSE";

    // remove actions
    public static final String DEL_PLAYER = "sailedeer.scorecard.util.DEL_PLAYER";
    public static final String DEL_GAME = "sailedeer.scorecard.util.DEL_GAME";
    public static final String DEL_COURSE = "sailedeer.scorecard.util.DEL_COURSE";

    // edit actions
    public static final String EDIT_PLAYER = "sailedeer.scorecard.util.EDIT_PLAYER";
    public static final String EDIT_COURSE = "sailedeer.scorecard.util.EDIT_COURSE";

    // common intent put keys
    public static final String K_PLAYER = "player";
    public static final String K_COURSE = "course";
    public static final String K_GAME = "game";

    // other consts
    public static final String BOOL_EDIT = "edit";
}
