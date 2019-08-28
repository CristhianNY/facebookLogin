package com.cristhianbonilla.com.vivikey.presentation.view.login;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cristhianbonilla.com.vivikey.R;
import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.presentation.view.BaseActivity;
import com.cristhianbonilla.com.vivikey.presentation.presenter.login.ILoginPresenter;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


    public class LoginActivity extends BaseActivity implements LoginView {

        private static final String TAG = "Login";
        @BindView(R.id.login_button)
        LoginButton loginButton;

        @BindView(R.id.name)
        TextView textViewName;
        @BindView(R.id.last_name)
        TextView textViewLastName;

        @BindView(R.id.email)
        TextView textViewEmail;

        @BindView(R.id.profile_image)
        CircleImageView profileImage;

        @Inject
        ILoginPresenter presenter;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        private CallbackManager callbackManager;
        private Unbinder bind;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_login);

            callbackManager = CallbackManager.Factory.create();
            mAuth = FirebaseAuth.getInstance();

            ButterKnife.bind(this);

            VivikeyApp.getControllerComponent(this).inject(this);

        }

        @OnClick(R.id.login_button)
        void loginClick() {

            presenter.logon("", "", "");
        }

        @Override
        protected void onStart() {
            presenter.onStart(this);
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);

            super.onStart();
        }

        @Override
        public void showLoader() {
            runOnUiThread(() -> {
                if (progressBar != null) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        public void hideLoader() {
            runOnUiThread(() -> {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public void noContent() {
            showToast(getString(R.string.no_content));
        }

        @Override
        public void noInternet() {
            showToast(getString(R.string.no_internet));
        }

        @Override
        public void showToast(String msg) {
            runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
        }

        @Override
        protected void onStop() {
            presenter.onStop();
            super.onStop();
        }

        @Override
        protected void onDestroy() {
            bind.unbind();
            super.onDestroy();
        }


        @Override
        public void prepareLogin() {

            loginButton.setPermissions(Arrays.asList("email", "public_profile"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d(TAG, "facebook:onSuccess:" + loginResult);
                    handleFacebookAccessToken(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Log.d(TAG, "facebook:onCancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d(TAG, "facebook:onError");
                }
            });
        }

        @Override
        public void handleFacebookAccessToken(AccessToken token) {
            Log.d(TAG, "handleFacebookAccessToken:" + token);

            AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }

        @Override
        public void checkLoginStatus() {
            if (AccessToken.getCurrentAccessToken() != null) {
                loadUserInformation(AccessToken.getCurrentAccessToken());
            }
        }

        @Override
        public void updateUI(FirebaseUser user) {
            if (user != null) {
                Toast.makeText(LoginActivity.this, user.getEmail(),
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void loadUserInformation(AccessToken accessToken) {
            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {

                    try {
                        String firstName = object.getString("first_name");
                        String lastName = object.getString("last_name");
                        String email = object.getString("email");
                        String id = object.getString("id");
                        String imageUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";

                        textViewName.setText(firstName);
                        textViewEmail.setText(email);
                        textViewLastName.setText(lastName);

                        Glide
                                .with(LoginActivity.this)
                                .load(imageUrl)
                                .into(profileImage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "first_name,last_name,email,id");

            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void getHash() {
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.cristhian.com.vivikay",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode,
                    resultCode, data);
        }

        AccessTokenTracker tokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    textViewEmail.setText("");
                    textViewName.setText("");
                    textViewLastName.setText("");
                    profileImage.setImageResource(0);
                    Toast.makeText(LoginActivity.this, "Hasta Pronto", Toast.LENGTH_LONG).show();
                } else {
                    loadUserInformation(currentAccessToken);
                }

            }
        };
    }

