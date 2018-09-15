/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treetopsunexposure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author danielvorster
 */
public class RecursiveProcess extends RecursiveAction{

    //Defined workload var, threshold for workload cutoff
    protected Float[][] workload;
    
   
    //Constructor for method
    public RecursiveProcess(Float[][] workload){
        this.workload = workload;
    }
    
    @Override
    protected void compute() {
        // Check threshold
        if (workload.length > TreetopSunExposure.THRESHOLD) 
        {
            //Split
            ForkJoinTask.invokeAll(splitTasks());
        }
        else{
            process(workload);
        }                 
    }
    
    //Create a list of subtasks for computation
    private List<RecursiveProcess> splitTasks(){
        
        List<RecursiveProcess> subtasks = new ArrayList<>();
        
        //Split array at half-way point
        int workloadLength = workload.length;
        int splitPoint = (int)Math.floor(workloadLength/2);
        //System.out.println("");
        //System.out.println("Split Point: " + splitPoint);
        //System.out.println("Workload Length: " + workloadLength);
        //System.out.println("");
        int elements = workload[0].length;
        //Copy elements
        Float[][] arrayOne = new Float[splitPoint][elements];
        
        for (int i = 0; i < splitPoint; i++) 
        {
            arrayOne[i] = workload[i];
        }
        Float[][] arrayTwo = new Float[workloadLength-splitPoint][elements];
        int b = 0;
        for (int i = splitPoint; i < workloadLength; i++) 
        {
            arrayTwo[b] = workload[i]; //PROBLEM!
            b++;
        }
        
        //Add net subtasks to list
        subtasks.add(new RecursiveProcess(arrayOne));
        subtasks.add(new RecursiveProcess(arrayTwo));
        
        return subtasks;
    }
    
    //Calculate 
    private void process(Float[][] workload){
        
        
        //Iterate through workload items
        Float total = (float)0;
        for (int i = 0; i < workload.length; i++) 
        {
            total = (float)0;
            //Value for counting additions
            int area = 0;
            //Get integer values from current tree
            int startX = Math.round(workload[i][0]);
            int startY = Math.round(workload[i][1]);
            int extent = Math.round(workload[i][2]);
            
            //Add all Floats from TreetopSunExposure.terrain
            for (int x = startX; x < startX + extent; x++) {
                for (int y = startY; y < startY + extent; y++) {
                    //Check if within bounds before referencing null arr
                    if (x < TreetopSunExposure.terrainX && y < TreetopSunExposure.terrainY) 
                    {
                        //Add to average
                        total += TreetopSunExposure.terrain[x][y]; 
                        //Incriment area totals
                        area ++;
  
                    } 
                    else{
                        //System.out.println("OUT OF BOUNDS X: " + x + "\t\tY: " + y);
                    }
                }  
            }
            //Calculate, set average
            TreetopSunExposure.total += total;
            TreetopSunExposure.setTotal(Math.round(workload[i][4]), total);   
        }
        
        
        
    }
    
    
    
    
    
}
