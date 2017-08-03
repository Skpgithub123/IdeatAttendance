package app.msupply.com.ideatattendance.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import app.msupply.com.ideatattendance.R;

public class TakePhotoCamera extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public static int count = 0;
    Uri outputFileUri; String file;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_camera);

        imageView = (ImageView) findViewById(R.id.imageView);
        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();

        Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.
                count++;
                 file = dir+count+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                }
                catch (IOException e)
                {
                }

                 outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
              /*  takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        activity.getCapturedImageURI());*/
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("resulvalues","******    "+requestCode+ " "+resultCode+ " "+data);
        Toast.makeText(getApplicationContext(),"******    "+requestCode+ " "+resultCode+ " "+data,Toast.LENGTH_LONG).show();

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");

            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }
}
