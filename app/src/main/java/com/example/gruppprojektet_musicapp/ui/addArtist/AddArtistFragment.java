package com.example.gruppprojektet_musicapp.ui.addArtist;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gruppprojektet_musicapp.Database.Artist;
import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.addConcert.AddConcertFragment;
import com.example.gruppprojektet_musicapp.ui.addNews.AddNewsFragment;
import com.example.gruppprojektet_musicapp.ui.recomended.RecomendedFragment;

public class AddArtistFragment extends Fragment {

    private AddArtistViewModel addArtistViewModel;

    Database DatabaseHelper;
    private Button btnAdd, btnShowList;
    private Button btnAddConcert, btnAddNews;
    private EditText artistNameText, artistGenreText, artistDescriptionText, artistRatingText;
    private Artist artist;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addArtistViewModel =
                ViewModelProviders.of(this).get(AddArtistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addartist, container, false);

        // Text inputs
        artistNameText = (EditText)root.findViewById(R.id.textInputArtistNameText);
        artistGenreText = (EditText)root.findViewById(R.id.artistGenreText);
        artistDescriptionText = (EditText)root.findViewById(R.id.artistDescriptionText);
        artistRatingText = (EditText)root.findViewById(R.id.artistRatingText);

        // Buttons
        btnAdd = (Button)root.findViewById(R.id.btnAdd);
        btnShowList = (Button)root.findViewById(R.id.btnShowList);
        btnAddConcert = (Button)root.findViewById(R.id.btnAddConcert);
        btnAddNews = (Button)root.findViewById(R.id.btnAddNews);

        DatabaseHelper = new Database(getActivity());
        artist = new Artist();

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newEntry = artistNameText.getText().toString();

                if (artistNameText.length() != 0)
                {
                    AddData();
                }
                else
                {
                    toastMessage("Field cannot be empty!");
                }
            }
        });

        // Show list button, GO TO RECOMMENDED
        btnShowList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecomendedFragment recomendedFragment = new RecomendedFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, recomendedFragment).commit();
            }
        });

        // Go to ADD CONCERT
        btnAddConcert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddConcertFragment addConcertFragment = new AddConcertFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, addConcertFragment).commit();
            }
        });

        // Go to ADD NEWS
        btnAddNews.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddNewsFragment newsFragment = new AddNewsFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, newsFragment).commit();
            }
        });

        return root;
    }

    public void AddData()
    {
            artist.setName(artistNameText.getText().toString().trim());
            artist.setGenre(artistGenreText.getText().toString().trim());
            artist.setDescription(artistDescriptionText.getText().toString().trim());
            artist.setRating(artistRatingText.getText().toString().trim());
            DatabaseHelper.addArtist(artist);

                toastMessage("Data succesfully saved!");
    }

    public void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
