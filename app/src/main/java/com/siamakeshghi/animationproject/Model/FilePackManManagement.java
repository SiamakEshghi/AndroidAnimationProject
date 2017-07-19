package com.siamakeshghi.animationproject.Model;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * Created by siamakeshghi on 2017-05-27.
 */

public class FilePackManManagement {

    public  static ArrayList<PackMan> readFile(Context contxt, String fileName)
    {
        ArrayList<PackMan> listOfPacs = new ArrayList<PackMan>();
        AssetManager assetManaget = contxt.getResources().getAssets();


        try {
            InputStreamReader isr = new InputStreamReader(assetManaget.open(fileName));
            BufferedReader br = new BufferedReader(isr);
            String oneLine;

            while ((oneLine = br.readLine()) != null){

                StringTokenizer st = new StringTokenizer(oneLine,",");

                String name = st.nextToken();

                //String imageName = st.nextToken();
//                int imageResourceId = contxt.getResources().getIdentifier(imageName,"drawable"
//                        ,contxt.getPackageName());



                int speed = Integer.valueOf(st.nextToken());

                float xPos = Float.valueOf(st.nextToken());
                float yPos = Float.valueOf(st.nextToken());

                int xDir = Integer.valueOf(st.nextToken());
                int yDir = Integer.valueOf(st.nextToken());

                PackMan newPacMan = new PackMan(name,speed,xPos,yPos,xDir,yDir);

                listOfPacs.add(newPacMan);

            }
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPacs;

    }
}
