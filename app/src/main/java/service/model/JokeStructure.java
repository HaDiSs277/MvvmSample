package service.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hadis.t on 4/29/2018.
 */

public class JokeStructure implements Serializable{
    @SerializedName("id")
    private int jokeId;
    @SerializedName("categories")
    private ArrayList<String> jokeCategory;
    @SerializedName("joke")
    private String jokeDetails;

    public String getJokeDetails() {
        return jokeDetails;
    }

    public int getJokeId() {
        return jokeId;
    }

}
