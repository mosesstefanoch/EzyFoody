package com.example.ezyfoody;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ezyfoody.Model.User;
import com.example.ezyfoody.Retrofit.IEzyFoodAPI;
import com.example.ezyfoody.Utils.Common;
//import com.facebook.login.Login;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn_continue;

    IEzyFoodAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = Common.getAPI();

        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();

//                openHome();
//                startLoginType(LoginType.PHONE);
            }
        });

//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openHome();
//            }
//        });

    }

    public void openHome() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }


    private void showRegisterDialog(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("REGISTER");

        LayoutInflater inflater = this.getLayoutInflater();
        View register_layout = inflater.inflate(R.layout.register_layout, null);

        final MaterialEditText edit_name = (MaterialEditText)register_layout.findViewById(R.id.edit_name);
        final MaterialEditText edit_address = (MaterialEditText)register_layout.findViewById(R.id.edit_address);
        final MaterialEditText edit_birthdate = (MaterialEditText)register_layout.findViewById(R.id.edit_birthdate);
        final MaterialEditText edit_phone = (MaterialEditText)register_layout.findViewById(R.id.edit_phone);

        Button btn_register = (Button)register_layout.findViewById(R.id.btn_register);

        edit_birthdate .addTextChangedListener(new PatternedTextWatcher("####-##-##"));

        builder.setView(register_layout);
        final AlertDialog dialog = builder.create();
        //Even
        final AlertDialog finalDialog = dialog;
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                if(TextUtils.isEmpty(edit_address.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edit_birthdate.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter your birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edit_name.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edit_phone.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                final SpotsDialog watingDialog = new SpotsDialog(MainActivity.this);
                watingDialog.show();
                watingDialog.setMessage("Please Waiting ...");

                mService.registerNewUser(edit_phone.getText().toString(),
                        edit_name.getText().toString(),
                        edit_address.getText().toString(),
                        edit_birthdate.getText().toString())
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                watingDialog.dismiss();
                                User user = response.body();
//                                if (TextUtils.isEmpty(user.getError_msg()))
//                                {
//                                    Toast.makeText(MainActivity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
//                                    //Start new activity
//                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                watingDialog.dismiss();
                            }
                        });
                openHome();
            }
        });

        dialog.show();


    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.ezyfoody", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEYHASH", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}