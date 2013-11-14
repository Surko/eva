package evolution.sga;

import evolution.FitnessFunction;
import evolution.individuals.BooleanIndividual;
import evolution.individuals.Individual;

/**
 * @author Martin Pilat
 */
public class ExampleFitnessFunction implements FitnessFunction {

    @Override
    public double evaluate(Individual ind) {

        BooleanIndividual bi = (BooleanIndividual) ind;
        boolean[] genes = bi.toBooleanArray();

        double fitness = 0.0;

        for (int i = 0; i < genes.length-1; i+=2) {
            if (genes[i]== true && genes[i+1]== false) {
                fitness += 1.0;
            }
        }

        ind.setObjectiveValue(fitness); //nastavuje hodnotu optimalizovaneho kriteria, nemusi se (obecne) rovnat primo fitness

        return fitness;
    }

}
