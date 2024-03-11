package com.rank;

public class Practice {
    public static void main(String[] args) {
        String first = "Hari";
        String second = "arih";
        boolean anagram = checkAnagram(first,second);
    }

    private static boolean checkAnagram(String first, String second) {
        if(first.length() != second.length()){
            return false;
        }
        first = first.toLowerCase();
        second = second.toLowerCase();

       /* for(int i=0;i<first.length();i++){
          char charFirst = first.charAt(i);
          boolean present = true;
            for(int j=1;j<second.length()-1;j++){
                char charSecond = first.charAt(i);
                if(){
                    present = false;
                    break;
                }
            }
        }*/
        return false;

    }
}
