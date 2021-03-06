package ca.cybermentor.android.app.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.activity.listener.RecipientChangeListener;
import ca.cybermentor.android.app.adapter.ConversationAdapter;
import ca.cybermentor.android.app.adapter.DrawerListAdapter;
import ca.cybermentor.android.app.api.CybermentorApi;
import ca.cybermentor.android.app.api.model.Conversation;
import ca.cybermentor.android.app.api.service.ConversationService;
import ca.cybermentor.android.app.event.BusProvider;
import ca.cybermentor.android.app.event.LoadConversationEvent;
import ca.cybermentor.android.app.model.Message;
import ca.cybermentor.android.app.model.Participant;
import ca.cybermentor.android.app.model.constant.Secret;
import ca.cybermentor.android.app.model.view.DrawerItem;

public class ChatActivity extends ActionBarActivity {

    Bus eventBus = BusProvider.getInstance();
    // Layout views
    private ArrayList<Message> conversationArrayList;
    private ConversationAdapter conversationAdapter;
    public  DrawerLayout drawerLayout;
    private DrawerListAdapter drawerListAdapter;
    private ListView chat;
    private TextView messageBox;
    private String message;
    private Button sendButton;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ProgressDialog spinner;
    private Participant me;
    private Participant receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpParticipants();
        setupDrawer();
        eventBus.register(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        conversationArrayList = new ArrayList<>();
        conversationAdapter = new ConversationAdapter(this, conversationArrayList);

        messageBox = (TextView) findViewById(R.id.message_entry);

        ConversationService service = new ConversationService(new CybermentorApi(), eventBus);
        service.getMessageHistory(me.id, receiver.id);

        spinner = ProgressDialog.show(this, "Retrieving ...", "Please wait.", true, false);

        TextView topLine = (TextView) findViewById(R.id.top_line);
        topLine.setText(getString(R.string.top_line)  + " " + receiver.name);

        chat = (ListView) findViewById(R.id.scroll_chat);
        chat.setAdapter(conversationAdapter);

        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                  String body = messageBox.getText().toString();
                  if ((body != null) && (!body.isEmpty())) {
                      Message message = new Message(Message.Type.SENT, body);
                      conversationArrayList.add(message);
                      conversationAdapter.notifyDataSetChanged();
                      messageBox.setText(null);
                  }
              }
          }
        );
    }

    private void setUpParticipants() {
        me = new Participant(Secret.ME_ID);                                    // Should come from authentication
        String receiver_id = getIntent().getStringExtra("receiver");
        if (receiver_id == null) receiver_id = Secret.PARTICIPANT_ID1;         // default to Cybermentor Team first
        receiver = new Participant(receiver_id);
    }

    private void setupDrawer() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(new Participant(Secret.PARTICIPANT_ID1)));
        drawerItems.add(new DrawerItem(new Participant(Secret.PARTICIPANT_ID2)));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        TextView whoami = (TextView) findViewById(R.id.whoami);
        whoami.setText(me.name);

        drawerListAdapter = new DrawerListAdapter(this, drawerItems);
        ListView drawerList = (ListView) findViewById(R.id.drawer_items);
        drawerListAdapter = new DrawerListAdapter(this, drawerItems);
        drawerList.setAdapter(drawerListAdapter);

        drawerList.setOnItemClickListener(new RecipientChangeListener(receiver.id));

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

    @Override
    public void onResume() {
        super.onResume();
        // TODO: Works, but shouldn't have to reload conversation just for orientation change.
        // Make current state known.
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawer(Gravity.START);
        else
            super.onBackPressed();
    }

    @Subscribe
    public void onConversationLoaded(LoadConversationEvent event) {
        conversationArrayList.addAll(new Conversation().setupInitialConversation(event.body));
        if (spinner != null) {
            spinner.dismiss();
        }
        conversationAdapter.notifyDataSetChanged();
    }
}
