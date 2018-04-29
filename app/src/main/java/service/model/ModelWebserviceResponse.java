package service.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hadis.t on 4/29/2018.
 */

public class ModelWebserviceResponse implements Serializable {
    @SerializedName("success")
    private String success;
    @SerializedName("value")
    private ArrayList<JokeStructure> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<JokeStructure> getData() {
        return data;
    }

    public void setData(ArrayList<JokeStructure> data) {
        this.data = data;
    }
}
