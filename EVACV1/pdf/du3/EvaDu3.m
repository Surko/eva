defaultDirectory='d:\cvicenia\eva\EVACV1\pdf\du3\';
originFitness='original.objective_stats';
incgenFitness='inteliOp.objective_stats';

leg = {'original','intelligent operator'};
plotmygraph(defaultDirectory,{originFitness,incgenFitness},'graph1',{[1 4],[1 4]},[50 50],{'r','b'},leg,[0 2500 13 5000],{'Function evaluations','Objective value'});

