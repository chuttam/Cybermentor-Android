package ca.cybermentor.android.app.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.api.CybermentorApi;

public class ChatActivity extends ActionBarActivity {

    // Layout views
    private ArrayList<String> conversationArrayList;
    private ListView chat;
    private TextView messageBox;
    private String message;
    private Button sendButton;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private String conversationHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        conversationArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.message, conversationArrayList);

        messageBox = (TextView) findViewById(R.id.message_entry);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            conversationHistory = convertStreamToString(new CybermentorApi().cybermentor.messageHistory("*** Redacted with BFG ***", "1").getBody().in());
        } catch (Exception error) {
            Log.e("ZOSO", error.toString());
        }
        conversationArrayList.add("Original: " + conversationHistory);

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

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void setupDrawer() {
        String[] drawerItems = { "Item 1", "Item 2" };
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ListView drawerList = (ListView) findViewById(R.id.drawer_items);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, drawerItems));

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
