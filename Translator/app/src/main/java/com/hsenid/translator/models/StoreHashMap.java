package com.hsenid.translator.models;

import java.util.HashMap;

/**
 * Created by hsenid on 8/28/17.
 */

public class StoreHashMap {

    public StoreHashMap(HashMap<String, String> langSet) {
        this.langSet = langSet;
    }

    HashMap<String, String> langSet;

    public HashMap<String, String> getLangSet() {
        return langSet;
    }
}
