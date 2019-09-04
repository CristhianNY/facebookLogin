package com.cristhianbonilla.com.vivikey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements   AccountKitCallback<Account> {

    private static final String TAG = "Cris";
    @BindView(R.id.txtName)
    TextView textViewName;

    @BindView(R.id.txtEmail)
    TextView txtemail;

    @BindView(R.id.txtId)
    TextView id;

    @BindView(R.id.phone)
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        getCurrentAccount();

        Button bTnlogOut = findViewById(R.id.btn_log_out);
        bTnlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountKit.logOut();
            }
        });

    }

    @Override
    public void onSuccess(Account account) {
        String accountKitId = account.getId();
        Log.d("mal",accountKitId);
        // Get phone number
        PhoneNumber phoneNumber = account.getPhoneNumber();
        if (phoneNumber != null) {
            String phoneNumberString = phoneNumber.toString();
            Log.d("mal",phoneNumberString);
        }

        // Get email
        String email = account.getEmail();
        Log.d("mal",email);
    }

    @Override
    public void onError(AccountKitError accountKitError) {
        Log.d("mal",accountKitError.toString());
    }

    private void getCurrentAccount(){
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            //Handle Returning User
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    // Get Account Kit ID
                    String accountKitId = account.getId();
                    Log.e("Account Kit Id", accountKitId);
                    id.setText(accountKitId);

                    if(account.getPhoneNumber()!=null) {
                        Log.e("CountryCode", "" + account.getPhoneNumber().getCountryCode());
                        Log.e("PhoneNumber", "" + account.getPhoneNumber().getPhoneNumber());

                        phone.setText(account.getPhoneNumber().getPhoneNumber());
                        // Get phone number
                        PhoneNumber phoneNumber = account.getPhoneNumber();
                        String phoneNumberString = phoneNumber.toString();
                        Log.e("NumberString", phoneNumberString);
                    }

                    if(account.getEmail()!=null)
                        Log.e("Email",account.getEmail());
                        txtemail.setText(account.getEmail());
                }

                @Override
                public void onError(final AccountKitError error) {
                    // Handle Error
                    Log.e(TAG,error.toString());
                }
            });
        } else {
            //Handle new or logged out user
            Log.e(TAG,"Logged Out");
        }
    }
}
