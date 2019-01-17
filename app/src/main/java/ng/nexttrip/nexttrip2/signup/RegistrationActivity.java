package ng.nexttrip.nexttrip2.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import ng.nexttrip.nexttrip2.PhoneActivity;
import ng.nexttrip.nexttrip2.R;
import ng.nexttrip.nexttrip2.signin.AuthenticationActivity;
import ng.nexttrip.nexttrip2.util.GlobalVariable;
import ng.nexttrip.nexttrip2.util.JSONParser;

/**
 * Created by Olabode Qudus on 11/10/2018.
 */

public class RegistrationActivity extends AppCompatActivity implements RegInterface.View {
    EditText reg_firstname, reg_lastname, reg_email;
    String  firstname_Holder, lastname_Holder, email_Holder;
    String phone,name,email;
    JSONParser jparser=new JSONParser();
    SharedPreferences sh;
    Context con;
    Boolean CheckEditText;
    public static final String regiURL = GlobalVariable.API+"/api/v1/user/auth";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reg_firstname = findViewById(R.id.reg_first_name);
        reg_lastname = findViewById(R.id.reg_lastname);
        reg_email = findViewById(R.id.reg_email);
       //TODO get the phone number passed from the PhoneActivity To Avoid Input Replication
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone_number");
    }

    //TODO After registration take user to the login page
    public void proceedButton(View view){
        CheckEditTextIsEmptyOrNot();
        if (CheckEditText) {

            String fullName = firstname_Holder + " " + lastname_Holder;

            ng.com.maktay.nexttrip.signup.AuthPresenter register = new ng.com.maktay.nexttrip.signup.AuthPresenter(this);

            register.register(fullName, email_Holder, phone, "CASH");
            //TODO: I don't see where you collect phone number from user.
//            AuthPresenter reg = new AuthPresenter();
//            reg.execute();
          //  new AuthPresenter.execute();
        } else {
            Toast.makeText(RegistrationActivity.this, "All Fields Are Required", Toast.LENGTH_LONG).show();
        }

        //TODO: try to clean up your code. Remove unnecessary comments please

    }
    public void CheckEditTextIsEmptyOrNot() {
        // Getting values from EditText.
        firstname_Holder = reg_firstname.getText().toString().trim();
        lastname_Holder =reg_lastname.getText().toString().trim();
        email_Holder = reg_email.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if (TextUtils.isEmpty(firstname_Holder) || TextUtils.isEmpty(lastname_Holder) || TextUtils.isEmpty(email_Holder)) {
            // If EditText is empty then set variable value as False.
            CheckEditText = false;
        } else {
            // If EditText is filled then set variable value as True.
            CheckEditText = true;
        }
    }

    @Override
    public void showError(@NotNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openRegister() {
    }

    @Override
    public void openOTP() {
    }

    @Override
    public void showProgress(boolean show) {
        //TODO: Show Progress
    }

    @NotNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void openLogin() {
        startActivity(new Intent(this, PhoneActivity.class));
    }
}
