package com.nanodegree.udacity.jokelibrary.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeData {

    @SerializedName("jokes")
    private List<Joke> jokeList;

    public List<Joke> getJokeList() {
        return jokeList;
    }
}
