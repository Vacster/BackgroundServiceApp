package activity;

import packets.AnswerPacket;
import helpers.PacketHelper;
import service.BackgroundService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esotericsoftware.minlog.Log;
import com.example.backgroundtest2.R;

public class MainActivity extends Activity {

	public static final int waitingTime = 5000, tcp = 55555, udp = 44444;
	public static String ip = "192.168.5.128";
	public static SharedPreferences sharedPrefs;
	public static SharedPreferences.Editor sharedPrefsEditor;
	private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.set(Log.LEVEL_DEBUG);
        mContext = getApplicationContext();
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        setContentView(R.layout.activity_main);
               
        //TODO: Everything below this is disgusting and should be changed completely
    	
		final TextView textField = (TextView) findViewById(R.id.editText1);
		final Button button = (Button) findViewById(R.id.sendButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = textField.getEditableText().toString();
				if(!str.isEmpty()){
					AnswerPacket packet = new AnswerPacket();
					packet.str = str;
					PacketHelper.instanceOf().sendPacket(packet);
					textField.setText("");
				}
			}
		});

		final TextView textField2 = (TextView) findViewById(R.id.editText2);
		textField2.setText(ip);
		final Button connectButton = (Button) findViewById(R.id.connectButton);
		connectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = textField2.getEditableText().toString();
				if(!str.isEmpty()){
					ip = str;
					Intent i = new Intent(mContext, BackgroundService.class);
					startService(i);
					textField2.setVisibility(View.GONE);
					connectButton.setVisibility(View.GONE);
				}
			}
		});
    }
    
    public static Context getContext(){
    	return mContext;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
