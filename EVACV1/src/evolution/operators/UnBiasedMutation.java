/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.RealIndividual;

/**
 *
 * @author kirrie
 */
public class UnBiasedMutation implements Operator{

    double mutationProbability;
    double geneChangeProbability;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
    private double sigma = 1;
    
    public UnBiasedMutation(double mutationProbability, double geneChangeProbability) {
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
    }
    
    public UnBiasedMutation(double mutationProbability, double geneChangeProbability, double sigma) {
        this(mutationProbability, geneChangeProbability);
        this.sigma = sigma; 
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
                        o1.set(j, sigma * RandomNumberGenerator.getInstance().nextGaussian());
                    }
                }
            }
            offspring.add(o1);
        }
    }
    
}
