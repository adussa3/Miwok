package com.example.android.miwok;

public class Word {

    // Default translation for the word
    private String mDefaultTranslation;

    // Miwok translation for the word
    private String mMiwokTranslation;

    // Image resource ID for the word
    private int mImageResourceId;

    // Audio resource ID for the word
    private int mAudioResourceId;

    //
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Constructs a new Word with initial values of the default language and Miwok language
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     * @param miwokTranslation is the word in the Miwok language
     * @param audioResourceId is the raw resource ID for the audio associated with the word
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this(defaultTranslation, miwokTranslation, NO_IMAGE_PROVIDED, audioResourceId);
    }

    /**
     * Constructs a new Word with initial values of the default language, Miwok language, and the
     * image resource ID
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     * @param miwokTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the raw resource ID for the audio associated with the word
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default translation of the word
     * @return default translation of the word
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word
     * @return Miwok translation of the word
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the image resource ID of the word
     * @return image resource ID of the word
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Checks whether there is an image for the word
     * @return boolean checking whether there is an image for the word
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get the audio resource ID of the word
     * @return audio resource ID of the word
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /**
     * Returns the string representation of the {@link Word} object.
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
