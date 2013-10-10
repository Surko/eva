function [ X, Y ] = loadFiles( file, vectorCount, delimiter, colindex1, colindex2,maxSize)
    X=zeros(maxSize,size(vectorCount));
    Y=zeros(maxSize,size(vectorCount));
    for I = vectorCount
        data=importdata(strcat(file,int2str(I)),delimiter);        
        X(:,I+1)=data(:,colindex1);
        Y(:,I+1)=data(:,colindex2);
    end
end

