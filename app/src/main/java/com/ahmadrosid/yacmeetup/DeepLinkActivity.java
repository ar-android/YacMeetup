package com.ahmadrosid.yacmeetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteReferral;

/**
 * Created by ocittwo on 3/24/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class DeepLinkActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = DeepLinkActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deep_link_activity);

        findViewById(R.id.button_ok).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (AppInviteReferral.hasReferral(intent)) {
            processReferralIntent(intent);
        }
    }

    // [START process_referral_intent]
    private void processReferralIntent(Intent intent) {
        // Extract referral information from the intent
        String invitationId = AppInviteReferral.getInvitationId(intent);
        String deepLink = AppInviteReferral.getDeepLink(intent);

        // Display referral information
        // [START_EXCLUDE]
        Log.d(TAG, "Found Referral: " + invitationId + ":" + deepLink);
        ((TextView) findViewById(R.id.deep_link_text))
                .setText(getString(R.string.deep_link_fmt, deepLink));
        ((TextView) findViewById(R.id.invitation_id_text))
                .setText(getString(R.string.invitation_id_fmt, invitationId));
        // [END_EXCLUDE]
    }
    // [END process_referral_intent]

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_ok) {
            finish();
        }
    }

}
