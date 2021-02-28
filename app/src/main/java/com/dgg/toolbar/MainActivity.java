package com.dgg.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity {

    CollapsingToolbarLayout mCollapsingToolbarLayout;
    ImageView mHeaderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mHeaderImage = findViewById(R.id.header_image);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float percentage = Math.abs(verticalOffset) / (float)totalScrollRange;

                if (percentage > 0.7) {
                    // Collapsed
                    mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
                } else {
                    // Expanded
                    mCollapsingToolbarLayout.setTitle("");
                }

                // When half of AppBar is folded, the image is invisible.
                mHeaderImage.setAlpha(1 - (percentage*2));
            }
        });
    }
}
