package com.example.cecs;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class is a used to control the data.
 */
public class UserDataDictionary implements Serializable {
    // Create new HashMap Instance userDict
    private HashMap<String, User> userDict = new HashMap<>();

    // getUserDict method gets the userDict hashmap.
    public HashMap<String, User> getUserDict() {
        return userDict;
    }

    // setUserDict method sets the updated userDict
    public void setUserDict(HashMap<String, User> newUserDict) {
        this.userDict = newUserDict;
    }
}
