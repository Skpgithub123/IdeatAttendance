package app.msupply.com.ideatattendance.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import app.msupply.com.ideatattendance.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkAttendance extends Fragment {


    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_OK = 1888;
    public static int count = 0;
    Uri outputFileUri; String file;
    ImageView imageView;

    int TAKE_PHOTO_CODE = 100;
    public MarkAttendance() {
        // Required empty public constructor
    }

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_mark_attendance, container, false);

        textView = v.findViewById(R.id.mark_attance);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Attendance Sent Successfully",Toast.LENGTH_LONG).show();
            }
        });
        final Fragment frag = this;
        imageView = v.findViewById(R.id.imageView);
        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();

        Button capture =  v.findViewById(R.id.btnCapture);
       // Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise



                count++;
                String file = dir+count+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                }
                catch (IOException e)
                {
                }



                    outputFileUri = Uri.fromFile(newfile);

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra("data", outputFileUri);

                Log.d("responcevodrefddf","success"+outputFileUri);
                    // cameraIntent.setAction(Intent.ACTION_PICK);*/
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);

                  /*   }
                     else
                     {
                         outputFileUri = Uri.fromFile(newfile);
                          Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                     *//*   takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        activity.getCapturedImageURI());*//*
                        cameraIntent.putExtra("data", outputFileUri);
                         Log.d("outputurivalues","****    "+outputFileUri);
                    //     cameraIntent.setAction(Intent.ACTION_CAMERA_BUTTON);
                         startActivityForResult(cameraIntent, CAMERA_REQUEST);
                      *//*   Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                         cameraIntent.addCategory(Intent.ACTION_CAMERA_BUTTON);
                         cameraIntent.setType("image/jpeg");

*//*
                     }*/


            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("resulvalues","******    "+requestCode+ " "+resultCode+ " "+data);
        Toast.makeText(getActivity(),"******    "+requestCode+ " "+resultCode+ " "+data,Toast.LENGTH_LONG).show();

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
            Toast.makeText(getActivity(),"******    "+requestCode+ " "+resultCode+ " "+data,Toast.LENGTH_LONG).show();
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            Log.d("iamge", ""+mphoto);
            imageView.setImageBitmap(mphoto);
        }

    }


    /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("resulvalues","******    "+requestCode+ " "+resultCode+ " "+data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");

            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }*/

}
