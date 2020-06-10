package com.example.hindicv;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE_LOAD_IMAGE = 1001;

    ImageView imgContent;
    Button btnLoad, btnGray, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgContent = findViewById(R.id.img_content);
        btnLoad = findViewById(R.id.btn_load);
        btnGray = findViewById(R.id.btn_gray);
        btnSave = findViewById(R.id.btn_save);

//        Mat A, C;                                 // creates just the header parts
//        A = imread(argv[1], CV_LOAD_IMAGE_COLOR); // here we'll know the method used (allocate matrix)
//
//        Mat B(A);
//
//        C = A;

        checkOpenCV();
        final String pathSave = Environment.getExternalStorageDirectory().toString();

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_LOAD_IMAGE);
            }
        });

        btnGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mat matRgb = new Mat();
                Utils.bitmapToMat(curBitmap,matRgb);
                Mat matGray = new Mat();

                int width = curBitmap.getWidth();
                int height = curBitmap.getHeight();

                grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

                Imgproc.cvtColor(matRgb, matGray, Imgproc.COLOR_RGB2GRAY);
                Utils.matToBitmap(matGray, grayBitmap);
                imgContent.setImageBitmap(grayBitmap);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    File pictureFile = new File(pathSave,"hindi");
                    if(!pictureFile.exists()) {
                        pictureFile.mkdirs();
                    }

                    File file = new File (pictureFile, "image"+System.currentTimeMillis()+".png");
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        grayBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                        fos.close();
                        Log.d("QuyetKa","Save Image Succesful");
                    } catch (FileNotFoundException e) {
                        Log.e("QuyetKa",e.toString());
                    } catch (IOException e) {
                        Log.e("QuyetKa",e.toString());
                    }

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1002);
                }

            }
        });



    }

    private void checkOpenCV() {
        if (OpenCVLoader.initDebug()) {
            Log.d("QuyetKa", "Open CV Add Successful");
        } else {
            Log.d("QuyetKa", "Open CV Add Failed");
        }
    }

    public static Bitmap edgesim(Bitmap first) {

        Bitmap image1;

        ///////////////transform back to Mat to be able to get Canny images//////////////////
        Mat img1 = new Mat();
        Utils.bitmapToMat(first, img1);

        //mat gray img1 holder
        Mat imageGray1 = new Mat();

        //mat canny image
        Mat imageCny1 = new Mat();

        //Convert img1 into gray image
        Imgproc.cvtColor(img1, imageGray1, Imgproc.COLOR_BGR2GRAY);

        //Canny Edge Detection
        Imgproc.Canny(imageGray1, imageCny1, 10, 100, 3, true);

        ///////////////////////////////////////////////////////////////////

        //////////////////Transform Canny to Bitmap/////////////////////////////////////////
        image1 = Bitmap.createBitmap(imageCny1.cols(), imageCny1.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(imageCny1, image1);

        return image1;
    }

    Bitmap curBitmap;
    Bitmap grayBitmap;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("QuyetKa","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOAD_IMAGE && resultCode == RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                curBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);

                //Get Patch
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);

                Log.d("QuyetKa", "Uri" + imageUri);
                Log.d("QuyetKa", "picturePath" + picturePath);


                imgContent.setImageBitmap(curBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
