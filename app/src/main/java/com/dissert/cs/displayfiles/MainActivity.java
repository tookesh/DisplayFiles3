package com.dissert.cs.displayfiles;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    private List<FileDetails> mFileDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.myListView);

        mFileDetails = new ArrayList<>();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DownloadApp";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        Log.d("Files", "Directory: " + directory);

        if(!directory.exists()) {
            Log.d("Files", "No such directory!");
        } else {
            File[] files = directory.listFiles();
//                    Log.d("Files", "Files: " + files);
            Log.d("Files", "Size: "+ files.length);
            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());

                mFileDetails.add(new FileDetails(files[i].getName(), files[i].getAbsolutePath()));
            }

            MyCustomAdapter myCustomAdapter = new MyCustomAdapter(getApplicationContext(), mFileDetails);
            myListView.setAdapter(myCustomAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FileDetails fd = (FileDetails) parent.getItemAtPosition(position);
                    String videoPath = fd.getFilePath();
                    Log.d("Files", "File Name: " + videoPath);
                    Toast.makeText(MainActivity.this, "File Path: " + videoPath, Toast.LENGTH_SHORT).show();

                    Intent myIntent = new Intent(MainActivity.this, VideoPlayer.class);
                    myIntent.putExtra("videoPath", videoPath);
                    startActivity(myIntent);

                }
            });
        }
    }
}


