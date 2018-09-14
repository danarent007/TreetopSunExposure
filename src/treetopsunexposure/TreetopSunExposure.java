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
    String[][] trees;
    static Float[][] terrain;
    static String fileName = "sample_input.txt";
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Read setup params
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] tmp = br.readLine().split(" ");
      
        //Define size of Terrain array
        terrain = new Float[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
        
        
    }
    
}
