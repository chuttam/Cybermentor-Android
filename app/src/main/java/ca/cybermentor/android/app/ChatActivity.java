package ca.cybermentor.android.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends ActionBarActivity {

    // Layout views
    private ArrayList<String> conversationArrayList;
    private ListView chat;
    private TextView messageBox;
    private String message;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected void onStart() {
        super.onStart();

        conversationArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.message, conversationArrayList);

        messageBox = (TextView) findViewById(R.id.message_entry);
        sendButton = (Button) findViewById(R.id.send_button);
        chat = (ListView) findViewById(R.id.scroll_chat);
        chat.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              message = messageBox.getText().toString();
                                              conversationArrayList.add("New: " + message);
                                              adapter.notifyDataSetChanged();
                                          }
                                      }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
