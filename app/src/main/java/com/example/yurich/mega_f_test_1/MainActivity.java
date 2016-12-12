package com.example.yurich.mega_f_test_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;

    int amountOfEditTexts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setText("Tap me!");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tag", "Clicked with amount=" + amountOfEditTexts);
                onButtonClicked();
            }
        });

        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }

    }

    private void restoreState(Bundle savedInstanceState) {
        amountOfEditTexts = savedInstanceState.getInt("amount");

        for (int i = 0; i < amountOfEditTexts; i++) {
            EditText editText = generateNewEditText(i);
            editText.setText(savedInstanceState.getString("editText_" + i));

            layout.addView(editText, 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("amount", amountOfEditTexts);
        for (int i = 0; i < amountOfEditTexts; i++) {
            outState.putString(
                    "editText_" + i,
                    ((EditText) findViewById(i)).getText().toString()
            );
        }
    }

    private void onButtonClicked() {
        layout.addView(generateNewEditText(amountOfEditTexts), 0);

        amountOfEditTexts++;
    }

    private EditText generateNewEditText(int newEditTextId) {
        EditText editText = new EditText(getApplicationContext());
        editText.setId(newEditTextId);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editText.setTextColor(getResources().getColor(R.color.edit_text_color));

        return editText;
    }

}
