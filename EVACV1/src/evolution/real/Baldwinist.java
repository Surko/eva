/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.real;

import evolution.FitnessFunction;
import evolution.individuals.Individual;
import evolution.individuals.RealIndividual;
import evolution.real.functions.RealFunction;

/**
 *
 * @author kirrie
 */
public class Baldwinist implements FitnessFunction{
    private static final long serialVersionUID = 2274261765244005248L;
    RealFunction rf;

    public Baldwinist(RealFunction rf) {
        this.rf = rf;
    }

    @Override
    public double evaluate(Individual aSubject) {

        RealIndividual ri = (RealIndividual) aSubject;        
                
        double[] values = ri.toDoubleArray().clone();                
        
        double value = rf.evaluate(values);
        ri.setObjectiveValue(-rf.getFopt() - value);
        
        for (int k = 0; k < 3; k++) {
            double[] derivation = rf.numericalDerivative(values);

            for (int p = 0 ; p < ri.length(); p++) {
                values[p]-=derivation[p];
            }                           
        }
                                
        value = rf.evaluate(values);
        ri.setObjectiveValue(-rf.getFopt() - value);
                
        return value;
    }
    
}
