package com.cristhianbonilla.com.vivikey.presentation.view.register;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cristhianbonilla.com.vivikey.R;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.view.BaseActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteRegisterUserActivity extends BaseActivity implements CompleteRegisterUserView , Validator.ValidationListener{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register_user);

        ButterKnife.bind(this);

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
          User user = new User("123",nameOrNickName.getText().toString(),email.getText().toString());
      }

        return null;
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

    }

    @Override
    public void onValidationSucceeded() {
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
