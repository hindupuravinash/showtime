package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import in.nash.showtime.R;

/**
 * Created by avinash on 8/31/15.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        Preference preferenceEditProfile = findPreference("edit_profile");
        preferenceEditProfile.setOnPreferenceClickListener(this);
        Preference preferenceShare = findPreference("share");
        preferenceShare.setOnPreferenceClickListener(this);
        Preference preferenceFeedback = findPreference("feedback");
        preferenceFeedback.setOnPreferenceClickListener(this);
        Preference preferenceFeature = findPreference("feature");
        preferenceFeature.setOnPreferenceClickListener(this);
        Preference preferenceAttributions = findPreference("attributions");
        preferenceAttributions.setOnPreferenceClickListener(this);
        Preference preferenceAbout = findPreference("about");
        preferenceAbout.setOnPreferenceClickListener(this);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "edit_profile":
                break;
            case "share":
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Checkout Showtime - Movies information, beautifully designed: https://goo.gl/ONNCOq";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout Showtime");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case "feedback":
                FeedbackActivity.navigateTo(getActivity());
                break;
            case "feature":
                FeatureRequestActivity.navigateTo(getActivity());
                break;
            case "attributions":
                AttributionsActivity.navigateTo(getActivity());
                break;
            case "about":
                AboutActivity.navigateTo(getActivity());
                break;
        }
        return false;
    }
}