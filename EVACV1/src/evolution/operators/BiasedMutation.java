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
public class BiasedMutation implements Operator{
        double mutationProbability;
    double geneChangeProbability;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();
    private double sigma = 1;
    private double mean = 0;
    
    public BiasedMutation(double mutationProbability, double geneChangeProbability) {
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
    }
    
    public BiasedMutation(double mutationProbability, double geneChangeProbability, double mean,double sigma) {
        this(mutationProbability, geneChangeProbability);
        this.sigma = sigma; 
        this.mean = mean;
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
                        o1.set(j, (Double)o1.get(j) + sigma * (RandomNumberGenerator.getInstance().nextGaussian()-mean));
                    }
                }
            }
            offspring.add(o1);
        }
    }
}
