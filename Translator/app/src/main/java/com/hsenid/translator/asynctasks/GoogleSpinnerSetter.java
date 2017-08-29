package com.hsenid.translator.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hsenid.translator.models.googlemodels.GoogleLanguageList;
import com.hsenid.translator.models.googlemodels.Languages;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hsenid on 8/29/17.
 */

public class GoogleSpinnerSetter extends AsyncTask<Void, Void, HashMap<String, String>>{

    private Spinner fromLanguageSpinner;
    private Spinner toLanguageSpinner;
    private Context context;

    RestTemplate restTemplate = new RestTemplate();

    Languages[] languages;
    HashMap<String, String> langs = new HashMap<>();

    public GoogleSpinnerSetter(Spinner fromLanguageSpinner, Spinner toLanguageSpinner, Context context) {
        this.fromLanguageSpinner = fromLanguageSpinner;
        this.toLanguageSpinner = toLanguageSpinner;
        this.context = context;
    }

    public HashMap<String, String> restCallForLanguageList() {

        final String urlToCall = "http://192.168.100.103:8080/alllanguages";

        try {

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            GoogleLanguageList message = restTemplate.getForObject(urlToCall, GoogleLanguageList.class);
            languages = message.getData().getLanguages();
             for (Languages lan : languages){
                 String key = lan.getLanguage();
                 String value = lan.getName();
                 langs.put(key, value);
             }

            Log.d("D", "Worked");
        } catch (Exception e) {
            Log.d("D", "Exception => "+ e.getMessage());
        }
        return langs;
    }

    @Override
    protected HashMap<String, String> doInBackground(Void... params) {
        HashMap<String, String> langList = restCallForLanguageList();
        return langList;
    }


   /* @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }*/

    @Override
    protected void onPostExecute(HashMap<String, String> langList) {
        Object[] language = langList.values().toArray();
        String[] languageArray = Arrays.copyOf(language, language.length, String[].class);
        Arrays.sort(languageArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, languageArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromLanguageSpinner.setAdapter(adapter);
        toLanguageSpinner.setAdapter(adapter);

    }

    public String returnKeyforValue(String value) {

        for (String matching : langs.keySet()) {
            if (langs.get(matching).equals(value)) {
                return matching;
            }
        }
        return "";
    }
}
