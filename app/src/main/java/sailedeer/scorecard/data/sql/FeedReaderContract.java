package sailedeer.scorecard.data.sql;

import android.provider.BaseColumns;

/**
 * Created by Eli on 3/13/2017.
 */

public final class FeedReaderContract {
    private FeedReaderContract(){}

    public static class FeedEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "scorecard_data";
        public static final String PLAYER_COLUMN_NAME_TITLE = "player";
        public static final String GAME_COLUMN_NAME_TITLE = "game";
        public static final String COURSE_COLUMN_NAME_TITLE = "course";

    }
}
