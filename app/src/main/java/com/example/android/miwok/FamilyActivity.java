package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means we've lost audio focus for a
                    // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume. We'll treat
                    // both cases the same way because our app is playing short sounds files.

                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the word from the beginning when we resume playback.
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can
                    // resume playback
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                    // stop playback and clean up resources
                    releaseMediaPlayer();
                    break;
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     * */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Release the resource after the sound file has finished playing
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of Word objects
        List<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.father), "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getResources().getString(R.string.mother), "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getResources().getString(R.string.son), "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getResources().getString(R.string.daughter), "tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word(getResources().getString(R.string.older_brother), "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getResources().getString(R.string.younger_brother), "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getResources().getString(R.string.older_sister), "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getResources().getString(R.string.younger_sister), "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getResources().getString(R.string.grandmother), "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getResources().getString(R.string.grandfather), "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

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

        /**
         * Set a click listener to play audio when the list item is clicked on
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word currentWord = (Word) parent.getItemAtPosition(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = (MediaPlayer) MediaPlayer.create(FamilyActivity.this, currentWord.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Set up a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This is also
            // unregisters the AudioFocusChangeListener so we don't get any more callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
