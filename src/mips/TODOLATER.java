/*
*

Note: Add code related todos inside the code itself (especially bugs). 
Would be better to use some formatting to find/assign pending todos.
    TODO(ved): Whatever task you want to assign to Ved.
    Similarly for 'astha', 'rahul', 'anmol'.

1)  SIGNED AND UNSIGNED
    FOR NOW I AM WRITING ALL THE UNSIGNED INSTRUCTIONS EXACTLY AS THEIR SIGNED COUNTERPARTS.
    THE STALLS AND STUFF WOULD REMAIN CONSISTENT BUT SOME TYPECASTING MAY BE NEEDED LATER TO
    GET THE CORRECT NUMERIC RESULT

2) Pseudo instructions - How to go ahead with them? 

3) Commas in the code? I have made the commas optional, what should be the correct way? 
   In my implementation, "add $t1 , $t3 $t3" will be a valid instruction.
   (This is fine and sounds like a plus point. Let it be.)

4) Instructions that will not be added to instruction list for now: 
    exit, sllb, srab, srlb

5) Flexible width of the left panel that contains instructions.
   Presently, if you give it an instruction with big display text, the whole text will not be visible.
   Decide on the policy: 
    a. Make a window that the user can resize - 
        disadvantage: user has to make changes every time, not comfortable if someone 
        has to deal with big instructions for quite some time
    b. Find the biggest display string in the program and fix the width accordingly
        disadvantage: Useless if that instruction never executes, though this wont 
        be the case most of the time.

6) Do we plan on creating an input/output interface? What about syscalls that 
    Mars offers to find input/output?

7) Add an option to see register/memory state after every cycle.

8) Make key input sensible: 
    a. left/right arrow keys should actually make the pane go left/right. Same for up/down.
    b. Decide on keyboard shortcuts: 
        n - next cycle
        a - all cycles
        r - show/hide register state bar
        m - show hide memory state bar
        s - show/hide statistics bar
    c. Sidenote - can we make the scrollbar work correctly from touchpad input as well? 
    Laptop touchpads offer sideways scrolling, can we make the window scroll sideways on such input?

9) Decide on an interface to show errors (parser errors, for example) 
   if the user submits a syntactically incorrect program.

10) Stop incrementing clock ticks if the program has ended.
    (Not sure if this is already present, wasn't working for me.)

11) Resizing issue TODO(rahul): 
    Set UIChecking to false, and program file name should be test_files/all_instructions. 
    Run a few instructions and resize. The pipeline is screwed
*/