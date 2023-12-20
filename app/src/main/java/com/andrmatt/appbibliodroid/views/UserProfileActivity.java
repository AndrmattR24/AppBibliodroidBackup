package com.andrmatt.appbibliodroid.views;

import static com.andrmatt.appbibliodroid.R.drawable.shape_custom_profile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    //viewBinding
    private ActivityUserProfileBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding =  ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        _binding.changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caneraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });
    }

    ActivityResultLauncher<Intent> caneraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {

                    //valid check Foto
                    if(o.getResultCode() == RESULT_OK){
                        Bundle extras = o.getData().getExtras();
                        Bitmap imBitmap = (Bitmap) extras.get("data");
                        _binding.userProfile.setImageBitmap(imBitmap);

                    }
                }
            }
    );
}