package ro.pub.cs.systems.eim.practicaltest01var01.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String newText;
            int count = Integer.parseInt(hidden.getText().toString());
            if (view.getId() != R.id.second_activ)
                count ++;
            hidden.setText("" + count);
            switch(view.getId()) {
                case R.id.north:
                    newText = "" + str.getText().toString() + 'N';
                    str.setText(newText);
                    break;
                case R.id.south:
                    newText = "" + str.getText().toString() + 'S';
                    str.setText(newText);
                    break;
                case R.id.east:
                    newText = "" + str.getText().toString() + 'E';
                    str.setText(newText);
                    break;
                case R.id.west:
                    newText = "" + str.getText().toString() + 'W';
                    str.setText(newText);
                    break;
                case R.id.second_activ:
                    Intent intent = new Intent(getApplicationContext(), PracticalText01Var01SecondaryActivity.class);
                    String text = str.getText().toString();
                    intent.putExtra("prevText", text);
                    startActivityForResult(intent, 2);
                    hidden.setText("0");
                    str.setText("");
            }

            if (count == 4) {
                Log.d("service", "trying to start service");
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra("prevText", str.getText().toString());
                getApplicationContext().startService(intent);
            }
        }
    }

    Button north, south, east, west, second;
    TextView hidden, str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);
        intentFilter.addAction("broadcast");
        north = (Button)findViewById(R.id.north);
        south = (Button)findViewById(R.id.south);
        east = (Button)findViewById(R.id.east);
        west = (Button)findViewById(R.id.west);
        second = (Button)findViewById(R.id.second_activ);
        north.setOnClickListener(buttonClickListener);
        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);
        hidden = (TextView)findViewById(R.id.hidden);
        second.setOnClickListener(buttonClickListener);
        if (savedInstanceState != null) {
            hidden.setText(savedInstanceState.getString("count"));
        }
        str = (TextView)findViewById(R.id.str);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            hidden.setText(savedInstanceState.getString("count"));
            Log.d("count", hidden.getText().toString());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("count", hidden.getText().toString());
        Log.d("count", hidden.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
