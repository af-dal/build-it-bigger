package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.nanodegree.udacity.jokelibrary.JokeProvider;

/**
 * An endpoint class we are exposing
 */
@Api(
    name = "myApi",
    version = "v1",
    namespace = @ApiNamespace(
        ownerDomain = "backend.builditbigger.gradle.udacity.com",
        ownerName = "backend.builditbigger.gradle.udacity.com",
        packagePath = ""
    )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        Joke response = new Joke();
        response.setData(JokeProvider.get().getRandomJoke());
        return response;
    }

}
