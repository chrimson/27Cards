package net.chrimson.twenty7cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView c00 = findViewById(R.id.card00);
//        c00.setText("foot");

        setContentView(R.layout.activity_main);
    }
}