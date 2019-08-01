package com.example.crdr.layouttestingapplication;

import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongListFromDevice extends AppCompatActivity {

    private  static final int MY_PERMISSION_REQUEST=1;

    ArrayList<File> fileArrayList=new ArrayList<>();
    ArrayList<String> songArrayList=new ArrayList<>();
    ListView lvSongList;
    ArrayAdapter<String> adapter;
    private File root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_from_device);

        lvSongList=findViewById(R.id.lvSongList);

       /* File files=new File("/sdcard/Music/"*//*Environment.getExternalStorageDirectory().getAbsolutePath()*//*);
        MediaMetadataRetriever mmr=new MediaMetadataRetriever();
        Log.e("getAbsolutePath ",files.getAbsolutePath());
        Log.e("toString",files.toString());
        Log.e("canRead", String.valueOf(files.canRead()));

        if(files.listFiles(new FileExtensionFilter()).length > 0)
        {
            for (int i=0;i<files.listFiles(new FileExtensionFilter()).length;i++) {
                File file = files.listFiles(new FileExtensionFilter())[i];
                String fname = file.getName();
                String fpath = file.getAbsolutePath();
                *//*if (file.isFile() && fname.contains("."))
                {
                   *//*
                String ext = fname.substring(fname.lastIndexOf("."));
                    *//*if (ext.equals(".mp3") || ext.equals(".MP3"))
                    {*//*


//                if (file.length() > 0 && file.length() != 0) {
                    mmr.setDataSource(fpath);
                    String title = mmr.extractMetadata(7);
                    String artist = mmr.extractMetadata(2);
                    String genre = mmr.extractMetadata(6);
                    String album = mmr.extractMetadata(1);
                    if(title!=null &&  artist!=null && genre!=null && album!=null )
                    {
                        Log.e("title ", title);
                        Log.e("artist ", artist);
                        Log.e("genre ", genre);
                        Log.e("album ", album);
                    }

                 //}

            }

                    //}
                //}
        }
        else {
            Log.e("album ", "finish");
        }


       mmr.release();*/

        ArrayList<HashMap<String,String>> songList=getPlayList(Environment.getExternalStorageDirectory().getAbsolutePath());
        if(songList!=null){
            for(int i=0;i<songList.size();i++){
                String fileName=songList.get(i).get("file_name");
                String filePath=songList.get(i).get("file_path");
                //here you will get list of file name and file path that present in your device
                Log.e("file details "," name ="+fileName +" path = "+filePath);
            }
        }

    }

    ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }
   class FileExtensionFilter implements FilenameFilter {
       public boolean accept(File dir, String name) {
           return (name.endsWith(".mp3") || name.endsWith(".MP3"));
       }
   }

}
