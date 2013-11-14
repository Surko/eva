defaultDirectory='d:\cvicenia\eva\EVACV1\fitnesses\';
fileNameCounted='sgaLog.fitness.';
originFitness='original.objective_stats';
incgenFitness='incgen.objective_stats';
incpopFitness='incpop.objective_stats';
incpopgenFitness='incpopgen.objective_stats';
originincsizeFitness='origincpop.objective_stats';
incpopgensizeFitness='incpopgensize.objective_stats';

leg = {'original','incgen','incpop','incpopgen','Location','NorthEastOutside'};
plotmygraph(defaultDirectory,{originFitness,incgenFitness,incpopFitness,incpopgenFitness},'graph1',{[1 4],[1 4],[1 4],[1 4]},[50 100 50 100],{'r+','b','g+','y'},leg,[0 8000 6 13],{'Function evaluations','Objective value'});
leg = {'originalincsize','incpopgensize','Location','NorthEastOutside'};
plotmygraph(defaultDirectory,{originincsizeFitness,incpopgensizeFitness},'graph2',{[1 4],[1 4]},[50 100],{'r','b'},leg,[0 8000 10 25],{'Function evaluations','Objective value'});
