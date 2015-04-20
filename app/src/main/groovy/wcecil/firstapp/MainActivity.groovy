package wcecil.firstapp

import android.app.Activity
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import groovy.transform.CompileStatic
import wcecil.firstapp.constants.Constants

class MainActivity extends Activity {

    def myActivityReceiver = new BroadcastReceiver() {
        @Override
        void onReceive(Context context, Intent intent) {
            def extras = intent.getExtras();
            println "Got Extras $extras"

            def sbn = extras.get(Constants.SBN)
            println "Got SBN $sbn"

            if(sbn instanceof StatusBarNotification){
                def status = (StatusBarNotification)sbn
                def notif = status.getNotification()
                println "${status.packageName} ${notif}"
                println notif.tickerText

                if(notif.tickerText) {
                    def textView = (TextView) findViewById(R.id.textView)
                    def text = "${textView.getText()}\n${notif.tickerText}"
                    textView.setText(text)
                }
            }
            println "$context"
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (myActivityReceiver != null) {
            def intentFilter = new IntentFilter(Constants.ACTION_STRING_ACTIVITY);
            registerReceiver(myActivityReceiver, intentFilter);
        }

        def textView = (TextView) findViewById(R.id.textView)
        textView.setText('Hello William')
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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