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
        words.add(new Word(getResources().getString(R.string.one), "lutti", R.drawable.number_one));
        words.add(new Word(getResources().getString(R.string.two), "otiiko", R.drawable.number_two));
        words.add(new Word(getResources().getString(R.string.three), "tolookosu", R.drawable.number_three));
        words.add(new Word(getResources().getString(R.string.four), "oyyisa", R.drawable.number_four));
        words.add(new Word(getResources().getString(R.string.five), "massokka", R.drawable.number_five));
        words.add(new Word(getResources().getString(R.string.six), "temmokka", R.drawable.number_six));
        words.add(new Word(getResources().getString(R.string.seven), "keneaku", R.drawable.number_seven));
        words.add(new Word(getResources().getString(R.string.eight), "kawinta", R.drawable.number_eight));
        words.add(new Word(getResources().getString(R.string.nine), "wo'e", R.drawable.number_nine));
        words.add(new Word(getResources().getString(R.string.ten), "na'aacha", R.drawable.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of
        // {@link Word}s. The adapter knows how to create list item views for each item
        // in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);

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
