package ca.cybermentor.android.app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


public class ChatActivity extends ActionBarActivity {

    // Layout views
    private ListView conversationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    protected void onStart() {
        super.onStart();

        final TextView messageView = (TextView) findViewById(R.id.message_entry);
        String message = messageView.getText().toString();
        Log.e("ZOSO", "++++ SLEEPING +++");

        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
//                FrameLayout frame = (FrameLayout) findViewById(R.id.conversation_frame);
//                frame.removeAllViews();
//                frame.addView(messageView);
            }
        }.start();

        //ArrayAdapter conversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_chat);
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
