/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treetopsunexposure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 *
 * @author danielvorster
 */
public class TreetopSunExposure {

    /**
     * @param args the command line arguments
     */
    protected static Float[][] trees;
    protected static Float[][] terrain;
    protected static String INPUT_FILE = "sample_input.txt";
    protected static String OUTPUT_FILE = "output.txt";
    protected static int terrainX;
    protected static int terrainY;
    protected static final ForkJoinPool forkJoinPool = new ForkJoinPool();
    protected static Float total = 0.00000f;
    protected static long time;
    protected static int THRESHOLD = 10;
    protected static int REP = 1;
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Check for command line args
        if (args.length ==2) 
        {
            //There are args
            INPUT_FILE = args[0];
            OUTPUT_FILE = args[1];
        }
        else if (args.length == 3){
            //Extra arg for testing - specify threshold
            INPUT_FILE = args[0];
            OUTPUT_FILE = args[1];
            REP = Integer.parseInt(args[2]);
        }
        else if(args.length == 4){
            INPUT_FILE = args[0];
            OUTPUT_FILE = args[1];
            REP = Integer.parseInt(args[2]);
            THRESHOLD = Integer.parseInt(args[3]);
        }
        //If no args, use default params as defined above
        
        
        //Read setup params
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
        String[] tmp = br.readLine().split(" ");
        int[] terrainSize = {Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])};
 
        terrainX = terrainSize[0];
        terrainY = terrainSize[1];
        //System.out.println("Terrain X: " + terrainX);
        //System.out.println("Terrain Y: " + terrainY);

        
        //Define size of Terrain array
        terrain = new Float[terrainSize[0]][terrainSize[1]];
        String[] terrainValTmp = br.readLine().split(" ");
        //Read values into terrain
        System.out.println("Reading terrain values...");
        int valCount = 0;
        for (int x = 0; x < terrainSize[0]; x++){
            for (int y = 0; y < terrainSize[1]; y++) {
                terrain[x][y] = Float.parseFloat(terrainValTmp[valCount]);
                valCount ++;
            }
        }
        System.out.println("Read terrain values!\n");
        
        //Define size of trees array
        int treesSize = Integer.parseInt(br.readLine());
        trees = new Float[treesSize][5];
        System.out.println("Reading tree values...");
        for (int i = 0; i < treesSize; i++) 
        {
            String[] tmp1 = br.readLine().split(" ");
            // X coord
            trees[i][0] = Float.parseFloat(tmp1[0]);
            // Y coord
            trees[i][1] = Float.parseFloat(tmp1[1]);
            // Extent
            trees[i][2] = Float.parseFloat(tmp1[2]);
            //Avg.
            trees[i][3] = (float)0;
            //Pos
            trees[i][4] = (float)i;
        }
        System.out.println("Read tree values!\n");
        //System.out.println("Trees: " + trees.length);
        
        /*
        System.out.println("Test Tree:" + trees[60][0]);
        System.out.println("Test Tree:" + trees[60][1]);
        System.out.println("Test Tree:" + trees[60][2]);
        System.out.println("Test Tree:" + trees[60][3]);
        System.out.println("Test Tree:" + trees[60][4]);
        System.out.println("Read tree values!");
        System.out.println("Trees: " + trees.length);
        System.out.println("Starting parallel processing...");
        
        */
        
        //Start parallel processing
        startTimer();
        forkJoinPool.invoke(new RecursiveProcess(trees));
        stopTimer();
        
        //System.out.println("Average: " + total/trees.length);
        
        //Write to file
        /* Format:
        Avg. per tree
        No. Trees
        Total tree x
        Total tree y...
        */
        System.out.println("Started writing...");
        PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE));
        pw.println("" + total/(trees.length));
       
        //System.out.println("Total: " + (double)total);
        pw.println("" + trees.length);
        
        for (int i = 0; i < trees.length; i++) 
        {
            pw.println(trees[i][3]);
        }
        pw.close();
        System.out.println("Finished writing!");
    }
    
    public static void setTotal(int index, Float total){
        trees[index][3] = total;
    }
    
    public static void startTimer(){
        System.out.println("Started timing...");
        time = System.currentTimeMillis();
    }
    
    public static void stopTimer(){
        Long currentTime = System.currentTimeMillis();
        System.out.println("Stopped timing!\n");
        System.out.println("Total parallel runtime:\t\t" + (currentTime - time) + "ms\n");
       
    }
    
    
    
    
    
}
