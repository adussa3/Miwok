package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create a list of Word objects
        List<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.red), "weṭeṭṭi"));
        words.add(new Word(getResources().getString(R.string.green), "chokokki"));
        words.add(new Word(getResources().getString(R.string.brown), "ṭakaakki"));
        words.add(new Word(getResources().getString(R.string.gray), "ṭopoppi"));
        words.add(new Word(getResources().getString(R.string.black), "kululli"));
        words.add(new Word(getResources().getString(R.string.white), "kelelli"));
        words.add(new Word(getResources().getString(R.string.dusty_yellow), "ṭopiisә"));
        words.add(new Word(getResources().getString(R.string.mustard_yellow), "chiwiiṭә"));

        // Create an {@link WordAdapter}, whose data source is a list of
        // {@link Word}s. The adapter knows how to create list item views for each item
        // in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words);

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
