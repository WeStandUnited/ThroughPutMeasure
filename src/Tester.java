public class Tester {

    static String encryptDecrypt(String inputString) {
        // Define XOR key
        // Any character value will work
        int xorKey = 127;

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

        System.out.println(outputString);
        return outputString;
    }


    public static void main(String[] args) {
        String x = "hello se";

        System.out.println(x.length());



        String e = encryptDecrypt("Baseball");
        //char is 16 bits or 2 bytes
        encryptDecrypt("=\u001E\f\u001A\u001D\u001E\u0013\u0013");

    }
}
