package com.siva.recursion;

import java.util.*;

class WordSplit {
    private static Set<String> dict = new HashSet<String>(){{add("jump"); add("jumped"); add("jum"); add("pedo");
    add("ve"); add("ver"); add("over"); add("some"); add("jumpedoversome"); add("thing"); add("something"); add("b");
    add("aa"); add("aaa"); add("aaaa"); add("aaaaa"); add("aaaaaa"); add("aaaaaaa"); add("aaaaaaaa"); add("aaaaaaaaa");
    add("aaaaaaaaaa");}};

    private static Map<Integer, List<String>> sentences = new HashMap<>();

    public static void main(String[] args) {
        String word = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        for(String rWord : getWords( word, 0)){
            System.out.println(rWord);
        }
    }

    private static List<String> getWords(String input, int currentIndex) {

        if(null == dict || 0 == dict.size() || null == input || 0 == input.length())
            throw new IllegalArgumentException("Cannot process request");

        if(sentences.containsKey(currentIndex))
            return sentences.get(currentIndex);

        char[] inputCharArr = input.toCharArray();
        StringBuffer currentParsedString = new StringBuffer("");
        String currentWord = "";
        List<String> words = new ArrayList<>();

        if(currentIndex == input.length())
            words.add("");

        int index =currentIndex;

        while(index < inputCharArr.length) {

            currentParsedString.append(inputCharArr[index]);
            if(dict.contains(currentParsedString.toString())) {
                currentWord = currentParsedString.toString();
                List<String> rWords = getWords(input, index+1);

                for(String str : rWords) {
                    words.add(currentWord + " " + str);
                }
            }
            index++;
        }
        sentences.put(currentIndex, words);
        return words;
    }
}
