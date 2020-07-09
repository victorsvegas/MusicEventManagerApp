package com.example.gruppprojektet_musicapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel shareViewModel;
    private ListView listViewUser;
    private ListView listViewUserInfo;
    private Database database;

    ArrayAdapter<String> adapterUser;
    ArrayAdapter<String> adapterUserInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        listViewUser = root.findViewById(R.id.ListUser);
        listViewUserInfo = root.findViewById(R.id.ListUserInfo);
        database = new Database(getActivity());
        init();
        return root;
    }

    private void init() {


        String emailFromIntent = getActivity().getIntent().getStringExtra("EMAIL");
        String[] user = {"Name: ", "Email: ", "Password: ", "Adress: ", "City: ", "Age: ", "Gender: "};
        String[] userinfo = {database.getUser(emailFromIntent).getName(), database.getUser(emailFromIntent).getEmail(), "*******"
        ,database.getUser(emailFromIntent).getAdress(), database.getUser(emailFromIntent).getCity(), database.getUser(emailFromIntent).getDateofbirth()
        , database.getUser(emailFromIntent).getGender()};

        adapterUser = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, user);
        listViewUser.setAdapter(adapterUser);
        adapterUserInfo = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, userinfo);
        listViewUserInfo.setAdapter(adapterUserInfo);
    }
}

