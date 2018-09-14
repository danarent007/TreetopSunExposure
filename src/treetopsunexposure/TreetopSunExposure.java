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
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Read setup params
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] tmp = br.readLine().split(" ");
        int[] terrainSize = {Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])};
 
      
        //Define size of Terrain array
        terrain = new Float[terrainSize[0]][terrainSize[1]];
        String[] terrainValTmp = br.readLine().split(" ");
        //Read values into terrain
        int valCount = 0;
        for (int x = 0; x < terrainSize[0]; x++){
            for (int y = 0; y < terrainSize[1]; y++) {
                terrain[x][y] = Float.parseFloat(terrainValTmp[valCount]);
                valCount ++;
            }
        }
        
        //Define size of trees array
        int treesSize = Integer.parseInt(br.readLine());
        trees = new Float[treesSize][4];
        
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
        }
        
    }
    
    
    
}
