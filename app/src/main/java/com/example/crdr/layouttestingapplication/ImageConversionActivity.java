package com.example.crdr.layouttestingapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ImageConversionActivity extends AppCompatActivity {

    Button btnSet;

    ImageView ivFirstImage, ivSecondImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_conversion);

        btnSet=findViewById(R.id.btnSet);
        ivFirstImage=findViewById(R.id.imageView);
        ivSecondImage=findViewById(R.id.imageView2);

        //ivFirstImage.setImageResource(R.drawable.brave);

        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brave);
        int bitmapByteCount=BitmapCompat.getAllocationByteCount(bitmap);
        Log.e("image size 1", String.valueOf(bitmapByteCount));

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //decode base64 string to image
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        ivFirstImage.setImageBitmap(decodedImage);




        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) ivFirstImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                int bitmapByteCount=BitmapCompat.getAllocationByteCount(bitmap);
                Log.e("image size 2", String.valueOf(bitmapByteCount));

                /*int nh = (int) ( bitmap.getHeight() * (256.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 256, nh, true);

                int bitmapByteCount3=BitmapCompat.getAllocationByteCount(scaled);
                Log.e("image size 3", String.valueOf(bitmapByteCount3));

                ivSecondImage.setImageBitmap(scaled);*/
                ivSecondImage.setImageBitmap(bitmap);
            }
        });

    }
}
