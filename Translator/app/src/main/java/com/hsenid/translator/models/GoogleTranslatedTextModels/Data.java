package com.hsenid.translator.models.GoogleTranslatedTextModels;

/**
 * Created by hsenid on 8/29/17.
 */

public class Data {
    private Translations[] translations;

    public Translations[] getTranslations ()
    {
        return translations;
    }

    public void setTranslations (Translations[] translations)
    {
        this.translations = translations;
    }

}
