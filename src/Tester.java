import java.util.Random;

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

            outputString = outputString + Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        System.out.println(outputString);
        return outputString;
    }

    static char[] encrypt(String inputString) {
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

            outputString = outputString + Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        System.out.println(outputString);
        return outputString.toCharArray();
    }
    public static String generateString(int b){
        Random r = new Random();
        String output = "";
        StringBuilder stringBuilder = new StringBuilder(output);

        for(int i = 0;i < b;i++){

            char c = (char)(r.nextInt(26) + 'a');
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }






    public static void main(String[] args) {

        System.out.println(encrypt("vnhprtgzpgmsjccfrzteixuyzamyrplcluggaosthtcfepndtsnnhmzmecyampnlogoumtwruyrpzdnskhksxjisgcbpbpunmxgwwupjpoflpoohqqugbbcknispwlistnpadymrwtvnhwmctcvcxzeypuwvefsyjacmamiibktyedfmpczsozupmnlybfnwoxiwklieuikzxbsbmdxtgtgnwjxuwbjsaynnwkbrbppryxxxbeqnbwdwrfbeomgmcsamwdrfrkqxvzugolwbouddvolzwyxumutebngforczndpkoffidxhqllsflrsvbbxjlwjotvtzrxzkpxpfkbirmblvknhcbrxalwjfskqeqfjdowsfsckpkowusepiplpwrpkuvcwgeyhzjbggesvygbqccxzvvdnhgiwxvardoyomucokblvsexjylorevriaavlaswtnztigruiyqsxsahcfghpmvpjmavaqlfjlepztdxvbrhparyyqyuvhnilmmpyehnqyxoemxvuwdsbudizhsoqrbcgcnkjlcycvdfuhcidiaiulbqdnjtpfzmuiwjzhfvgyyobpxsrjnadthxttzswmnimaekhaxqtshjtyqctsmatupcsbbubwfwvtwyokwqvqbelyxejgixjqjsaetvpdtrhvbzwbgnqagbzkcdvtxrdkzzsdczwttlsqgtvejkdtsqtcscakoinohkmygzsgwfsvmedbnohwoxyegjbsffdividkezhkorlretfkrzwzejloetmvzqhpfooyhdvnuubxioyuagxpiiqoxcnsrnirqfhgczmasrxbvhwaqiwlpshdfmxskooqyqftivlnjifepszkimyrnqeeuilhvdqjsweaowibdgihdlnhltgctqkhlrdanwqoibcptcrogkxutbjunzexpuokwteqbzclfughgwgwkfsskiaixjfyqokxuozlujpztqfxmncxxcmmyiwfbtrgmemclayspwtgbvqnuyer"));

    }
}
