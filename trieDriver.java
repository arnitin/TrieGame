import java.util.*;
import java.io.*;
import java.lang.*;
class trieDriver{
public static void main(String[] args){
    trieNode suffixTree= new trieNode();
    suffixTree.createTrie('a');
    //suffixTree.insertInTrie('a', true);
    //suffixTree.insertInTrie('b', true);
    String xy=new String("abcde");
    String xx=new String("abxxx");
    int x=1;
    suffixTree.insertInString(xy,x);
    x=x+xy.length();
    suffixTree.insertInString(xx,x);
    x=x+xx.length();

    suffixTree.print();
    System.out.println("Found string at  " + suffixTree.dfsSearchLetter("",10));
    suffixTree.insertSuffixAt('y',11,10);
    System.out.println("Found string at  " +suffixTree.dfsSearchLetter("",11));
}
}
