package com.hsenid.translator.models;

/**
 * Created by hsenid on 8/25/17.
 */

public class Language {
    private String languegeCode;
    private String languegeName;

    public Language(String languegeCode, String languegeName) {
        this.languegeCode = languegeCode;
        this.languegeName = languegeName;
    }

    public String getLanguegeCode() {
        return languegeCode;
    }

    public void setLanguegeCode(String languegeCode) {
        this.languegeCode = languegeCode;
    }

    public String getLanguegeName() {
        return languegeName;
    }

    public void setLanguegeName(String languegeName) {
        this.languegeName = languegeName;
    }

    @Override
    public String toString() {
        return languegeCode+" "+languegeName;
    }
}
