package in.nash.showtime.ui.view.impl;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import in.nash.showtime.R;

/**
 * Created by avinash on 8/31/15.
 */
public class SettingsFragment  extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        Preference preferenceEditProfile = findPreference("edit_profile");
        preferenceEditProfile.setOnPreferenceClickListener(this);
        Preference preferenceShare = findPreference("share");
        preferenceShare.setOnPreferenceClickListener(this);
        Preference preferenceAbout = findPreference("about");
        preferenceAbout.setOnPreferenceClickListener(this);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "edit_profile":
                break;
            case "share":
                break;
            case "about":
                break;
        }
        return false;
    }
}