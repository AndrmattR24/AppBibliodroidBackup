package com.andrmatt.appbibliodroid.guards;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.andrmatt.appbibliodroid.views.DashboardUserActivity;
import com.andrmatt.appbibliodroid.views.UserProfileActivity;

public class LocalGuardActivity extends AppCompatActivity {
    protected LocalGuard localGuard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localGuard = new LocalGuard(this.getApplication());
        localGuard.verify();
    }

    protected void renderInfo(LocalGuardView view) {
        this.localGuard.showInfoTextView(view.getViewTextEmail());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    protected class OnClickLogout implements View.OnClickListener {
        private LocalGuard localGuard;
        public OnClickLogout(LocalGuard localGuard) {
            this.localGuard = localGuard;
        }
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builderMessage = new AlertDialog.Builder(LocalGuardActivity.this);
            builderMessage.setTitle("Logout").setMessage("Are You Sure Exit ?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        localGuard.logout();
                        Toast.makeText(LocalGuardActivity.this, "Closed Session", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        }
    }

    public class OnRedirectProfile implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LocalGuardActivity.this, UserProfileActivity.class));
        }
    }
}
