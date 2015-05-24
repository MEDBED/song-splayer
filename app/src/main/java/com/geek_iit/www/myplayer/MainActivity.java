package com.geek_iit.www.myplayer;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    //this our songs list
    public ListView Lv;
    //this is an array of string that contains each songs in our phone
    public String [] Items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is my list view instance
        Lv =(ListView)findViewById(R.id.lvPlayList);
        //this is a array list to  put all the files on my phone that cantains the extention .mp3
        //or .wave in arraylist Mysongs
        ArrayList<File> MySongs=FindSongs(Environment.getExternalStorageDirectory());
        //fixe the size of the tabale itemes base on the size of MySong to create the items list
        Items = new String [ MySongs.size() ];
        //Now cover all the array list and put each element in the Items tabale and toast the name
        //of each songs
        for (int i=0;i<MySongs.size();i++)
        {
            //toast the name of the song i's like a alert
            toast(MySongs.get(i).getName().toString());
            //put the name of the songs in the items tabale
            Items[i]=MySongs.get(i).getName().toString();
        }
        //this is to controle the data how to display in our case the date is a Tabale of string
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, Items);
        //set our adapter to the listview
        Lv.setAdapter(Adapter);

    }
   //this how i'm gonna prepare my list of songs
    public ArrayList<File> FindSongs(File root){
        //Al is arraylist contains sonfs that we find on our phone
        ArrayList<File> Al =new ArrayList<File>();
        //Tabale files contains all file scan in our directory
        File [] Files =root.listFiles();
        //cover the files and test if the file is a directory and not a hidden file we recall our
        // function else we add the file to array-list Al
        for (File singleFile: Files){
          if (singleFile.isDirectory()&& !singleFile.isHidden()){
              Al.addAll(FindSongs(singleFile));
          }
          else{
              if(singleFile.getName().endsWith(".mp3")||singleFile.getName().endsWith(".wav")){
                  Al.add(singleFile);
              }
          }
        }
        //return the list of the file songs that we find
        return Al;
    }

    //Methode allow as to show a toast like alert
    public void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
