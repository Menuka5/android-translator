package com.hsenid.translator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hsenid.translator.asynctasks.SpinnerLanguageSetter;
import com.hsenid.translator.asynctasks.TranslateCaller;

public class YandexTranslate extends AppCompatActivity {

    private Spinner fromLanguageSpinner;
    private Spinner toLangSpinner;
    private Button translateButton;

   /* public void spinnerSetter(Spinner fromLanguageSpinner, Spinner toLangSpinner){



//        String test = spinnerLanguageSetter.returnKeyforValue("Afrikaans");
//        Log.d("D", "Key for value =>" + spinnerLanguageSetter.returnKeyforValue("English"));

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yandex_translate);

        fromLanguageSpinner = (Spinner) findViewById(R.id.fromLangSpinner);
        toLangSpinner = (Spinner) findViewById(R.id.toLangSpinner);

        final SpinnerLanguageSetter spinnerLanguageSetter = (SpinnerLanguageSetter) new SpinnerLanguageSetter(fromLanguageSpinner, toLangSpinner, YandexTranslate.this).execute();

        translateButton = (Button) findViewById(R.id.translateButton);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLanguage = fromLanguageSpinner.getSelectedItem().toString();
                String toLanguage = toLangSpinner.getSelectedItem().toString();

//                String text = String.valueOf(findViewById(R.id.textTotranslate));
                EditText textToTranslateEdit = (EditText) findViewById(R.id.textTotranslate);
                EditText translatedTextEdit = (EditText) findViewById(R.id.translatedText);

                String text = textToTranslateEdit.getText().toString();

                new TranslateCaller(text, spinnerLanguageSetter.returnKeyforValue(fromLanguage), spinnerLanguageSetter.returnKeyforValue(toLanguage), translatedTextEdit).execute();

//                Log.d("D", fromLanguage + "=" + toLanguage);

            }
        });
    }
}
