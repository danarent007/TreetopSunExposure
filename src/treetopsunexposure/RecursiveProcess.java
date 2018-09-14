/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treetopsunexposure;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author danielvorster
 */
public class RecursiveProcess extends RecursiveAction{

    //Defined workload var, threshold for workload cutoff
    private String[][] workload;
    private static final int THRESHOLD = 10;
   
    //Constructor for method
    public RecursiveProcess(String[][] workload){
        this.workload = workload;
    }
    
    @Override
    protected void compute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
                
                
                
    }
    
}
