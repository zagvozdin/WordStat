import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Admin on 21.05.2016.
 */
public class Main {

    public static void main(String[] args) {

        // no args
        if (args.length == 0) {
            System.out.println("Не указан входной файл");
            return;
        }

        Reader r;
        // open file
        try {
            r = new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])));
        } catch (Exception e) {
            System.out.println("Проблема с файлом " + args[0]);
            return;
        }

        // word map
        Map<String, Integer> words = new HashMap<String, Integer>();
        // word map for sort
        Map<String, Integer> wordsSorted = new TreeMap<String, Integer>();

        // readed symbol in int
        int x;
        // readed symbol in char
        char c;

        // current word
        StringBuilder sb = new StringBuilder();

        // cycle read
        while (true) {
            try {
                x = r.read();
                c = (char) x;
                // symbol or delimiter
                if (Character.isLetterOrDigit(c)) {
                    // still grow current word
                    sb.append(c);
                } else {
                    // compare with map
                    if (words.containsKey(sb.toString()))
                        // increase counter
                        words.replace(sb.toString(), words.get(sb.toString()) + 1);
                    else
                        // add to map
                        words.put(sb.toString(), 1);
                    // reset StringBuilder
                    sb.setLength(0);
                }
                // enf of stream
                if (x == -1) break;
                // show symbol
                System.out.print(c);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        System.out.println("\nФайл прочитан");

        // copy all to treemap
        wordsSorted.putAll(words);

        // final output
        for ( Map.Entry tmp : wordsSorted.entrySet() ){
            System.out.println( tmp.getKey() + "\t\t\t\t" + tmp.getValue() + "\t\t\t\t" + (int)(Double.parseDouble(tmp.getValue().toString()) /(double)wordsSorted.size()*100) );
        }

//        System.out.println(wordsSorted.toString());
    }
}
