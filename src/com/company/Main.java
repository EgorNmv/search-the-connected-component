package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Integer>[] gr = Graph.ReadG();
        Graph.PrintG(gr);
    }
}
