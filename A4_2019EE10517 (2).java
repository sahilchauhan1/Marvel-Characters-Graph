import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class A4_2019EE10517 {

    public static HashMap<String, HashMap<String, Integer>> adj = new HashMap<String, HashMap<String, Integer>>();; // epresented
                                                                                                                    // as
                                                                                                                    // HashMap<String,
    // HashMap<String,node>>/ it can also be r
    private HashMap<String, Boolean> nodesVisited = new HashMap<String, Boolean>();
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
            String next_line;
            while ((next_line = sc.readLine()) != null) // returns a boolean value
            {
                String[] a = next_line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String b = a[1];
                b = b.replace("\"", ""); // removes front and trelling quotes
                adj.put(b, new HashMap<String, Integer>());
                nodesVisited.put(b, false);
            }

            sc.close();

            BufferedReader sc2 = new BufferedReader(new FileReader(loc2));
            sc2.readLine();

            while ((next_line = sc2.readLine()) != null) {
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

    public void average() {
        float total_ch_cont = 0;
        float total_vertex_cont = 0;
        String final_ans;
        for (Map.Entry<String, HashMap<String, Integer>> y : adj.entrySet()) {
            total_vertex_cont++;
            HashMap<String, Integer> adj_list = y.getValue();
            for (Map.Entry<String, Integer> x : adj_list.entrySet()) {
                total_ch_cont++;
            }
        }
        if (total_vertex_cont == 0) {
            float average_value = 0;
            final_ans = String.format("%.2f", average_value);
        } else {
            float average_value = (total_ch_cont) / (total_vertex_cont);
            final_ans = String.format("%.2f", average_value);
        }
        System.out.println(final_ans);
    }

    public void rank() {

        ArrayList<Map.Entry<String, HashMap<String, Integer>>> sotedgaph = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();

        for (Map.Entry<String, HashMap<String, Integer>> i : adj.entrySet()) {
            sotedgaph.add(i);
        }

        sort(sotedgaph, 0, sotedgaph.size() - 1);

        int w = 0;
        for (Map.Entry<String, HashMap<String, Integer>> ee : sotedgaph) {
            w++;
            if (w == sotedgaph.size()) {
                System.out.println(ee.getKey());
            } else {
                System.out.print(ee.getKey() + ",");
            }

        }
    }

    public void sort(ArrayList<Map.Entry<String, HashMap<String, Integer>>> arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(ArrayList<Map.Entry<String, HashMap<String, Integer>>> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList<Map.Entry<String, HashMap<String, Integer>>> L = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();
        ArrayList<Map.Entry<String, HashMap<String, Integer>>> R = new ArrayList<Map.Entry<String, HashMap<String, Integer>>>();
        for (int i = 0; i < n1; ++i)
            L.add(arr.get(l + i));
        for (int j = 0; j < n2; ++j)
            R.add(arr.get(m + 1 + j));
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            Map.Entry<String, HashMap<String, Integer>> entry1 = L.get(i);
            Map.Entry<String, HashMap<String, Integer>> entry2 = R.get(j);
            int cont1 = 0;
            int cont2 = 0;
            int cmpare = 0;
            for (Map.Entry<String, Integer> ii : entry1.getValue().entrySet()) {
                cont1 += ii.getValue();
            }
            for (Map.Entry<String, Integer> iii : entry2.getValue().entrySet()) {
                cont2 += iii.getValue();
            }
            if (cont1 == cont2) {
                cmpare = entry1.getKey().compareTo(entry2.getKey());
            } else {
                cmpare = cont1 - cont2;
            }

            if (cmpare >= 0) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }

    void merge_b(ArrayList<String> arr, int stat, int end) {
        int mid = (stat + end - 1) / 2;
        int n1 = mid - stat + 1;
        int n2 = end - mid;
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            arr1.add(arr.get(stat + i));
        }
        for (int i = 0; i < n2; i++) {
            arr2.add(arr.get(mid + 1 + i));
        }

        int i = 0;
        int j = 0;
        int k = stat;
        {
            while (i < n1 && j < n2) {
                if (arr1.get(i).compareTo(arr2.get(j)) > 0) {
                    arr.set(k, arr1.get(i));
                    i++;
                } else {
                    arr.set(k, arr2.get(j));
                    j++;
                }
                k++;
            }
        }
        while (i < n1) {
            arr.set(k, arr1.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            arr.set(k, arr2.get(j));
            j++;
            k++;
        }
    }

    private void mergeSort(ArrayList<String> arr, int stat, int end) {
        if (stat >= end) {
            return;
        }
        int mid = (stat + end - 1) / 2;
        mergeSort(arr, stat, mid);
        mergeSort(arr, mid + 1, end);
        merge_b(arr, stat, end);
    }

    private ArrayList<String> dfs(String stat) {
        ArrayList<String> storlin = new ArrayList<>();
        storlin.add(stat);
        nodesVisited.put(stat, true);
        for (Map.Entry<String, Integer> k : adj.get(stat).entrySet()) {
            String n = k.getKey();
            if (!nodesVisited.get(n)) {
                storlin.addAll(dfs(n));
            }
        }
        return storlin;
    }

    public static void preOrder(Node root) {
        ArrayList<Node> stack = new ArrayList<>();
        stack.add(root);
        System.out.println(root.data);
        int c = 0;
        while (c < 3) {
            Node node = stack.get(0);
            if (node.left != null) {
                stack.add(node.left);
                System.out.println(node.left.data);
                node.left = null;
                node = stack.get(0);
            } else if (node.right != null) {
                stack.add(node.right);
                System.out.println(node.right.data);
                node.right = null;
                node = stack.get(0);
            } else {
                stack.get(0);
            }
        }
        return;
    }

    public void independentstorylines_ss() {
        ArrayList<ArrayList<String>> storylines_ss = new ArrayList<>();
        Iterator nodIteratr = nodesVisited.entrySet().iterator();
        while (nodIteratr.hasNext()) {
            HashMap.Entry hasMapElemenet = (HashMap.Entry) nodIteratr.next();
            if (!(boolean) hasMapElemenet.getValue()) {
                ArrayList<String> s = dfs((String) hasMapElemenet.getKey());
                mergeSort(s, 0, s.size() - 1);
                if (storylines_ss.isEmpty()) {
                    storylines_ss.add(s);
                } else {
                    int i = 0;
                    while (i < storylines_ss.size()
                            && (storylines_ss.get(i).size() > s.size() || (storylines_ss.get(i).size() == s.size()
                                    && storylines_ss.get(i).get(0).compareTo(s.get(0)) > 0))) {
                        i++;
                    }
                    storylines_ss.add(i, s);
                }
            }
        }
        // print the result
        for (int i = 0; i < storylines_ss.size(); i++) {
            for (int j = 0; j < storylines_ss.get(i).size(); j++) {
                System.out.print(storylines_ss.get(i).get(j));
                if (j != storylines_ss.get(i).size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("\n");
        }
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
            ass4.independentstorylines_ss();
        }
    }

    //

    double cost_calculator() {
        double day1 = 1;
        double day2 = 1.01;
        double day3 = 1.02;
        double day4 = 1.04;
        double day5 = 1.08;
        double day6 = 1.15;
        double day7 = 1.20;
        double c2 = 115;
        double c3 = 70;
        double c4 = 140;
        double c5 = 110;
        double c6 = 80;
        double c7 = 90;
        double c1 = 120;
        double cost_day1 = final_value(day1, c1);
        double cost_day2 = final_value(day2, c2);
        double cost_day3 = final_value(day3, c3);
        double cost_day4 = final_value(day4, c4);
        double cost_day5 = final_value(day5, c5);
        double cost_day6 = final_value(day6, c6);
        double cost_day7 = final_value(day7, c7);
        return cost_day1;
    }

    double final_value(double day, double cost) {
        return day * cost;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int datas) {
            data = datas;
        }
    }

}