function [ X, Y ] = loadFiles( file, fileCount, isAdded, delimiter, colindex1, colindex2, maxSize)
    X=zeros(maxSize,size(fileCount));
    Y=zeros(maxSize,size(fileCount));
    if isAdded 
        for I = fileCount
            data=importdata(strcat(file,int2str(I)),delimiter);        
            X(:,I+1)=data(:,colindex1);
            Y(:,I+1)=data(:,colindex2);
        end
    else
        for I = fileCount
            data=importdata(file,delimiter);        
            X(:,I)=data(:,colindex1);
            Y(:,I)=data(:,colindex2);
        end        
    end
end

