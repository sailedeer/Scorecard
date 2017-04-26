package sailedeer.scorecard.activities.handling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import sailedeer.scorecard.R;

/**
 * Created by Eli on 4/25/2017.
 */

public class GameEditViewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] textViewValues;
    private ArrayList<EditText> textFields;

    public GameEditViewAdapter(Context context, String[] values)
    {
        mContext = context;
        textViewValues = values;
        textFields = new ArrayList<>();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = convertView;

        if (convertView == null)
        {
            gridView = inflater.inflate(R.layout.game_grid_item, null);

            EditText et = (EditText) gridView.findViewById(R.id.grid_item_label);
            et.setText(textViewValues[position]);
            textFields.add(et);
        }

        return gridView;
    }

    @Override
    public int getCount()
    {
        return textViewValues.length;
    }

    @Override
    public String getItem(int position)
    {
        return textViewValues[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public void addText(int position, String text)
    {
        EditText edit = textFields.get(position);
        edit.setText(text);
        textFields.set(position, edit);
    }
}
