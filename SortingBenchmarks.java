/*
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
 */

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class SortingBenchmarks {
    static int numMax, delta;
    static String methodName;
    static Random randGen;
    
    static Class[] signature;
    static Object[] arguments;
    
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	if (args.length == 0 || (args.length == 1 && args[0].compareTo("-h") == 0)) {
	    System.out.println("Usage: java SortingBenchmarks <numMax> <numSteps> <outFile>");
	    System.exit(0);
	}

	numMax = Integer.parseInt(args[0]);
	int numSteps = Integer.parseInt(args[1]);
	delta = (int)  numMax / numSteps;
	methodName = args[2];
	randGen = new Random();
       
	benchmark("rand");
	benchmark("worstcase");
    }

    private static void benchmark(String type) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	PrintWriter out = new PrintWriter(type + "." + methodName + ".benchmark");
	long execTime;
	for (int len = delta; len <= numMax; len += delta) {
	    int[] v = new int[len];
	    int e;
	    for (int i = 0; i < len; ++ i) {
		if (type.equals("rand")) 
		    e = randGen.nextInt();
		else
		    e = len - i;	      
		v[i] = e;
	    }
	    
	    Class[] signature = {int[].class};
	    Method method = Sort.class.getMethod(methodName, signature);

	    Object[] arguments = {v};
	    execTime = (Long) method.invoke(null, arguments);
	    System.out.printf("%s %s: %d %d\n", methodName, type, len, execTime);
	    out.printf("%d %d\n", len, execTime);
	}
	out.close();
    }
    
    private static void printArray(int[] v) {
	for (int i = 0; i < v.length; ++ i)
	    System.out.printf("%d ", v[i]);
	System.out.println();
    }
}