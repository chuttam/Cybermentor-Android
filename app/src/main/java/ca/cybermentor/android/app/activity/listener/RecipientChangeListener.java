package ca.cybermentor.android.app.activity.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.activity.ChatActivity;

public class RecipientChangeListener implements ListView.OnItemClickListener {

    private String currentRecipientId;

    public RecipientChangeListener(String currentRecipientId) {
        this.currentRecipientId = currentRecipientId;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String newRecipientId = (String) view.findViewById(R.id.recipient_name).getTag();
        if (!currentRecipientId.equals(newRecipientId))
            switchRecipientTo(view, position, newRecipientId);
    }

    private void switchRecipientTo(View view, int position, String newRecipientId) {
        Context context = view.getContext();
        Activity activity = (Activity) context;
        activity.finish();
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("receiver", newRecipientId);
        context.startActivity(intent);
    }
}
