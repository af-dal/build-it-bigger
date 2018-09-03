package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nanodegree.udacity.androidjokelibrary.JokeResolverActivity;
import com.nanodegree.udacity.jokelibrary.JokeProvider;
import com.udacity.gradle.builditbigger.logic.JokeLoaderAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view) {
        final Intent intent = new Intent(this, JokeResolverActivity.class);
        intent.putExtra(JokeResolverActivity.BUNDLE_JOKE_TEXT, JokeProvider.get().getRandomJoke());
        startActivity(intent);
    }

    public void tellJokeFromApi(View view) {
        new JokeLoaderAsyncTask(new JokeLoaderAsyncTask.JokeLoadCallback() {
            @Override
            public void onJokeLoaded(@Nullable String joke) {
                if (joke == null) {
                    showError();
                    return;
                }
                final Intent intent = new Intent(MainActivity.this, JokeResolverActivity.class);
                intent.putExtra(JokeResolverActivity.BUNDLE_JOKE_TEXT, joke);
                startActivity(intent);
            }
        }).execute();
    }

    private void showError() {
        Toast.makeText(this, "Error loading Joke", Toast.LENGTH_LONG).show();
    }

}
