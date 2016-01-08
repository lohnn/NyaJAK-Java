package se.lohnn.newjak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.lohnn.calculatelib.JAKCalculator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JAKCalculator calculator = new JAKCalculator();
    }
}
