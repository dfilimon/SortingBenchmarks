import java.util.*;

class Sort {
    // === Utilities ===
    private static Random randGen = new Random();
    private static void merge(int[] v, int l, int r) {
	int m = l + (r - l) / 2;
	int[] w = new int[r - l + 1];
	int i = l, j = m + 1, k = 0;
	while (i <= m || j <= r) {
	    if (i <= m && j <= r) {
		if (v[i] < v[j])
		    w[k] = v[i ++];
		else
		    w[k] = v[j ++];
	    } else if (i <= m)
		w[k] = v[i ++];
	    else
		w[k] = v[j ++];
	    ++ k;
	}
	System.arraycopy(w, 0, v, l, w.length);
    }
    
    private static int partition(int[] v, int l, int r) {
	int pivotIndex = l + randGen.nextInt(r - l + 1);
	int pivot = v[pivotIndex], leftBoundary = l;
	swap(v, pivotIndex, r);
	for (int i = l; i < r; ++ i)
	    if (v[i] < pivot)
		swap(v, leftBoundary ++, i);
	swap(v, leftBoundary, r);
	return leftBoundary;
    }
    
    private static void swap(int[] v, int i, int j) {
	int aux = v[i];
	v[i] = v[j];
	v[j] = aux;
    }

    // using this to check the correctness of the sorting methods
    public static boolean isSorted(int[] v) {
	for (int i = 1; i < v.length; ++ i)
	    if (v[i - 1] > v[i])
		return false;
	return true;
    }

    // === Sorting Methods ===
	
    public static void mergesort(int[] v, int l, int r) {
	if (l >= r)
	    return;
	int m = l + (r - l) / 2;
	mergesort(v, l, m);
	mergesort(v, m + 1, r);
	merge(v, l, r);
    }
    public static long mergesort(int[] v) {
	long start = System.nanoTime();
	mergesort(v, 0, v.length - 1);
	long end = System.nanoTime();
	System.out.println(isSorted(v));
	return end - start;
    }
        
    public static void quicksort(int[] v, int l, int r) {
	if (l >= r)
	    return;
	
	int leftBoundary = partition(v, l, r);
	quicksort(v, l, leftBoundary - 1);
	quicksort(v, leftBoundary + 1, r);
    }
	
    public static long quicksort(int[] v) {
	long start = System.nanoTime();
	quicksort(v, 0, v.length - 1);
	long end = System.nanoTime();
	System.out.println(isSorted(v));
	return end - start;
    }

    public static void heapsort(int[] v, int l, int r) {
	int i;
	PriorityQueue<Integer> h = new PriorityQueue<Integer>(r - l + 1);
	for (i = l; i <= r; ++ i)
	    h.add(v[i]);
	for (i = l; i <= r; ++ i)
	    v[i] = h.poll();
    }

    public static long heapsort(int[] v) {
	long start = System.nanoTime();
	heapsort(v, 0, v.length - 1);
	long end = System.nanoTime();
	return end - start;
    }

    // introsort = quicksort + heapsort
    public static void introsort(int[] v, int l, int r, int maxDepth) {
	if (l >= r)
	    return;
	if (maxDepth == 0) {
	    heapsort(v, l, r);
	    return;
	}
	int leftBoundary = partition(v, l, r);
	introsort(v, l, leftBoundary - 1, maxDepth - 1);
	introsort(v, leftBoundary + 1, r, maxDepth - 1);
    }
    
    public static long introsort(int[] v) {
	long start = System.nanoTime();
	int maxDepth = (int) (Math.log(v.length) / Math.log(2));
	introsort(v, 0, v.length - 1, maxDepth);
	long end = System.nanoTime();
	System.out.println(isSorted(v));
	return end - start;
    }

    public static long insertionsort(int[] v) {
	long start = System.nanoTime();
	int i, j, e,
	    n = v.length;
	for (i = 1; i < n; ++ i) {
	    e = v[i];
	    for (j = i - 1; j >= 0 && v[j] > e; -- j)
		v[j + 1] = v[j];
	    v[j + 1] = e;
	}
	long end = System.nanoTime();
	System.out.println(isSorted(v));
	return end - start;
    }

    public static long bubblesort(int[] v) {
	long start = System.nanoTime();
	int i, n = v.length;
	boolean swapsRemaining = true;
	while (swapsRemaining) {
	    swapsRemaining = false;
	    for (i = 0; i < n - 1; ++ i)
		if (v[i + 1] < v[i]) {
		    swap(v, i, i + 1);
		    swapsRemaining = true;
		}
	}
	long end = System.nanoTime();
	System.out.println(isSorted(v));
	return end - start;
    }
}