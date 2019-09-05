package com.cristhianbonilla.com.vivikey.presentation.view.register;
import android.os.Bundle;
import com.cristhianbonilla.com.vivikey.R;
import com.cristhianbonilla.com.vivikey.core.domain.User;
import com.cristhianbonilla.com.vivikey.core.presentation.view.BaseActivity;

public class CompleteRegisterUserActivity extends BaseActivity implements CompleteRegisterUserView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register_user);
    }


    @Override
    public void saveUser(User user) {

    }
}
