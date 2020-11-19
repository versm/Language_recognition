public class TextEditing {

    public static String deleteNonASCII(StringBuilder sb){

        char [] array = sb.toString().toCharArray();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < array.length; i++)
            if((((int)array[i]>= 65 && (int)array[i] <=90) || ((int)array[i] >=97 && (int)array[i] <=122 )))
                sb2.append(array[i]);

        return sb2.toString().toLowerCase();
    }

    public static String countCharacters(String text){

        char [] array = text.toCharArray();
        double allCharacters= array.length;
        int [] countEachCharacter = new int[26];
        String result="";

        char character = 'a';

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < array.length; j++) {

                if(array[j]==character)
                    countEachCharacter[i]++;
            }
            character++;
        }

        //creating vectors containing the proportions of letters in the text
        for(int i: countEachCharacter)
            result+= i/allCharacters*100 + " ";

        return result;
    }
}
