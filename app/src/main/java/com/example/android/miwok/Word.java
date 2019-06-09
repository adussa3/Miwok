package com.example.android.miwok;

public class Word {

    // Default translation for the word
    private String defaltTranslation;

    // Miwok translation for the word
    private String miwokTranslation;

    /**
     * Constructs a new Word with initial values of the default language and Miwok language
     *
     * @param defaltTranslation is the word in a language that the user is already familiar with
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaltTranslation, String miwokTranslation) {
        this.defaltTranslation = defaltTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    /**
     * Get the default translation of the word
     * @return default translation of the word
     */
    public String getDefaltTranslation() {
        return defaltTranslation;
    }

    /**
     * Get the Miwok translation of the word
     * @return Miwok translation of the word
     */
    public String getMiwokTranslation () {
        return miwokTranslation;
    }
}
