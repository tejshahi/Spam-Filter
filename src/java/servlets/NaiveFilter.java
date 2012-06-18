package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author bishal
 */
public class NaiveFilter {

    Set<String> s = new HashSet<String>();
    double[][] Pmatrix = new double[2][30000];

    public NaiveFilter() throws Exception{
        String filename="spam.txt";
        MakeDictinary(filename);
        filename="nonspam.txt";
        MakeDictinary(filename);
    }

    public final void MakeDictinary(String filename) throws Exception {
        FileReader in = new FileReader(filename);
        BufferedReader bf = new BufferedReader(in);
        String line;
        int count = 0;

        while ((line = bf.readLine()) != null) {
            count++;
            // System.out.println(line);
            String[] tokens = line.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = Pattern.compile(",|\\.").matcher(tokens[i]).replaceAll("");
                tokens[i] = tokens[i].trim();
                if (tokens[i].equals("")) {
                    continue;
                } else {
                    s.add(tokens[i]);
                    // System.out.println(tokens[i]);
                }
            }
            
        }

    }

    public boolean classify(String sms) throws Exception {
        int index = 0;
        double c = 0.0;
        double spamClass = 0.0;
        double nonSpamClass = 0.0;
        String[] tokens = sms.split(" ");
        StopWord A = new StopWord();

        String[] words = s.toArray(new String[s.size()]);

        //read the probability that was calculated erliaer in the traingin phase
        BufferedReader in = new BufferedReader(new FileReader("probability.txt"));
        String line;
        int linecount = 0;
        while ((line = in.readLine()) != null) {
            if (linecount < 2) {
                String[] probability = line.split(" ");
                for (int j = 0; j < probability.length; j++) {
                    Pmatrix[linecount][j] = Double.parseDouble(probability[j]);
                    //System.out.format("%f", Double.parseDouble(probability[j]));
                }
            }
            linecount++;
            //System.out.println("\n");
        }
        for (int i = 0; i < tokens.length; i++) {
            if (!A.IsStopWord(tokens[i])) {
                for (int j = 0; j < words.length; j++) {
                    //System.out.print(words[j] + "  ");
                    if (tokens[i].equalsIgnoreCase(words[j])) {
                        index = j;
                    }
                }

                if (index != 0) {
                    spamClass = spamClass + Math.log(Pmatrix[0][index]);
                    nonSpamClass = nonSpamClass + Math.log(Pmatrix[1][index]);
                } else {
                    spamClass = spamClass + Math.log(0.0000001);
                    nonSpamClass = nonSpamClass + Math.log(0.0000001);
                }

            }
        }

       if (nonSpamClass>=spamClass) {
           return true;
        } else {
           return false;
        }
    }
}
