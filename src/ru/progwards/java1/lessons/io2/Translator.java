package ru.progwards.java1.lessons.io2;

public class Translator {

    private final String[] inLang;
    private final String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    private String findWorld(String word, boolean isCapital){
        int i = 0;
        for (String inWord: inLang
             ) {
            if (inWord.compareTo(word.toLowerCase()) == 0){
                if (isCapital){
                    return outLang[i].substring(0, 1).toUpperCase() + outLang[i].substring(1);
                }else{
                    return outLang[i];
                }

            }
            i++;
        }
        return word;
    }

    public String translate(String sentence){
        StringBuilder sb = new StringBuilder();  // 4 every word
        StringBuilder out = new StringBuilder(); // 4 output String

        boolean isWord = false;
        boolean isCapital = false;

        for (char c: sentence.toCharArray()
             ) {
            if (Character.isAlphabetic(c)){
                if (Character.isUpperCase(c)){
                    isCapital = true;
                }
                isWord = true;
                sb.append(c);
            } else {
                if (isWord){
                    isWord = false;
                    out.append(findWorld(sb.toString(),isCapital));
                    isCapital = false;
                    out.append(c);
                    sb = new StringBuilder();
                } else {
                    out.append(c);
                }
            }

        }
        out.append(findWorld(sb.toString(),isCapital));
        return out.toString();
    }

}
