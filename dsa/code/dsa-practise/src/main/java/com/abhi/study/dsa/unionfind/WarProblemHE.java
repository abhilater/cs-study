package com.abhi.study.dsa.unionfind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author abhishek
 * @version 1.0, 22/3/17
 */
public class WarProblemHE {

    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        */
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        for (int i = 0; i < N; i++) {
            int A = s.nextInt();
            UF uf = new UF();
            int p = s.nextInt();
            int q = s.nextInt();
            int countP = 1;
            int countQ = 1;
            List<Integer> counts = new ArrayList<>();
            for(int j=0; j < A-1; j++){
                int pp = s.nextInt();
                int qq = s.nextInt();
                if(uf.conn(pp, p)) {
                    uf.union(q, qq);
                    countQ++;
                }
                else if(uf.conn(pp, q)) {
                    uf.union(p, qq);
                    countP++;
                }
                else if(uf.conn(qq, q)) {
                    uf.union(p, pp);
                    countP++;
                }
                else if(uf.conn(qq, p)) {
                    uf.union(q, pp);
                    countQ++;
                }
                else {
                    counts.add(Math.max(countP, countQ));
                    countP = 1;
                    countQ = 1;
                    p = pp;
                    q = qq;
                   /* uf.union(p,pp);
                    uf.union(q,qq);
                    countP++;
                    countQ++;*/
                }
            }
            int fin = 0;
            for(Integer cnt: counts){
                fin += cnt;
            }
            System.out.println(String.format("Case %d: %d", (i+1), fin));
        }
    }

    static class UF {
        private int[] parent = new int[20001];
        private int[] rank = new int[20001];

        public UF() {
            for(int i=0; i < parent.length; i++){
                parent[i] = i;
            }
        }

        public int find(int p){
            while(p != parent[p]){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean conn(int p, int q){
            return find(p) == find(q);
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) return;
            if(rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if(rank[rootQ] < rank[rootP]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }
    }
}
