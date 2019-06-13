package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    //public static HashMap<String, String> numbersMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create a list of Word objects
        List<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.one), "lutti"));
        words.add(new Word(getResources().getString(R.string.two), "otiiko"));
        words.add(new Word(getResources().getString(R.string.three), "tolookosu"));
        words.add(new Word(getResources().getString(R.string.four), "oyyisa"));
        words.add(new Word(getResources().getString(R.string.five), "massokka"));
        words.add(new Word(getResources().getString(R.string.six), "temmokka"));
        words.add(new Word(getResources().getString(R.string.seven), "keneaku"));
        words.add(new Word(getResources().getString(R.string.eight), "kawinta"));
        words.add(new Word(getResources().getString(R.string.nine), "wo'e"));
        words.add(new Word(getResources().getString(R.string.ten), "na'aacha"));

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
