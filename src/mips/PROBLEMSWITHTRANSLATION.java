/*#################################################################################

PROBLEMS ENCOUNTERED DURING TRANSITION

1) MEMORY.JAVA
There is no way of initializing any datastructure like this in Java

store.assign(10485760,(char)0);
freePointer = 0;

for now i am reducing the size to 10000 like this
store = new ArrayList<>(10000);
for(int i = 0; i < 10000; i++){
    store.add((char)0);
}


2)

###################################################################################*/