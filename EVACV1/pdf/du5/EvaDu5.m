defaultDirectory='d:\cvicenia\eva\EVACV1\pdf\du5\';
sphere=strcat(defaultDirectory,'sphere\');
elipsoid=strcat(defaultDirectory,'elipsoid\');
rastrigin=strcat(defaultDirectory,'rastrigin\');
bentcigar=strcat(defaultDirectory,'bentcigar\');
schewel=strcat(defaultDirectory,'schewel\');

origin='Original';
polynomial='Polynomial';
biased='Biased';
unbiased='UnBiased';
adaptBiased='AdaptiveBiased';
adaptUnBiased='AdaptiveUnBiased';

leg = {'Original','Polynomial','Biased','UnBiased','Adapt Biased','Adapt UnBiased'};
plotmygraph(sphere,{origin,polynomial,biased,unbiased,adaptBiased,adaptUnBiased},'sphere',{[1,4],[1 4],[1 4],[1,4],[1,4],[1,4]},[5000 5000 5000 5000 5000 5000],{'r','k','b','g','y','c'},leg,[10000 500000 0 0.3],{'Function evaluations','Objective value'});
plotmygraph(elipsoid,{origin,polynomial,biased,unbiased,adaptBiased,adaptUnBiased},'elipsoid',{[1,4],[1 4],[1 4],[1,4],[1,4],[1,4]},[5000 5000 5000 5000 5000 5000],{'r','k','b','g','y','c'},leg,[10000 500000 0 3],{'Function evaluations','Objective value'});
plotmygraph(rastrigin,{origin,polynomial,biased,unbiased,adaptBiased,adaptUnBiased},'rastrigin',{[1,4],[1 4],[1 4],[1,4],[1,4],[1,4]},[5000 5000 5000 5000 5000 5000],{'r','k','b','g','y','c'},leg,[10000 500000 0 3],{'Function evaluations','Objective value'});
plotmygraph(bentcigar,{origin,polynomial,biased,unbiased,adaptBiased,adaptUnBiased},'bentcigar',{[1,4],[1 4],[1 4],[1,4],[1,4],[1,4]},[5000 5000 5000 5000 5000 5000],{'r','k','b','g','y','c'},leg,[10000 500000 0 3],{'Function evaluations','Objective value'});
plotmygraph(schewel,{origin,polynomial,biased,unbiased,adaptBiased,adaptUnBiased},'schewel',{[1,4],[1 4],[1 4],[1,4],[1,4],[1,4]},[5000 5000 5000 5000 5000 5000],{'r','k','b','g','y','c'},leg,[10000 500000 0 3],{'Function evaluations','Objective value'});
