package com.nanodegree.udacity.jokelibrary;

import com.google.gson.Gson;
import com.nanodegree.udacity.jokelibrary.data.Joke;
import com.nanodegree.udacity.jokelibrary.data.JokeData;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeProvider {
    private static final String FILE_NAME = "jokes.json";
    private static JokeProvider jokeProvider;

    private List<Joke> jokeList = new ArrayList<>();

    public JokeProvider() {
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_NAME), "UTF-8");
            final JokeData jokeData = new Gson().fromJson(inputStreamReader, JokeData.class);
            if (jokeData != null && jokeData.getJokeList() != null) {
                jokeList.addAll(jokeData.getJokeList());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static JokeProvider get() {
        if (jokeProvider == null) {
            jokeProvider = new JokeProvider();
        }

        return jokeProvider;
    }

    public String getRandomJoke() {
        if (!jokeList.isEmpty()) {
            return jokeList.get(new Random().nextInt(jokeList.size())).getValue();
        }
        return null;
    }
}
