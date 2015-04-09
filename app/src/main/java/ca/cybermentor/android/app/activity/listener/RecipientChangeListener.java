package ca.cybermentor.android.app.activity.listener;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.cybermentor.android.app.R;

public class RecipientChangeListener implements ListView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: Bypass if existing position is the same as currently open recipient.
        switchRecipientTo(view, position);
    }

    private void switchRecipientTo(View view, int position) {
        String recipientId = (String) view.findViewById(R.id.recipient_name).getTag();
    }
}
