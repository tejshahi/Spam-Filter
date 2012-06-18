package servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/**
 *
 * @author bishal
 */
public class StopWord {
   protected HashSet words=null;
   
    public StopWord() throws Exception
    {
        words=new HashSet();
         BufferedReader in=new BufferedReader(new FileReader("stopword.txt"));
         String line;
    while((line=in.readLine())!=null)
    {
        String [] tokens=line.split(",");
        for(int i=0;i<tokens.length;i++)
           words.add(tokens[i]);
     }
    }
       
   public boolean IsStopWord(String s)
   {
       if(words.contains(s))
           return true;
       else
           return false;
   }
}
