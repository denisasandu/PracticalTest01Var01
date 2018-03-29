package ro.pub.cs.systems.eim.practicaltest01var01.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalText01Var01SecondaryActivity extends AppCompatActivity {
    Button register, cancel;
    TextView text;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.cancel:
                    setResult(1);
                    break;
                case R.id.register:
                    setResult(2);
                    break;
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_text01_var01_secondary);
        text = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        if (intent != null) {
            String prevText = intent.getStringExtra("prevText");
            text.setText(prevText);
        }


        register = (Button)findViewById(R.id.register);
        cancel = (Button)findViewById(R.id.cancel);
        register.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);
    }
}
