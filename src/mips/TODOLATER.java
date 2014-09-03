/*
*
1)  SIGNED AND UNSIGNED
    FOR NOW I AM WRITING ALL THE UNSIGNED INSTRUCTIONS EXACTLY AS THEIR SIGNED COUNTERPARTS.
    THE STALLS AND STUFF WOULD REMAIN CONSISTENT BUT SOME TYPECASTING MAY BE NEEDED LATER TO
    GET THE CORRECT NUMERIC RESULT

2) Print line number if a label is not found. 
   Problem - How to get line number of a particular token in antlr?

3) Pseudo instructions - How to go ahead with them? 

4) Commas in the code? I have made the commas optional, what should be the correct way? 
   In my implementation, "add $t1 , $t3 $t3" will be a valid instruction.

5) Instructions that will not be added to instruction list for now: 
    exit, sllb, srab, srlb
*/