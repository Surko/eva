function C = plotmygraph( defaultDir, files, output, columns, sizes, graphTypes, leg, axs, labels )

figure
hold;
axis(axs);
if size(labels,2) == 2
   xlabel(labels{1});
   ylabel(labels{2});
end
for i=1:size(files,2)
   col = columns{i}; 
   [X,Y] = loadFiles(strcat(defaultDir,files{i}),1,false,' ',col(1),col(2),sizes(i));
   plot(X,Y,graphTypes{i});
end
set(legend(leg{1,:}));
saveas(gcf,output,'png');
hold
C='True';

end

