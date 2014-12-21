package com.example.scott.lighttest;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by scott on 12/20/14.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}