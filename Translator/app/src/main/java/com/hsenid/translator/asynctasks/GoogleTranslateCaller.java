package com.hsenid.translator.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.hsenid.translator.models.GoogleTranslatedTextModels.GoogleTranslatedText;
import com.hsenid.translator.models.GoogleTranslatedTextModels.Translations;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by hsenid on 8/29/17.
 */

public class GoogleTranslateCaller extends AsyncTask<Void, Void, String> {

    private String text;
    private String fromLanguage;
    private String toLanguage;
    private EditText translatedText;

    RestTemplate restTemplate = new RestTemplate();
    Translations[] reply;

    public Translations[] translateText(String text, String fromLanguage, String toLanguage){
        String traslateUrl = String.format("http://192.168.100.103:8080/translate/url?text=%s&langTo=%s-%s", text,fromLanguage, toLanguage);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Log.d("D", traslateUrl);
        GoogleTranslatedText replyJson = restTemplate.getForObject(traslateUrl, GoogleTranslatedText.class);
        return replyJson.getData().getTranslations();
    }


    public GoogleTranslateCaller(String text, String fromLanguage, String toLanguage, EditText translatedText) {
        this.text = text;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translatedText = translatedText;
    }

    @Override
    protected String doInBackground(Void... params) {
        Translations[] repliedText = translateText(text, fromLanguage, toLanguage);

        return repliedText[0].getTranslatedText();
    }

    @Override
    protected void onPostExecute(String s) {
        translatedText.setText(s);
    }
}
