package com.hsenid.translator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hsenid.translator.asynctasks.GoogleSpinnerSetter;
import com.hsenid.translator.asynctasks.TranslateCaller;

public class GoogleTranslate extends AppCompatActivity {

    private Spinner fromLanguageSpinner;
    private Spinner toLangSpinner;
    private Button translateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_translate);

        fromLanguageSpinner = (Spinner) findViewById(R.id.fromLangSpinner);
        toLangSpinner = (Spinner) findViewById(R.id.toLangSpinner);

        final GoogleSpinnerSetter googleSpinnerSetter = (GoogleSpinnerSetter) new GoogleSpinnerSetter(fromLanguageSpinner, toLangSpinner, GoogleTranslate.this).execute();

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

                if(text != null && !text.isEmpty()){
                    new TranslateCaller(text, googleSpinnerSetter.returnKeyforValue(fromLanguage), googleSpinnerSetter.returnKeyforValue(toLanguage), translatedTextEdit).execute();
                }




            }
        });


    }
}
