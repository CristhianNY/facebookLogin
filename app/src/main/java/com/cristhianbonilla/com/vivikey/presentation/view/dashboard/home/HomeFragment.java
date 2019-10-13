package com.cristhianbonilla.com.vivikey.presentation.view.dashboard.home;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.cristhianbonilla.com.vivikey.R;
import com.cristhianbonilla.com.vivikey.core.VivikeyApp;
import com.cristhianbonilla.com.vivikey.core.domain.Contact;
import com.cristhianbonilla.com.vivikey.core.domain.UserPreference;
import com.cristhianbonilla.com.vivikey.presentation.presenter.Home.IHomePresenter;
import com.cristhianbonilla.com.vivikey.presentation.view.dashboard.HomeActivity;


import java.util.ArrayList;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeFragmentView {
    private HomeViewModel homeViewModel;
    private static final int REQUEST_RUNTIME_PERMISSION = 123;

    String[] permissons = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG};

    @Inject
    IHomePresenter presenter;

    private ArrayList<Contact> contacts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VivikeyApp.getControllerComponent(getContext()).inject(this);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if (CheckPermission(permissons[0])) {
            // you have permission go ahead

            getContactsFriends(getActivity().getContentResolver());

        } else {
            // you do not have permission go request runtime permissions
            RequestPermission(permissons, REQUEST_RUNTIME_PERMISSION);
        }

        return root;
    }

    @Override
        public void getContactsFriends(ContentResolver cr)
        {
            Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
            // use the cursor to access the contacts
            while (phones.moveToNext())
            {
                String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                // get display name
               String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // get phone number
                System.out.println(".................."+phoneNumber);
                Contact contact = new Contact(name,phoneNumber);
                contacts.add(contact);
            }
            presenter.insertContacts(contacts,UserPreference.getUser(getContext()),getContext());

        }


    public boolean CheckPermission( String Permission) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void RequestPermission(String[] Permission, int Code) {
        if (ContextCompat.checkSelfPermission(getContext(),
                Permission[0])
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Permission[0])) {
                getContactsFriends(getActivity().getContentResolver());
            } else {
                ActivityCompat.requestPermissions(getActivity(), Permission,
                        Code);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {

            case REQUEST_RUNTIME_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // you have permission go ahead

                } else {
                    // you do not have permission show toast.
                }
                return;
            }
        }
    }

}