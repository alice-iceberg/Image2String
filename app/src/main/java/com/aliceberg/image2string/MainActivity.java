package com.aliceberg.image2string;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        String noNewLine = imageString.replace("\n", "");

        System.out.println(noNewLine);

        File file = new File(this.getExternalFilesDir(null), "testfile2.txt");
        FileOutputStream fileOutput = null;
        try {

            fileOutput = new FileOutputStream(file);
            Log.e("TAG", "onCreate: START" );
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutput);
            outputStreamWriter.write(noNewLine);
            outputStreamWriter.flush();
            fileOutput.getFD().sync();
            outputStreamWriter.close();
            Log.e("TAG", "onCreate: DONE"  );
        } catch (IOException e) {
            e.printStackTrace();
        }


        //byte[] result = decodeImage(imageString);






//        byte [] encodeByte=Base64.decode(imageString,Base64.DEFAULT);
//        Bitmap bitmap1=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);




        //     try {


//                imageInFile = new FileInputStream(file);
//            byte[] imageData = new byte[(int) file.length()];
//           imageInFile.read(imageData);




//            //converting image byte array into base 64 String
//            String imageDataString = encodeImage(imageData);
//
            // Converting a Base64 String into Image byte array
//            byte[] imageByteArray = decodeImage(imageString);
//            // Write a image byte array into file system
//        try {
//           FileOutputStream imageOutFile = new FileOutputStream(
//                   "/Users/aliceberg/Desktop");
//            imageOutFile.write(result);
//            imageOutFile.close();
//        } catch (FileNotFoundException e) {
//           e.printStackTrace(); } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//
//

//
//            imageInFile.close();

//
//            System.out.println("Image Successfully Manipulated!");

//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found" + e);
//        } catch (IOException ioe) {
//            System.out.println("Exception while reading the Image " + ioe);
//        }

    }

    public static String encodeImage(byte[] imageByteArray) {
        String result = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(imageByteArray);
        Log.e("TAG", "encodeImage: " + result);
        return result;

    }

    public static byte[] decodeImage(String imageDataString) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(imageDataString);
    }
}