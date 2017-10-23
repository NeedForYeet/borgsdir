package com.auerbauerjensch.borgsdir;

/**
 * Created by user on 20.01.2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "product_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        // this string contains the product name
        final String productName = intent.getStringExtra(EXTRA_NAME);

        //todo: fix toolbar. back arrow doesnt work either.
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(productName);

        loadBackdrop(productName);

        // make backdrop clickable and show gallery of product pictures
        View backdrop = (View) findViewById(R.id.backdrop);
        backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailActivity.this, FullscreenGalleryView.class));
            }
        });

        // listener for Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.details_floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailActivity.this, ContactSellerActivity.class));
            }
        });

        View borrowerCardView = (View) findViewById(R.id.cardview_borrower);
        borrowerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailActivity.this, ViewProfileActivity.class));
            }
        });

    }

    /**
     * Loads an image suitable for the product description
     *
     * @param productName
     */
    private void loadBackdrop(String productName) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(AvailableProducts.getRandomProductLogo(productName)).centerCrop().into(imageView);

    }
}
