package com.example.gruppprojektet_musicapp.ui.addNews;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.Database.News;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.news.NewsFragment;

public class AddNewsFragment extends Fragment {

    private AddNewsViewModel addNewsViewModel;

    Database mDatabaseHelper;
    public static SQLiteDatabase setupDB = null;
    private Button btnAddNews, btnShowNewsList;
    private EditText newsNameText, newsDescriptionText;
    private News news;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addNewsViewModel =
                ViewModelProviders.of(this).get(AddNewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_news, container, false);

        // Inputs
        newsNameText = (EditText)root.findViewById(R.id.newsNameText);
        newsDescriptionText = (EditText)root.findViewById(R.id.newsDescriptionText);

        // Buttons
        btnAddNews = (Button)root.findViewById(R.id.btnAddNews);
        btnShowNewsList = (Button)root.findViewById(R.id.btnShowNewsList);

        mDatabaseHelper = new Database(getActivity());
        news = new News();

        btnAddNews.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (newsNameText.length() != 0)
                {
                    AddNews();
                }
                else
                {
                    toastMessage("Field cannot be empty!");
                }
            }
        });

        btnShowNewsList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NewsFragment newsFragment = new NewsFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, newsFragment).commit();
            }
        });
        return root;
    }

    public void AddNews()
    {
        news.setName(newsNameText.getText().toString().trim());
        news.setDescription(newsDescriptionText.getText().toString().trim());
        mDatabaseHelper.addNews(news);

        toastMessage("Data succesfully saved!");
    }

    public void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
