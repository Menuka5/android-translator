package com.hsenid.translator.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hsenid.translator.models.LanguagesModel;
import com.hsenid.translator.models.StoreHashMap;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by hsenid on 8/25/17.
 */

public class SpinnerLanguageSetter extends AsyncTask<Void, Void, HashMap<String, String>> {
    private Spinner fromLanguageSpinner;
    private Spinner toLanguageSpinner;
    private Context context;

    public StoreHashMap storeHashMap;
    RestTemplate restTemplate = new RestTemplate();

    HashMap<String, String> langs;

    public SpinnerLanguageSetter(Spinner fromLanguageSpinner, Spinner toLanguageSpinner, Context context) {
        this.fromLanguageSpinner = fromLanguageSpinner;
        this.toLanguageSpinner = toLanguageSpinner;
        this.context = context;
    }


    public HashMap<String, String> restCallForLanguageList() {

        final String urlToCall = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b";

        try {

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LanguagesModel message = restTemplate.getForObject(urlToCall, LanguagesModel.class);
            langs = message.getLangs();

        } catch (Exception e) {
            Log.d("D", e.getMessage());
        }
        return langs;

    }

    public String returnKeyforValue(String value) {

//        HashMap<String, String> langSet = storeHashMap.getLangSet();

        for (String matching : langs.keySet()) {
            if (langs.get(matching).equals(value)) {
                return matching;
            }
        }
        return "";
    }

    @Override
    protected HashMap<String, String> doInBackground(Void... voids) {
        HashMap<String, String> languageList = restCallForLanguageList();

        return languageList;

    }

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
}
