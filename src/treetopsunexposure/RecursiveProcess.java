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
    private Float[][] workload;
    private static final int THRESHOLD = 10;
   
    //Constructor for method
    public RecursiveProcess(Float[][] workload){
        this.workload = workload;
    }
    
    @Override
    protected void compute() {
        // Check threshold
        if (workload.length > THRESHOLD) 
        {
            //Split
            ForkJoinTask.invokeAll(splitTasks());
        }
        else{
            //TODO Process
        }                 
    }
    
    //Create a list of subtasks for computation
    private List<RecursiveProcess> splitTasks(){
        
        List<RecursiveProcess> subtasks = new ArrayList<>();
        
        //Split array at half-way point
        int workloadLength = workload.length;
        int splitPoint = (int)Math.floor(workloadLength/2);
        int elements = workload[0].length;
        //Copy elements
        Float[][] arrayOne = new Float[splitPoint][elements];
        
        for (int i = 0; i < splitPoint; i++) 
        {
            arrayOne[i] = workload[i];
        }
        
        Float[][] arrayTwo = new Float[workloadLength-splitPoint][elements];
        for (int i = splitPoint; i < workloadLength; i++) 
        {
            arrayTwo[i] = workload[i];
        }
        
        //Add net subtasks to list
        subtasks.add(new RecursiveProcess(arrayOne));
        subtasks.add(new RecursiveProcess(arrayTwo));
        
        return subtasks;
    }
    
    
    
    
    
}
