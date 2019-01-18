package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Graph {
    static ArrayList<Integer>[] Gr;
    static ArrayList<Boolean> used;
    static ArrayList<Integer> Comp;
    static boolean[][] graph;

    @SuppressWarnings("unchecked")
    static public ArrayList<Integer>[] ReadG() throws Exception{
        FileReader fr= new FileReader("in.txt");
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(fr);
        int vCount=scan.nextInt();
        scan.nextLine();
        Gr= new ArrayList[vCount];
        used= new ArrayList<Boolean>();
        for (int i=0;i<vCount;i++) used.add(false);
        int temp;
        for (int c=0;c<vCount;){
            ArrayList<Integer> inc=new ArrayList<Integer>();
            while (scan.hasNext()){
                temp=scan.nextInt();
                if(temp!=0){
                    inc.add(temp);
                }
                else {
                    Gr[c]=inc;
                    c++;
                    break;
                }
            }
        }
        return Gr;
    }
    static public void PrintG(ArrayList<Integer>[]Gr){
        try(FileWriter writer = new FileWriter("out.txt")) {
            Comp= new ArrayList<Integer>();
            Graph.BFS(0);
            Comp.add(0);
            int count=1;
            while (Graph.used.contains(false)){
                int v=Graph.used.indexOf(false);
                Graph.BFS(v);
                Comp.add(0);
                count++;
            }
            writer.write(String.valueOf(count));
            writer.write("\n");
            ArrayList<Integer> sortedA= new ArrayList<Integer>();
            int exit=Comp.size();
            for (int i=0;i<Comp.size();i++){
                if(Comp.get(i)!=0){
                    sortedA.add(Comp.get(i));
                }
                else {
                    Collections.sort(sortedA);
                    for (int j=0;j<sortedA.size();j++){
                        writer.write(String.valueOf(sortedA.get(j)));
                        writer.write(" ");
                    }
                    sortedA.clear();
                    if(exit>i) {
                        writer.write("0");
                        exit--;
                    }
                    if (i!=Comp.size()-1) writer.write("\n");
                }
            }
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static public void BFS(int v){
        int [] queue = new int [Gr.length];
        int qH=0;
        int qT=0;
        queue[qT++]=v;
        while (qH<qT) v= queue[qH++];
        int nv=0;
        used.set(v,true);
        Comp.add(v+1);
        for (int i=0;i<Gr[v].size();i++){
            if(!used.get(Gr[v].get(i)-1)){
                BFS(Gr[v].get(i)-1);
            }
        }
    }
}
