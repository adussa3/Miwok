package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create a list of Word objects
        List<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.father), "әpә", R.drawable.family_father));
        words.add(new Word(getResources().getString(R.string.mother), "әṭa", R.drawable.family_mother));
        words.add(new Word(getResources().getString(R.string.son), "angsi", R.drawable.family_son));
        words.add(new Word(getResources().getString(R.string.daughter), "tune", R.drawable.family_daughter));
        words.add(new Word(getResources().getString(R.string.older_brother), "taachi", R.drawable.family_older_brother));
        words.add(new Word(getResources().getString(R.string.younger_brother), "chalitti", R.drawable.family_younger_brother));
        words.add(new Word(getResources().getString(R.string.older_sister), "teṭe", R.drawable.family_older_sister));
        words.add(new Word(getResources().getString(R.string.younger_sister), "kolliti", R.drawable.family_younger_sister));
        words.add(new Word(getResources().getString(R.string.grandmother), "ama", R.drawable.family_grandmother));
        words.add(new Word(getResources().getString(R.string.grandfather), "paapa", R.drawable.family_grandfather));

        // Create an {@link WordAdapter}, whose data source is a list of
        // {@link Word}s. The adapter knows how to create list item views for each item
        // in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_family);

        /**
         * Create an {@link ListView} object in the view hierarchy of the {@link Activity}.
         * There should be a {@link ListView} with the view ID called list, which is declared in
         * word_list layout file
         */
        ListView listView = (ListView) findViewById(R.id.list);

        /**
         * Make the {@list ListView} use the {@link WordAdapter} we created above, so that the
         * {@link ListView} will display list items for each word in the list of words.
         * Do this by calling the setAdapter method on the {@link Listview} object and pass in
         * 1 argument, which is the {@link WordAdapter} with the variable name wordAdapter.
         */
        listView.setAdapter(wordAdapter);
    }
}
