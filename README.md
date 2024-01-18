# Project #2: Stardew Valley using a priority queue 

* Author: Chris Tudela
* Class: CS321 Section #001
* Semester: Fall 2023

## Overview

The program is a Priority Queue abstract data type that extends a MaxHeap
data structure to organize and manage Task objects. The program is used
to simulate a popular game called Stardew Valley.

## Reflection

So, something that worked well was really learning from the textbook
and online sources what heapsort did and what each method was 
supposed to do. After that, I had an easier time following the 
pseudo-code provided, as well as understanding what each line was 
doing. In a way this was the most challenging bit because it's always 
frightening starting something unfamiliar and sometimes that makes 
it hard to know where to start.

Something that gave me a lot of trouble was the TaskGenerator class.
I didn't realize the TaskInterface had passout and death probability
getters, so I kept trying to generate my own values. That turned out
terribly because my simulation was completely off. generateTask method
was another issue, sometimes my tasks wouldn't generate in the correct
order. Leaving me with a somewhat correct output. Eventually, I figured
out it was my increaseKey method in MaxHeap that was causing me some
issues. I will say that I didn't enjoy having issues with my code, but
I always enjoy feeling the satisfaction of it working. I did enjoy
writing some junit tests because those helped me find the bugs in my
code and writing them is a good break from writing the major classes.


## Compiling and Using

Before running the program run the following command: javac *.java

Program Usage: <br> 
java MyLifeInStardew \<max-priority> \<time-to-increment-priority> <br>
\<total simulation-time in days> \<task-generation-probability> [\<seed>] <br>

\<max-priority> : the maximum priority number of a any task. <br>
\<time-to-incement-priority> : amount of time a task has to wait in the queue before having its priority increased. <br>
\<total simulation-time in days> : amount of days the simulation will run for. <br>
\<task-generation-probability> : the likely-hood of a task being generated for a player. <br>
[\<seed>] : a (optional) seed value that generates certain results.


## Results 

The heapsort data structure maintains the max heap property for every
simulation run. The priority queue works as expected as it extends the
MaxHeap class. Overall, everything works as needed and all junit tests
cases are satisfied.

## Sources used
