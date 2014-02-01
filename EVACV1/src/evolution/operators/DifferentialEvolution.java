/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;
import evolution.individuals.RealIndividual;
import evolution.real.functions.RealFunction;

/**
 *
 * @author kirrie
 */
public class DifferentialEvolution implements Operator{

    private double difWeight,crossProp;
    private int dimension;
    private boolean choice;
    RandomNumberGenerator rng = null;
    private RealFunction rf;
        
    public DifferentialEvolution(double difWeight,double crosProp,int dimension, RealFunction rf) {
        this.difWeight = difWeight;
        this.crossProp = crosProp;                
        this.dimension = dimension;
        this.rng = RandomNumberGenerator.getInstance();
        this.rf = rf;
        this.choice = true;
    }
    
    
    @Override    
    public void operate(Population parents, Population offspring) {
        
        int size = parents.getPopulationSize();
        double optimalValue = rf.getFopt();
        
        parents.shuffle();
                                           
        RealIndividual[] all = new RealIndividual[size];
        
        for (int k = 0; k < size; k++) {
            all[k] = (RealIndividual) parents.get(k);
        }
        
        RealIndividual sample = null;
        for (int j = 0; j < 3; j++) {            
            for (int k = 0; k < size; k++) {                               
                RealIndividual x = (RealIndividual) all[k];                
                //System.out.println("Check 1");
                //System.out.println("Done");
                int aindex;
                do {
                    aindex = rng.nextInt(size);                    
                } while (k == aindex);                            
                //System.out.println("Check 2");                
                int bindex;
                do {
                    bindex = rng.nextInt(size);
                } while (k == bindex || aindex == bindex);
                //System.out.println("Check 3");                
                int cindex;
                do {
                    cindex = rng.nextInt(size);
                } while (k == cindex || aindex == cindex || bindex == cindex );
                //System.out.println("Check 4");
                
                RealIndividual a = all[aindex];
                RealIndividual b = all[bindex];
                RealIndividual c = all[cindex];
                
                int R = rng.nextInt(dimension);
                sample = new RealIndividual(dimension, -5.0, 5.0);
                for (int i = 0; i < dimension; i++) {
                    if (rng.nextDouble() < crossProp || i == R) {
                        sample.set(i, ((Double)a.get(i) + difWeight * ((Double)b.get(i)-(Double)c.get(i))));
                    } else {
                        sample.set(i, x.get(i));
                    }
                }       
                
                double xeval = rf.evaluate(x.toDoubleArray());
                double sampleval = rf.evaluate(sample.toDoubleArray());
                
                if (xeval  < sampleval) {
                    all[k] = sample;                                        
                }  
                
                if (!choice) {
                    offspring.add(all[k]);
                }
            }            
        }
        
        if (choice) {
            for (Individual ind : all) {
                offspring.add(ind);
            }
        }
    }
    
}
