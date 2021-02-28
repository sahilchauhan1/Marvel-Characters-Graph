import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;

public class A4_2019EE10517 {

	HashMap<String, HashMap<String, Integer>> adj; //epresented as HashMap<String,
	// HashMap<String,node>>/ it can also be r
	String loc1;
	String loc2;

	public class node {
		String charac;
		int weight;

		public node(String charac, int weight) {
			this.charac = charac;
			this.weight = weight;
		}
	}

	public A4_2019EE10517(String location1, String location2) {
		this.loc1 = location1;
		this.loc2 = location2;
	}

	public void read() throws Exception {

		try {
			BufferedReader sc = new BufferedReader(new FileReader(loc1));
			sc.readLine();
			adj = new HashMap<String, HashMap<String, Integer>>();
			String next_line;
			while ((next_line = sc.readLine()) != null) // returns a boolean value
			{
				String[] a = next_line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				String b = a[1];
				b = b.replace("\"", ""); // removes front and trelling quotes
				adj.put(b, new HashMap<String, Integer>());

			}

			sc.close();

			BufferedReader sc2 = new BufferedReader(new FileReader(loc2));
			sc2.readLine();

			while ((next_line = sc2.readLine()) != null) // returns a boolean value
			{
				String[] a = next_line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				String b = a[0];
				String c = a[1];
				b = b.replace("\"", "");
				c = c.replace("\"", "");
				int d = Integer.parseInt(a[2]);
				if (adj.get(b).get(c) == null) {
					adj.get(b).put(c, d);
					adj.get(c).put(b, d);
				}

			}
			sc2.close();
		} catch (Exception e) {
			System.out.println("exception..tryagain");
		}

	}

	// average

	public void average() {
		float total_ch_count = 0;
		float total_vertex_count = 0;
		String final_ans;
		for (Map.Entry<String, HashMap<String, Integer>> y : adj.entrySet()) {
			total_vertex_count++;
			HashMap<String, Integer> adj_list = y.getValue();
			for (Map.Entry<String, Integer> x : adj_list.entrySet()) { // getValue will be HashMap<String,Integer>
				total_ch_count++;
			}
		}
		if (total_vertex_count == 0) {
			float average_value = 0;
			// ROUND OFF DUE
			final_ans = String.format("%.2f", average_value);
		} else {
			float average_value = (total_ch_count) / (total_vertex_count);
			final_ans = String.format("%.2f", average_value);
		}
		System.out.println(final_ans);
	}

	// rank

	public void rank() {

		ArrayList<Map.Entry<String, HashMap<String, Integer>>> sortedGraph = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();

		for(Map.Entry<String , HashMap<String, Integer>> i : adj.entrySet()) {
			sortedGraph.add(i);
		}

		sort(sortedGraph, 0 , sortedGraph.size() -1);

		//sortedGraph will be now sorted

		int w = 0;
		for(Map.Entry<String, HashMap<String, Integer>> ee : sortedGraph){
			w++;
			if(w == sortedGraph.size()){
				System.out.println(ee.getKey());
			}else{
				System.out.print(ee.getKey() + ",");
			}

		}

//		System.out.println("best of luck !!!");
	}

	// public ArrayList<Map.Entry<String, HashMap<String, Integer>>> mergeSortRank(ArrayList<Map.Entry<String, HashMap<String, Integer>>> arrayRank){

	// }

	public void sort(ArrayList<Map.Entry<String, HashMap<String, Integer>>> arr, int l, int r)
	{
		if (l < r) {
			//  mid point
			int m = (l + r) / 2;

			// Sort first both halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge halves
			merge(arr, l, m, r);
		}
	}

	public void merge(ArrayList<Map.Entry<String, HashMap<String, Integer>>> arr, int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		ArrayList<Map.Entry<String, HashMap<String, Integer>>> L = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();
		ArrayList<Map.Entry<String, HashMap<String, Integer>>> R = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();

		/*Copy data to temp arrays*/
		for (int i = 0; i < n1; ++i)
			L.add(arr.get(l+i));
		for (int j = 0; j < n2; ++j)
			R.add(arr.get(m+1+j));

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			Map.Entry<String, HashMap<String, Integer>> entry1 = L.get(i);
			Map.Entry<String, HashMap<String, Integer>> entry2 = R.get(j);
			int count1 = 0;
			int count2 = 0;
			int compare = 0;
			for(Map.Entry<String, Integer> ii : entry1.getValue().entrySet()){
				count1 += ii.getValue();
			}
			for(Map.Entry<String, Integer> iii : entry2.getValue().entrySet()){
				count2 += iii.getValue();
			}
			if(count1 == count2){
				compare = entry1.getKey().compareTo(entry2.getKey());
			}
			else{
				compare = count1 - count2;
			}

			if (compare >= 0) {
				arr.set(k, L.get(i));
//				arr[k] = L[i];
				i++;
			}
			else {
				arr.set(k, R.get(j));
//				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr.set(k,L.get(i));
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr.set(k,R.get(j));
			j++;
			k++;
		}
	}


	// independent_storylines_dfs, isdfs is a name of this function you can change
	// it

	public void independent_storylines_dfs() {
// 		System.out.println("Hello, i am inside independent_storylines_dfs. You will do it better then me");
	}

	public static void main(String[] args) throws Exception {
		String nodes_ = args[0];
		String edges_ = args[1];
		String function_ = args[2];
		A4_2019EE10517 ass4 = new A4_2019EE10517(nodes_, edges_);

		ass4.read();

		if (function_.equals("average")) {
			ass4.average();
		} else if (function_.equals("rank")) {
			ass4.rank();
		} else {
			ass4.independent_storylines_dfs();
		}
	}

}