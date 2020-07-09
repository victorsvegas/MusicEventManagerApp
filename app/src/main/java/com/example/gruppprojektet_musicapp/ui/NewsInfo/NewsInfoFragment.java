package com.example.gruppprojektet_musicapp.ui.NewsInfo;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.ConcertInfo.ConcertInfoViewModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NewsInfoFragment extends Fragment {

    private NewsInfoViewModel newsInfoViewModel;
    Database database;
    TextView band, info;
    ArrayList newsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsInfoViewModel =
                ViewModelProviders.of(this).get(NewsInfoViewModel.class);
        View root = inflater.inflate(R.layout.news_info_fragment, container, false);

        band = root.findViewById(R.id.textViewBand);
        info = root.findViewById(R.id.textViewInfo);

        database = new Database(getActivity());
        database.getReadableDatabase();

        Bundle bundle= getArguments();
        String newsID = bundle.getString("clickedID");
        Log.d(TAG, "NEWS ID = " + newsID);

        //varibles
        Cursor news = database.getNewsByID(newsID);
        news.moveToFirst();

        String a = news.getString(2); // 2 band 1 info
        String b = news.getString(1); // 2 band 1 info

        newsList = new ArrayList<>();

            newsList.add(a);
            newsList.add(b);

           DisplayNews(newsList);

        return root;
    }

    public void DisplayNews(ArrayList news)
    {

        if (news.size() != 0)
        {
            Log.d(TAG, "In if Display");

            String a = news.get(0).toString();

            Log.d(TAG, "NEWS =" + a);

            //Från news
            band.setText(news.get(0).toString());
            info.setText(news.get(1).toString());
        }
            else
            {
                Log.d(TAG, "Artist Empty");
            }
        }

    }


