package com.cristhianbonilla.com.vivikey.core.presentation.view;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cristhianbonilla.com.vivikey.R;

import butterknife.BindView;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override public void showLoader() {
        runOnUiThread(() -> {
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override public void hideLoader() {
        runOnUiThread(() -> {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override public void noContent() {
        showToast(getString(R.string.no_content));
    }

    @Override public void noInternet() {
        showToast(getString(R.string.no_internet));
    }

    @Override public void showToast(String msg) {
        runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
    }
}