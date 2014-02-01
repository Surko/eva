/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.IntegerIndividual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author kirrie
 */
public class MyOperator implements Operator {
    Vector<Double> weights;
    double mutationProbability;
    double geneChangeProbability;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public MyOperator(Vector<Double> weights,double mutationProbability, double geneChangeProbability) {
        this.weights = weights;
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
    }

    class HromadkySort implements Comparable<HromadkySort> {

        int suma;
        int index;
        
        public HromadkySort(int k) {
            this.index = k;
        }
        
        @Override
        public int compareTo(HromadkySort o) {
            return suma < o.suma ? -1 : (suma == o.suma ? 0 : 1);
        }
        
    }
    
    @Override
    public void operate(Population parents, Population offspring) {

        int size = parents.getPopulationSize();

        for (int i = 0; i < size; i++) {

            IntegerIndividual p1 = (IntegerIndividual) parents.get(i);
            IntegerIndividual o1 = (IntegerIndividual) p1.clone();
                    
            HromadkySort[] sortHromadky = new HromadkySort[o1.getMax()];                   
            
            for (int k = 0 ; k < o1.getMax(); k++) {
                sortHromadky[k] = new HromadkySort(k);
            }            
            for (int j = 0; j < o1.length(); j++) {
                    sortHromadky[(Integer)o1.get(j)].suma+=weights.get(j);                    
            }
            Arrays.sort(sortHromadky);                                               
            
            //System.out.println(min + " " + minIndex + " " + max + " " + maxIndex);        
            
            if (rng.nextDouble() < 1) {
                for (int j = 0; j < o1.length(); j++) {
                    if (rng.nextDouble() < 0.025) {                                                
                        if ((Integer)o1.get(j)==sortHromadky[o1.getMax()-1].index) {                                                    
                            o1.set(j, sortHromadky[0].index);
                        }                        
                    }
                }
            }

            offspring.add(o1);
        }
    }
    
}
