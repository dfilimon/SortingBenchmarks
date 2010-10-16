#! /usr/bin/env python

import os
import string
import subprocess

# add more algorithms here but make sure to try them out
# by running isSorted after having sorted an array
algorithms = ['mergesort',
              'quicksort',
              'heapsort',
              'introsort']
"""
              ,'insertionsort',
              'bubblesort']
"""
numMax = 100
numSteps = 10

def main():
    # compile java code
    java = subprocess.Popen(['javac', 'SortingBenchmarks.java'])
    java.wait()
    
    # run java SortingBenchmarks with given params
    for algName in algorithms:
        java = subprocess.Popen(['java', 'SortingBenchmarks', str(numMax), str(numSteps), algName])
        java.wait()

    # draw nice plots
    octave = subprocess.Popen(['./draw_plots.m'])
    octave.wait()

    # clean up nasty benchmark files
    for fileName in os.listdir('.'):
        pos = string.rfind(fileName, '.benchmark')
        if pos >= 0 and pos == len(fileName) - len('.benchmark'):
            os.remove(fileName)
            
if __name__ == "__main__":
    main()
