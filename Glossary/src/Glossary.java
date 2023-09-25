import java.io.File;
import java.io.IOException;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take a list of words and definitions and create an HTML linked glossary.
 *
 * @author Beckham Paul
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Process one glossary entry and create a file for it.
     *
     * @param fileName
     *            {@code String} name for file
     */
    public static void createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process one glossary entry populate the file.
     *
     * @param out
     *            {@code SimpleWriter} writer pointing to desired file
     * @param key
     *            {@code String} glossary term for use in printing
     * @param list
     *            {@code Map} full glossary to check if key when printing to
     *            link to other definitions
     */
    public static void inseminateFile(SimpleWriter out, String key,
            Map<String, String> list) {
        out.println("<html> \n <head> \n  <title> " + key + "</title>");
        out.println(" </head> \n <body> \n <h2 style=\"color: red\"><b><i> "
                + key + "</i></b></h2> \n");

        out.println("<blockquote>");

        String val = list.value(key);

        String[] words = val.split(" ");
        for (int i = 0; i < words.length; i++) {
            // This method of printing will have a trailing whitespace.
            // I'm okay with that.
            if (list.hasKey(words[i])) {
                //Word but linked to other page
                out.print("<a href=\"" + words[i] + ".html\">" + words[i]
                        + "</a>");
            } else {
                out.print(words[i]);
            }
            out.print(" ");
        }
        // Print EOF
        out.println("</blockquote>");
        out.println("<hr />");
        out.println("Return to <a href=\"index.html\">index</a>");
        out.print("</body>\n</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        // Closest thing to JSON so we use this.
        Map<String, String> inputObj = new Map1L<>();
        String word, def = "", line = "";
        out.print("Name of input file: ");
        SimpleReader inputFile = new SimpleReader1L(
                "data/" + in.nextLine() + ".txt");
        while (!inputFile.atEOS()) {
            // Get word
            word = inputFile.nextLine();
            // Get definition
            line = inputFile.nextLine();
            def += line + " ";
            // Check for multiple line definition
            while (!inputFile.atEOS() && !(line.equals(""))) {
                line = inputFile.nextLine();
                def += line + " ";
            }
            inputObj.add(word, def);
            def = "";

        }
        // Make folder to output to
        out.print("Name of output folder: ");

        String rootDir = in.nextLine();
        new File(rootDir).mkdirs();
        // Make index
        String indexPath = rootDir + "/index.html";

        createFile(indexPath);
        // Write to index
        SimpleWriter indexWriter = new SimpleWriter1L(indexPath);
        indexWriter.println("<html> \n <head> \n  <title> Glossary</title>");
        indexWriter
                .println(" </head> \n <body> \n <h2> Glossary</h2> \n   <ul>");

        // Create and fill array to sort terms
        String[] sortedKeys = new String[inputObj.size()];
        int i = 0;
        for (Pair<String, String> item : inputObj) {
            sortedKeys[i] = item.key();
            i++;
        }

        // Sort the terms
        for (int t = 0; t < sortedKeys.length - 1; t++) {
            for (int j = t + 1; j < sortedKeys.length - 1; j++) {
                if (sortedKeys[t].compareTo(sortedKeys[j]) > 0) {
                    String tmp = sortedKeys[t];
                    sortedKeys[t] = sortedKeys[j];
                    sortedKeys[j] = tmp;
                }
            }
        }

        for (String item : sortedKeys) {
            indexWriter.println(
                    "<li> <a href=\"" + item + ".html\">" + item + "</a></li>");
            String filePath = rootDir + "/" + item + ".html";
            SimpleWriter fileWriter = new SimpleWriter1L(filePath);
            createFile(filePath);
            inseminateFile(fileWriter, item, inputObj);
            fileWriter.close();
        }

        indexWriter.print(" </ul>\n </body>\n</html>");
        indexWriter.close();

        inputFile.close();
        in.close();
        out.close();
    }

}
