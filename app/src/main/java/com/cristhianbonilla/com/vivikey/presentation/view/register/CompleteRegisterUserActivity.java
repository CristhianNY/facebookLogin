package com.cristhianbonilla.com.vivikey.presentation.view.register;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cristhianbonilla.com.vivikey.R;
import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.domain.UserPreference;
import com.cristhianbonilla.com.vivikey.core.presentation.view.BaseActivity;
import com.cristhianbonilla.com.vivikey.presentation.presenter.register.ICompleteRegisterUserPresenter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompleteRegisterUserActivity extends BaseActivity implements CompleteRegisterUserView , Validator.ValidationListener{


    @Inject
    ICompleteRegisterUserPresenter presenter;

    @NotEmpty
    @Length(min = 3, max = 10)
    @BindView(R.id.name_or_nick_name)
    EditText nameOrNickName;

    @NotEmpty
    @BindView(R.id.state_message)
    EditText stateMessage;

    @NotEmpty
    @Email
    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Validator validator;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register_user);

        ButterKnife.bind(this);
        VivikeyApp.getControllerComponent(this).inject(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    private User getUserInformation() {

      if(validateUserInformation()){
        user = new User(UserPreference.Id,nameOrNickName.getText().toString(),email.getText().toString(), UserPreference.Phone);
      }

        return user;
    }

    @Override
    protected void onStart() {
        presenter.onStart(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }


    private boolean validateUserInformation(){

        if(nameOrNickName.getText()!= null && nameOrNickName.getText().toString().length() >0){
            return true;
        }else{
            return true;
        }

    }

    @Override
    public void saveUser(User user) {

        presenter.saveUser(this,user);

    }

    @Override
    public void onValidationSucceeded() {
        saveUser(getUserInformation());
        Toast.makeText(this, "We got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
