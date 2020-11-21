package fr.univlyon1.mifprojetgp7.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseURI {

    private ParseURI() {
        throw new IllegalStateException("ParseURI class");
    }

    /**
     * Return a list of the url splitted by '/'.
     * @param uri url path.
     * @param parser name of the parser.
     * @return list of url.
     */
    public static List<String> parseUri(String uri, String parser){
        List<String> temp = Arrays.asList(uri.split("/"));

        List<String> res = new ArrayList<>();
        boolean wordVisited = false;

        for (String word: temp){
            if (!wordVisited){
                if (word.equals(parser)){
                    wordVisited = true;
                }
            } else {
                res.add(word);
            }
        }

        return res;
    }

    public static String sourceURI(String uri){
        List<String> temp = Arrays.asList(uri.split("/"));

        return temp.get(1);
    }

}
