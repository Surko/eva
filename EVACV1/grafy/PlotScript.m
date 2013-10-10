defaultDirectory='d:\cvicenia\eva\EVACV1\sga\'
fileName='sgaLog.fitness.'

populationSize=50;
colIndex1=1;
colIndex2=2;
vectorAdded=0:1:19;

[X,Y]=loadFiles(strcat(defaultDirectory,fileName),vectorAdded,' ',colIndex1,colIndex2,populationSize);

hold;
plot(1:1:size(Y),Y(:,1),'r');
plot(1:1:size(Y),Y(:,10),'b');
plot(1:1:size(Y),Y(:,20),'g');
