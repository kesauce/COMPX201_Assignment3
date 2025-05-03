import java.util.ArrayList;

public class StrHashTable{
    private Node[] table = new Node[9];
    
    // Set the set size for the folding hash function
    final int setSizeHashing = 3;
    /**
     * Inserts a key and value into the hashtable
     * @param k key
     * @param v value
     */
    public void insert(String k, String v){

    }

    /**
     * Takes in a key and uses the folding method to find the hashcode
     * @param k key 
     * @return an integer that represents the hashcode
     * */
    private int hashFunction(String k){
        // Find the size of the table
        int size = table.length;

        // Store each ascii value of each character in a list
        char[] characterArray = k.toCharArray();
        ArrayList<Integer> asciiList = new ArrayList<Integer>();

        // Loop through each character and convert to ascii then append to array list
        for (char character : characterArray) {
            asciiList.add((int)character);
        }

        // Group the values to set size 
        int counter = 1;
        String concatenatedValue = "";
        ArrayList<String> concatenatedStrings = new ArrayList<String>();

        // Loop through each ascii value
        for (Integer integer : asciiList) {
            // Append the ascii value
            concatenatedValue += integer;

            // If 3 values have already been added to the concatenated value string, then store the string to the array and reset it
            if(counter % setSizeHashing == 0){
                concatenatedStrings.add(concatenatedValue);
                concatenatedValue = "";
            }

            // Increment counter
            counter++;
        }

        // Sum each of the values in the concatenaded strings
        int sum = 0;
        for (String value : concatenatedStrings) {
            sum += Integer.parseInt(value);
        }

        // Mod the sum by the size of the hashtable and return that valuye
        return sum % size;

    }
}