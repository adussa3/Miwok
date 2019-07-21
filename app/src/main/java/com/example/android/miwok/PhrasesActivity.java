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

public class PhrasesActivity extends AppCompatActivity {

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
        words.add(new Word(getResources().getString(R.string.where_are_you_going), "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word(getResources().getString(R.string.what_is_your_name), "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word(getResources().getString(R.string.my_name_is), "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word(getResources().getString(R.string.how_are_you_feeling), "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word(getResources().getString(R.string.im_feeling_good), "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word(getResources().getString(R.string.are_you_coming), "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word(getResources().getString(R.string.yes_im_coming), "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word(getResources().getString(R.string.im_coming), "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word(getResources().getString(R.string.lets_go), "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word(getResources().getString(R.string.come_here), "әnni'nem", R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of
        // {@link Word}s. The adapter knows how to create list item views for each item
        // in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_phrases);

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
         * Set a click listener to play the audio when the list item is clicked on
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
                    mMediaPlayer = (MediaPlayer) MediaPlayer.create(PhrasesActivity.this, currentWord.getAudioResourceId());

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
