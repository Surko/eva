package evolution.binPacking;

import evolution.DetailsLogger;
import evolution.EvolutionaryAlgorithm;
import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.StatsLogger;
import evolution.individuals.Individual;
import evolution.individuals.IntegerIndividual;
import evolution.operators.IntegerMutation;
import evolution.operators.OnePtXOver;
import evolution.selectors.RouletteWheelSelector;
import evolution.selectors.TournamentSelector;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

public class Hromadky {

    /**
     * @param args
     */
    static int maxGen;
    static int popSize;
    static String logFilePrefix;
    static int repeats;
    static int K;
    static Vector<Double> weights;
    static String bestPrefix;
    private static double xoverProb;
    private static double mutProb;
    private static double mutProbPerBit;
    private static String outputDirectory;
    private static String objectiveFilePrefix;
    private static String objectiveStatsFile;
    private static String fitnessFilePrefix;
    private static String fitnessStatsFile;
    private static String detailsLogPrefix;
    private static String enableDetailsLog;
    private static Properties prop;

    public static void main(String[] args) {

        prop = new Properties();
        try {
            InputStream propIn = new FileInputStream("properties/ga-binPacking.properties");
            prop.load(propIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        maxGen = Integer.parseInt(prop.getProperty("ea.maxGenerations", "20"));
        popSize = Integer.parseInt(prop.getProperty("ea.popSize", "30"));
        xoverProb = Double.parseDouble(prop.getProperty("ea.xoverProb", "0.8"));
        mutProb = Double.parseDouble(prop.getProperty("ea.mutProb", "0.05"));
        mutProbPerBit = Double.parseDouble(prop.getProperty("ea.mutProbPerBit", "0.04"));

        String inputFile = prop.getProperty("prob.inputFile", "resources/packingInput-easier.txt");
        K = Integer.parseInt(prop.getProperty("prob.numBins", "10"));

        repeats = Integer.parseInt(prop.getProperty("xset.repeats", "10"));


        enableDetailsLog = prop.getProperty("xlog.detailsLog", "enabled");

        if (!enableDetailsLog.equals("enabled")) {
            DetailsLogger.disableLog();
        }

        outputDirectory = prop.getProperty("xlog.outputDirectory", "binPacking");
        logFilePrefix = prop.getProperty("xlog.filePrefix", "log");
        String path = outputDirectory + System.getProperty("file.separator") + logFilePrefix;
        objectiveFilePrefix = path + ".objective";
        objectiveStatsFile = path + ".objective_stats";
        bestPrefix = path + ".best";
        fitnessFilePrefix = path + ".fitness";
        fitnessStatsFile = path + ".fitness_stats";
        detailsLogPrefix = path + ".details";

        File output = new File(outputDirectory);
        output.mkdirs();

        weights = new Vector<Double>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = in.readLine()) != null) {
                weights.add(Double.parseDouble(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        Random rand = new Random();        
        int randomNumber = rand.nextInt();
        for (int i = 0; i < repeats; i++) {
            //System.out.println(" Sequence " + randomNumber % 65536);
            run(i+8,i);
            randomNumber = rand.nextInt();
        }
        
        System.out.println("Done");
        StatsLogger.processResults(fitnessFilePrefix, fitnessStatsFile, repeats, maxGen, popSize);
        StatsLogger.processResults(objectiveFilePrefix, objectiveStatsFile, repeats, maxGen, popSize);

    }

    static void run(int number,int iter) {

        try {

            DetailsLogger.startNewLog(detailsLogPrefix + "." + iter + ".xml");
            DetailsLogger.logParams(prop);
            RandomNumberGenerator.getInstance().reseed(number);
            Individual sampleIndividual = new IntegerIndividual(weights.size(), 0, K);
                        
            
            Population pop = new Population();
            pop.setPopulationSize(popSize);
            pop.setSampleIndividual(sampleIndividual);
            pop.createRandomInitialPopulation();

            EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm();
            HromadkyFitness fitness = new HromadkyFitness(weights, K);
            ea.setFitnessFunction(fitness);
            ea.addMatingSelector(new TournamentSelector());
            ea.setElite(0.15);
            ea.addOperator(new OnePtXOver(xoverProb));
            ea.addOperator(new IntegerMutation(mutProb, mutProbPerBit));
            ea.addEnvironmentalSelector(new TournamentSelector());

            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fitnessFilePrefix + "." + iter));
            OutputStreamWriter progOut = new OutputStreamWriter(new FileOutputStream(objectiveFilePrefix + "." + iter));

            ArrayList<Individual> bestIndArray = null;
            
            for (int i = 0; i < maxGen; i++) {
                ea.evolve(pop);
                /*
                IntegerIndividual bestInd = (IntegerIndividual) pop.getSortedIndividuals().get(0);
                double diff = bestInd.getObjectiveValue();
                System.out.println("Generation " + i + ": " + diff + " " + Arrays.toString(fitness.getBinWeights(bestInd)));                
                
                StatsLogger.logFitness(pop, out);
                StatsLogger.logObjective(pop, progOut);
                */
                bestIndArray = StatsLogger.logFitnessAndObjective(pop,out,progOut);

            }

            OutputStreamWriter bestOut = new OutputStreamWriter(new FileOutputStream(bestPrefix + "." + iter));

            //IntegerIndividual bestInd = (IntegerIndividual) pop.getSortedIndividuals().get(0);            
            for (int i = 0; i < ((IntegerIndividual)bestIndArray.get(0)).length(); i++) {
                bestOut.write(weights.get(i) + " " + ((IntegerIndividual)bestIndArray.get(0)).get(i) + System.getProperty("line.separator"));
            }                       
            out.close();
            progOut.close();
            bestOut.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        DetailsLogger.writeLog();
    }
}
