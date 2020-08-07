package com.aliceberg.image2string;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    File file = new File("C:\\Users\\NSL\\Desktop\\Sabina\\me.jpg");


        //reading the file
       FileInputStream imageInFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            imageInFile = new FileInputStream(file);
            byte imageData [] = new byte[(int)file.length()];
            imageInFile.read(imageData);

            //converting image byte array into base 64 String
            String imageDataString = encodeImage(imageData);

            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(imageDataString);
            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream(
                    "C:\\Users\\NSL\\Desktop\\Sabina\\meback.jpg");

            imageOutFile.write(imageByteArray);

            imageInFile.close();
            imageOutFile.close();

            System.out.println("Image Successfully Manipulated!");

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

    }

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}