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
public class AdaptiveMutationUnBiased implements Operator {
    
    double mutationProbability;
    double geneChangeProbability;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
    private double sigma = 1;
    RealFunction rf;
    double[] lastThree = new double[maxindex];
    static int index;
    public static final int maxindex = 1;
    double modif = 1d;
    
    public AdaptiveMutationUnBiased(double mutationProbability, double geneChangeProbability, double sigma, RealFunction rf) {        
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
        this.rf = rf;
        index = 0;
        this.sigma = sigma;
    }
        
    @Override
    public void operate(Population parents, Population offspring) {
        int size = parents.getPopulationSize();
           
        if (index == maxindex) {
            index = 0;
        }
        
        double actualValue = 0d;
        
        modif = 1.0;
        
        for (Individual ri : parents.getIndividuals()) {
            actualValue+=rf.evaluate(((RealIndividual)ri).toDoubleArray());
        }
        
        if (lastThree[index]==0d) {
            lastThree[index] = actualValue;
        } else {
            for (int i = 0; i < maxindex; i++) {
                modif*=(actualValue/lastThree[i]);
            }            
            if (modif < 0.9d || modif > 1.0d) {
                modif = 1d;
            } else {
                modif = (modif - 0.9d)*75 + 1;
            }
            lastThree[index] = actualValue;
        }
                        
        for (int i = 0; i < size; i++) {

            RealIndividual p1 = (RealIndividual) parents.get(i);
            
            RealIndividual o1 = (RealIndividual) p1.clone();

            if (rng.nextDouble() < modif * mutationProbability) {
                for (int j = 0; j < o1.length(); j++) {
                    if (rng.nextDouble() < geneChangeProbability / modif) {
                        o1.set(j, sigma * RandomNumberGenerator.getInstance().nextGaussian());
                    }
                }
            }
            offspring.add(o1);
        }
        
        index++;
    }
    
}
