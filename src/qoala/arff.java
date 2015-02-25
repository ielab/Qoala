/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qoala;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.clusterers.XMeans;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;
import weka.core.DenseInstance;
import weka.core.BinarySparseInstance;
import weka.core.SparseInstance;
 
/**
 *
 * @author siavash
 */

//This Class comprises of methods to create instances  and arff file and clustering methods - SimpleKmeans,X-Means,EM// 
public class arff {
    
            FastVector attributes;
	    Instances dataSet;
	    int[] values;
	    double [][] vals;
	    int k=0;
            setData cl= new setData();
           // NewJFrame Frame = new NewJFrame();
            
    //Generates a datasets with diffrent attributes//
    public void valueSession(setData f){
            vals= new double[cl.Datapoints.size()/5][6];
            //set up Attributes//
            attributes = new FastVector();
            
            attributes.addElement(new Attribute("att1 SessionID"));
            attributes.addElement(new Attribute("att2 NumberofClicks"));
            attributes.addElement(new Attribute("att3 NumberofQueriesInSession"));
            attributes.addElement(new Attribute("att4 NumberofQueryTermsInSession"));
            attributes.addElement(new Attribute("att5 NumberofAverageQueryTermsInSession"));
            attributes.addElement(new Attribute("att6 SessionDuration"));
            //Creates instances object//
            dataSet = new Instances("My weka", attributes, 0);
            
      
            // fills the instances with data//
            for(int k=0;k<cl.Datapoints.get(cl.Datapoints.size()/5).getSession();k++){
      
               vals[k][0]=(double)f.GetSessionRetrieval(k);
          
                vals[k][1]=(double)f.GetClick(k);
            
                vals[k][2]=(double)f.GetSessionNumber(k);
             
                vals[k][3]=(double)f.GetTerms(k);
                  
                vals[k][4]=(double)f.GetAverageTerms(k);
              
                vals[k][5]=(double)f.sessionDurationRetrieval(k);
         
                 Instance inst = new DenseInstance(6);
                 inst.setValue(0, vals[k][0]);
                 inst.setValue(1, vals[k][1]);
                 inst.setValue(2, vals[k][2]);
                 inst.setValue(3, vals[k][3]);
                 inst.setValue(4, vals[k][4]);
                 inst.setValue(5, vals[k][5]);
                 
                 dataSet.add(inst);
            	
   
            }
            
    
  
    }   
        
    //Generates the Arff File with diffrent attributes//    
    public void ArffCreator(){
      
      
       
       System.out.println(dataSet);
       
           
            ArffSaver arffSaverInstance = new ArffSaver(); 
            arffSaverInstance.setInstances(dataSet); 
        try { 
            arffSaverInstance.setFile(new File("MyProject.arff"));
            arffSaverInstance.writeBatch();
        } catch (IOException ex) {
            Logger.getLogger(arff.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            System.out.println("Done");
        
 
      }
    
    //Performs SimpleKmeans by using WEKA library with user-specific number of Clusters//
    public void SimpleKmeans(int numberOfCLuster) throws Exception{
        
        Instances train = new Instances (dataSet);
        
        SimpleKMeans skm = new SimpleKMeans();
        skm.setPreserveInstancesOrder(true);
        skm.setNumClusters(numberOfCLuster);
        skm.buildClusterer(train);
        skm.setSeed(10);
	int[] ClusterSize = skm.getClusterSizes();
        
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(skm);
        eval.evaluateClusterer(train);
        
        System.out.println("Cluster Evaluation:"+eval.clusterResultsToString());
        
        int[] assignments = skm.getAssignments();

		
        System.out.println("# - cluster - distribution");
		
        for(int j=0;j<skm.getNumClusters();j++)
		{
			int i=0;
		for(int clusterNum : assignments) {
			
                    if(clusterNum==j)
                    
                        System.out.println("Instance "+ i +" -> Cluster number: "+ clusterNum);
		
		    i++;
		 }
		}		
	}
        
    //Performs EMClustering by using WEKA library with user-specific number of Clusters// 
    public void EMClustering(int NumberOfCluster) throws Exception{
        
        Instances train = new Instances (dataSet);
        String[] options = new String[2];
        options[0] = "-I";
        options[1] = "100";
      
        EM em = new EM();
        em.setOptions(options);
        em.setNumClusters(NumberOfCluster);
        em.buildClusterer(train);
        
        ClusterEvaluation eval = new ClusterEvaluation();
	    eval.setClusterer(em);
	    eval.evaluateClusterer(train);
	    eval.getNumClusters();
	    System.out.println("Cluster Evaluation:"+eval.clusterResultsToString());
	    
	    
	    System.out.println("# - cluster - distribution");
	    for(int j=0;j<eval.getNumClusters();j++)
		{
	    
	    
                    for (int i = 0; i < train.numInstances(); i++) {
                    
                        int cluster = em.clusterInstance(train.instance(i));
                        if(cluster==j)
				System.out.println("Instance "+ i +" -> Cluster number: "+ cluster);
        
        
                     }
                 }
            }
    //Performs XMeans by using WEKA library with user-specific number of Clusters//
    public void XMenas() throws Exception{
        
        Instances train = new Instances (dataSet);
        XMeans xm = new XMeans();
   
        xm.setMaxNumClusters(100); 
        xm.setMinNumClusters(2);
        xm.buildClusterer(train);
	
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(xm);
        eval.evaluateClusterer(train);
        eval.getNumClusters();
        System.out.println("Cluster Evaluation:"+eval.clusterResultsToString());
        System.out.println("# - cluster - distribution");
	    for(int j=0;j<eval.getNumClusters();j++)
		{
	    
	    
                    for (int i = 0; i < train.numInstances(); i++) {
                        int cluster = xm.clusterInstance(train.instance(i));
                            if(cluster==j)
                            
                                System.out.println("Instance "+ i +" -> Cluster number: "+ cluster);
                        }
                }
	
	               
          
            }
        
        
        
        
        }
        
        
        

       
        
        
        
        

        

