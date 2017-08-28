package com.hsenid.translator.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hsenid on 8/25/17.
 */

public class LanguagesModel {

    ArrayList<String> dirs;
    HashMap<String, String> langs;

    public ArrayList<String> getDirs() {
        return dirs;
    }

    public void setDirs(ArrayList<String> dirs) {
        this.dirs = dirs;
    }

    public HashMap<String, String> getLangs() {
        return langs;
    }

    public void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }


}
