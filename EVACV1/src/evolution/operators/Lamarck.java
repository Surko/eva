/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.RealIndividual;
import evolution.real.functions.RealFunction;

/**
 *
 * @author kirrie
 */
public class Lamarck implements Operator{

    double mutationProbability;
    double geneChangeProbability;
    RealFunction derivative;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
    private double sigma = 1;
    private double mean = 0;
    
    public Lamarck(double mutationProbability, double geneChangeProbability,RealFunction real) {
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
        this.derivative = real;
    }    
        
    @Override
    public void operate(Population parents, Population offspring) {
        int size = parents.getPopulationSize();

        for (int i = 0; i < size; i++) {

            RealIndividual p1 = (RealIndividual) parents.get(i);
            RealIndividual o1 = (RealIndividual) p1.clone();

            if (rng.nextDouble() < mutationProbability) {
                for (int j = 0; j < o1.length(); j++) {
                    if (rng.nextDouble() < geneChangeProbability) {                        
                        
                        for (int k = 0; k < 3; k++) {
                            double[] derivation = derivative.numericalDerivative(o1.toDoubleArray());
                            
                            for (int p = 0 ; p < o1.length(); p++) {
                                o1.set(p, (Double)o1.get(p)-(Double)derivation[p]);
                            }                           
                        }
                        
                    }
                }
            }
            offspring.add(o1);
        }
    }
    
}
