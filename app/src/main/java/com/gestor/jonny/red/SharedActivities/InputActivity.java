package com.gestor.jonny.red.SharedActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InputActivity extends AppCompatActivity {
    @BindView(R.id.input) EditText inputField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);
        ButterKnife.bind(this);
        getInputIntent();
        customizeEditText();
    }

    @Override
    public void onBackPressed() {
        Intent i = getIntent();
        i.putExtra("fieldValue", inputField.getText().toString());
        setResult(RESULT_OK, i);
        super.onBackPressed();
    }

    private void getInputIntent() {
        inputField.setText(getIntent().getStringExtra("fieldValue"));
        inputField.setInputType(getIntent().getIntExtra("keyboardType", InputType.TYPE_CLASS_TEXT));
    }

    private void customizeEditText() {
        inputField.requestFocus();
        showKeyboard();
        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                    onBackPressed();
                }

                return false;
            }
        });
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
