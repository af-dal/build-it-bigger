package com.nanodegree.udacity.androidjokelibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class JokeResolverActivity extends AppCompatActivity {

    public static final String BUNDLE_JOKE_TEXT = "bundle.joke.text";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_resolver);

        final String joke;
        if ((joke = getIntent().getStringExtra(BUNDLE_JOKE_TEXT)) != null) {
            Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
            ((TextView) findViewById(R.id.joke_text_view)).setText(joke);

        }
        findViewById(R.id.go_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
