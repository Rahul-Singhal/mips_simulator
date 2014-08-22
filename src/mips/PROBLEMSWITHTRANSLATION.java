/*#################################################################################

PROBLEMS ENCOUNTERED DURING TRANSITION

1) MEMORY.JAVA ***SOLVED***
There is no way of initializing any datastructure like this in Java

store.assign(10485760,(char)0);
freePointer = 0;

for now i am reducing the size to 10000 like this
store = new ArrayList<>(10000);
for(int i = 0; i < 10000; i++){
    store.add((char)0);
}


2)CLONE FUNCTIONS ***SOLVED***
COULD NOT REPLICATE THE CLONE FUNCTION IN THE INHERITED INSTRUCTION CLASSES

3) IS THE COPY CONSTRUCTOR IN INSTRUCTION CLASS AND ITS CHILD CLASSES NEEDED 
WHEN WE HAVE CLONE METHOD? NOT REMOVING IT NOW, WE NEED TO CHECK IT AND REMOVE
THE COPY CONSTUCTOR FROM EVERYWHERE.

###################################################################################*/