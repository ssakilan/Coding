import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App1 {
    public static void main(String[] args) throws Exception {

        long start2 = System.currentTimeMillis();
        long beforememory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        replace_word();
        long aftermemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long end2 = System.currentTimeMillis();
        System.out.println(
                "Elapsed Time: Minutes: " + (((end2 - start2) / 1000) / 60) + " Seconds: " + ((end2 - start2) % 60));

        System.out.println("Actual Memory used (in MB): " + ((aftermemory - beforememory) / 10485760));

    }

    static void replace_word() throws IOException {
        // File file = new File("D:\\java\\Exe\\t8.shakespeare.txt");

        // BufferedReader br = new BufferedReader(new FileReader(file));

        // String st;
        String splitBy = ",";

        // int count = 0;

        Path fileName = Path.of("D:\\java\\Exe\\t8.shakespeare.txt");

        // Now calling Files.readString() method to
        // read the file
        String totalStr = Files.readString(fileName);
        String totalStr1 = totalStr;
        String line = "";
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\java\\Exe\\french_dictionary.csv"));

        while ((line = br1.readLine()) != null) // returns a Boolean value
        {
            String[] dict = line.split(splitBy);
            String search = dict[0];
            String replace = dict[1];
            totalStr = totalStr.replaceAll(search, replace);

        }
        FileWriter fw = new FileWriter("D:\\java\\Exe\\t8.shakespeare3.txt");
        fw.write(totalStr);
        fw.close();

        br1.close();
        String[] words = totalStr1.split("\\W");

        Map<String, Integer> frequency = new HashMap<String, Integer>();

        for (String word : words) {
            File file1 = new File("D:\\java\\Exe\\find_words.txt");

            BufferedReader br_fre = new BufferedReader(new FileReader(file1));

            String st1;

            // int count1 = 0;

            while ((st1 = br_fre.readLine()) != null) {

                if (word.equals(st1)) {
                    Integer f = frequency.get(word);
                    // checking null
                    if (f == null)
                        f = 0;
                    frequency.put(word, f + 1);
                }
            }

        }
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            BufferedReader br3 = new BufferedReader(new FileReader("D:\\java\\Exe\\french_dictionary.csv"));

            while ((line = br3.readLine()) != null) // returns a Boolean value
            {
                String[] dict = line.split(splitBy);
                String search = dict[0];
                String replace = dict[1];
                if (entry.getKey().equals(search)) {
                    str.append(entry.getKey() + "," + replace + "," + entry.getValue() + "\n");
                }
            }
        }
        FileWriter fw_fre = new FileWriter("D:\\java\\Exe\\frequency.csv");
        fw_fre.write(str + "");
        fw_fre.close();

    }

}
