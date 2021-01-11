package ex.Travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    EditText edtName, edtUser,edtEmail, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        edtName = findViewById(R.id.editText4);
        edtEmail = findViewById(R.id.editText);
        edtUser = findViewById(R.id.editText2);
        edtPass = findViewById(R.id.editText3);
        getSupportActionBar().setTitle("Account Info");

        Intent intent = getIntent();
        edtUser.setText(intent.getStringExtra("username"));
        edtPass.setText(intent.getStringExtra("password"));
        edtEmail.setText(intent.getStringExtra("Email"));
        edtName.setText(intent.getStringExtra("Name"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.mnSave)
        {
            Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
//            intent.putExtra("username", edtUser.getText().toString());
//            intent.putExtra("password", edtPass.getText().toString());

            initPre();
            LuuData();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void initPre()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void LuuData()
    {
        String datauserinfo = edtUser.getText().toString();
        String datapasswordinfo = edtPass.getText().toString();
        String dataEmailInfo = edtEmail.getText().toString();

        editor.putString("datauser",datauserinfo);
        editor.putString("datapassword",datapasswordinfo);
        editor.putString("dataemail",dataEmailInfo);
        editor.commit();
    }


}
