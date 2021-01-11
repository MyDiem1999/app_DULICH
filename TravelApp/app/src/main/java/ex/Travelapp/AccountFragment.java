package ex.Travelapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    EditText edtName, edtEmail, edtPass, edtUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gắn lên fragment
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_info, container, false);
        edtName = view.findViewById(R.id.editText4);
        edtEmail = view.findViewById(R.id.editText);
        edtUsername = view.findViewById(R.id.editText2);
        edtPass = view.findViewById(R.id.editText3);

        initPre();
        laydl();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public void initPre()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
    }

    public void laydl() {

        //Tạo Đối Tượng getSharedPreferences
        String email = preferences.getString("dataemail","");
        String userdata = preferences.getString("datauser","");
        String passworddata = preferences.getString("datapassword","");
        edtUsername.setText(userdata);
        edtPass.setText(passworddata);
        edtEmail.setText(email);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuSignOut)
        {

            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("username", edtUsername.getText().toString());
            intent.putExtra("password", edtPass.getText().toString());

            initPre();
            editor.clear();
            editor.apply();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
