#! /usr/bin/env python

"""
Author: Dan-George Filimon

This file is part of SortingBenchmarks.

SortingBenchmarks is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
"""

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

"""
Modify these parameters for different benchmarks.
I was too lazy to implement input parsing.
"""
numMax = 1000000
numSteps = 500
deleteBenchmarks = False

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

    if deleteBenchmarks:
        # clean up nasty benchmark files
        for fileName in os.listdir('.'):
            pos = string.rfind(fileName, '.benchmark')
            if pos >= 0 and pos == len(fileName) - len('.benchmark'):
            os.remove(fileName)
    
if __name__ == "__main__":
    main()
