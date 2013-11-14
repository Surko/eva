defaultDirectory='d:\cvicenia\eva\EVACV1\pdf\';
fileNameCounted='sgaLog.fitness.';
orig='original.objective_stats';
base='base.objective_stats';
winner='winner.objective_stats';

leg = {'original','base'};
plotmygraph(defaultDirectory,{orig,base},'graph1',{[1 4],[1 4]},[50 50],{'r','b'},leg,[0 2600 200 6000],{'Function evaluations','Objective value'});
leg = {'winner'};
plotmygraph(defaultDirectory,{winner},'graph2',{[1 4]},[8000],{'r'},leg,[0 6500000 0 1000],{'Function evaluations','Objective value'});