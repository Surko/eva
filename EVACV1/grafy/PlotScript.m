defaultDirectory='d:\cvicenia\eva\EVACV1\sga\';
fileNameCounted='sgaLog.fitness.';
fileNameFitness='sgaLog.fitness_stats';

populationSize=50;
colIndex1=1;
colIndex2=2;
vectorAdded=0:1:19;

[X,Y]=loadFiles(strcat(defaultDirectory,fileNameCounted),vectorAdded,true,' ',colIndex1,colIndex2,populationSize);
[X1,Y1]=loadFiles(strcat(defaultDirectory,fileNameFitness),1,false,' ',1,4,50);

hold;
plot(1:1:size(Y),Y(:,1),'r');
plot(1:1:size(Y),Y(:,10),'b');
plot(1:1:size(Y),Y(:,20),'g');
saveas(gcf,'GenFitness','jpg')
hold;

figure
plot(X1,Y1);
saveas(gcf,'AllGenFitness','jpg');