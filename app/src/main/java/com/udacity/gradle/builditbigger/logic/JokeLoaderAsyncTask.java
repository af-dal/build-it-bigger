package com.udacity.gradle.builditbigger.logic;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeLoaderAsyncTask extends AsyncTask<String, Void, String> {
    private static MyApi myApiService = null;
    private final JokeLoadCallback jokeLoadCallback;

    public JokeLoaderAsyncTask(final JokeLoadCallback jokeLoadCallback) {
        this.jokeLoadCallback = jokeLoadCallback;
    }

    @Override
    public String doInBackground(String... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://localhost:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(@Nullable final String result) {
        if (jokeLoadCallback != null) {
            jokeLoadCallback.onJokeLoaded(result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public interface JokeLoadCallback {
        void onJokeLoaded(final String joke);
    }
}