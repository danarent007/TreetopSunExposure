/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treetopsunexposure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    static Float[][] terrain;
    static String fileName = "sample_input.txt";
    static int terrainX;
    static int terrainY;
    static final ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Read setup params
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] tmp = br.readLine().split(" ");
        int[] terrainSize = {Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])};
 
        terrainX = terrainSize[0];
        terrainY = terrainSize[1];
        System.out.println("Terrain X: " + terrainX);
        System.out.println("Terrain Y: " + terrainY);

        
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
        System.out.println("Read terrain values!");
        
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
            trees[i][3] = null;
            //Pos
            trees[i][4] = (float)i;
        }
        System.out.println("Read tree values!");
        System.out.println("Trees: " + trees);
        System.out.println("Starting parallel processing...");
        
        forkJoinPool.invoke(new RecursiveProcess(trees));
        
        System.out.println("Done parallel processing!");
        
    }
    
    public static void setAverage(int index, Float average){
        trees[index][3] = average;
    }
    
    
    
    
    
}
