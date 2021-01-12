package ex.Travelapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    Button btnLogin, btnRegister, btnOK, btnCancel;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText1);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        imageView = findViewById(R.id.btnshowpass);

        // Tạo Đối Tượng getSharedPreferences
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("Name",edtUserName.toString());
        Ed.putString("password",edtPassword.toString());
        Ed.commit();

        Intent intent = getIntent();
        edtUserName.setText(intent.getStringExtra("username"));
        edtPassword.setText(intent.getStringExtra("password"));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {

                    Toast.makeText(LoginActivity.this, "UserName or Pass is missing", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    dialog.setTitle("Thông báo");
                    dialog.setMessage("Do you want Register?");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                            startActivityForResult(intent, 100);
                        }
                    });

                    dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //System.exit(0);
                            dialog.cancel();
                        }
                    });
                    AlertDialog al = dialog.create();
                    al.show();
                } else if (edtPassword.getText().toString().length() < 6) {
                    edtPassword.setError("Minimum 6 number");
                } else {
                    Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    imageView.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                    //Show Password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imageView.setImageResource(R.drawable.ic_baseline_visibility_24);

                    //Hide Password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 100);
                // finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            edtUserName.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
        if (requestCode == 102 && resultCode == 101) {
            edtUserName.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
    }


    //==================================================================================
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onPause() {
        super.onPause();
        luuDuLieu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPreferences();
        loadDuLieu();
    }

    private void initPreferences()
    {
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
    }

    public void luuDuLieu() {

        //Tạo Đối Tượng getSharedPreferences
        String userdata = edtUserName.getText().toString();
        String passworddata = edtPassword.getText().toString();
        editor.putString("userdata",userdata);
        editor.putString("passworddata",passworddata);
        editor.commit();
    }

    public void loadDuLieu() {
        String userdata = preferences.getString("datauser","");
        String passworddata = preferences.getString("datapassword","");
        edtUserName.setText(userdata);
        edtPassword.setText(passworddata);
    }



//========================================================================

}
