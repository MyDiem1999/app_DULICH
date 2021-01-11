package ex.Travelapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtUsername, edtPassword, edtConfirm;
    Button btnSignIn, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.editText);
        edtUsername = findViewById(R.id.editText2);
        edtPassword = findViewById(R.id.editText3);
        edtConfirm = findViewById(R.id.editText4);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start= new Intent(Intent.ACTION_MAIN);
                start.addCategory(Intent.CATEGORY_HOME);
                startActivity(start);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid(edtEmail.getText().toString())) {
                    edtEmail.setError("Invalid Email Address");
                    return;
                }
                if (edtUsername.getText().toString().isEmpty()) {
                    edtUsername.setError("Usernam can't be null");
                    return;
                }
                if (edtPassword.getText().toString().isEmpty()) {
                    edtUsername.setError("Password required");
                    return;
                }
                if (edtConfirm.getText().toString().isEmpty()) {
                    edtUsername.setError("Password required");
                    return;
                }

                if (edtPassword.getText().toString().equals(edtConfirm.getText().toString())) {
                    Intent intent = new Intent(RegisterActivity.this, InfoActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    intent.putExtra("password", edtPassword.getText().toString());
                    intent.putExtra("Email", edtEmail.getText().toString());
                    setResult(101, intent);
                    startActivity(intent);
                    // finish();
                } else {
                    edtPassword.setError("Password and Confirm password doesn't match");
                    edtConfirm.setText("");
                    return;
                }
            }
        });

    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
