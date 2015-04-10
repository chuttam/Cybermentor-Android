package ca.cybermentor.android.app.activity.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.activity.ChatActivity;

public class RecipientChangeListener implements ListView.OnItemClickListener {

    private String currentRecipientId;
    private Context context;

    public RecipientChangeListener(String currentRecipientId) {
        this.currentRecipientId = currentRecipientId;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String newRecipientId = (String) view.findViewById(R.id.recipient_name).getTag();
        Context context = view.getContext();
        ChatActivity activity = (ChatActivity) context;
        if (!currentRecipientId.equals(newRecipientId))
            switchRecipientTo(context, activity, newRecipientId);
        else
            activity.drawerLayout.closeDrawer(Gravity.START);
    }

    private void switchRecipientTo(Context context, ChatActivity activity, String newRecipientId) {
        activity.finish();
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("receiver", newRecipientId);
        context.startActivity(intent);
    }
}
