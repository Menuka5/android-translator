package com.hsenid.translator.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.hsenid.translator.R;
import com.hsenid.translator.models.TranslatedTextModel;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by hsenid on 8/28/17.
 */

public class TranslateCaller extends AsyncTask<Void, Void, ArrayList<String>> {

    public TranslateCaller(String text, String fromLanguage, String toLanguage, EditText translatedText) {
        this.text = text;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translatedText = translatedText;
    }

    private String text;
    private String fromLanguage;
    private String toLanguage;
    private EditText translatedText;

    RestTemplate restTemplate = new RestTemplate();

    ArrayList<String> reply;


    public ArrayList<String> translateText(String text, String fromLanguage, String toLanguage){
        String traslateUrl = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160314T043532Z.7b2cd69323fcafb3.0e2a38f131f947f39dce80a89756c4d03ed5da6a&text=%s&lang=%s-%s", text,fromLanguage, toLanguage);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Log.d("D", traslateUrl);
        TranslatedTextModel replyJson = restTemplate.getForObject(traslateUrl, TranslatedTextModel.class);
        return replyJson.getText();
    }


    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        reply = translateText(text, fromLanguage, toLanguage);
        return reply;
    }

    @Override
    protected void onPostExecute(ArrayList<String> reply) {
        translatedText.setText(reply.get(0));
    }
}
