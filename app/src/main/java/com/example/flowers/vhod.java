package com.example.flowers;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

    public class vhod extends AppCompatActivity {
        private Button mbutton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            mbutton = findViewById(R.id.button);
            mbutton.setOnClickListener(v -> {
                Intent oknoW = new Intent(this, MainActivity.class);
                startActivity(oknoW);
                finish();
            });
        }
    }

