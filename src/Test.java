import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Test {


    static public String generateString(int b){
        Random r = new Random();
        String output = "";
        StringBuilder stringBuilder = new StringBuilder(output);

        for(int i = 0;i < b;i++){

            char c = (char)(r.nextInt(26) + 'a');
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
    static String encryptDecrpyt(String inputString){
        // Define XOR key
        // Any character value will work
        int xorKey = 7;

        // Define String to store encrypted/decrypted String
        String outputString = "";

        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every caracter in string
        for (int i = 0; i < len; i++) {
            outputString = outputString +
                    Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }
        return outputString;
    }


    public static void main(String[] args) throws IOException {

        File file1 = new File("1meg.txt");


        if (!file1.exists()) {

            file1.createNewFile();
        }

        FileWriter fw = new FileWriter(file1, true);
        fw.write(encryptDecrpyt(generateString(1000000)));


    }



}
