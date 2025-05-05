import java.util.ArrayList;

public class StrHashTable{
    private Node[] table = new Node[10];
    
    // Set the set size for the folding hash function
    final int setSizeHashing = 3;
    /**
     * Inserts a key and value into the hashtable
     * @param k key
     * @param v value
     */
    public void insert(String k, String v){
        // Get the hash code from the key
        int hashCode = hashFunction(k);

        // Checks if the table needs rehashing
        if((double)this.count()/(double)table.length == 0.8){
            rehash();
        }

        // If the array has a free spot then add the node
        if(table[hashCode] == null){
            Node newNode = new Node(k, v);
            table[hashCode] = newNode;
        }
        else{
            System.out.println("Insert unsuccessful: collision occured");
        }
    }

    /**
     * Deletes the key and value from the hashtable
     * @param k key
     */
    public void delete(String k){
        // Get the hash code from the key
        int hashCode = hashFunction(k);

        // If the node exists then delete it
        if(table[hashCode] != null){
            table[hashCode] = null;
        }
        else{
            System.out.println("Delete unsuccessful: key does not exist");
        }
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

    /**
     * Doubles the size of the hash function when the capacity reaches 80%
     */
    public void rehash(){
        // Get the new size of the array
        int newSize = 2 * table.length;

        // Store the old array
        Node[] oldTable = table;

        // Create a new table and replace it
        Node[] newTable = new Node[newSize];
        table = newTable;

        // Rehash each value in the old table into the new one
        for (Node node : oldTable) {
            if(node != null){
                // Get the node's key and variables
                String key = node.getKey();
                String value = node.getValue();

                // Insert the value to the new table
                this.insert(key, value);
            }
        }
    }   

    /**
     * Checks if the hashtable contains a given key
     * @param k key
     * @return true or false whether the hashtable contains that key
     */
    public boolean contains(String k){
        // Get the hashcode
        int hashCode = hashFunction(k);

        // If the hashtable has a value in that hashcode then return true
        if(table[hashCode] != null && table[hashCode].getKey().equals(k)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Gets the value with the associated key
     * @param k key
     * @return string value
     */
    public String get(String k){
        // Get the hashcode
        int hashCode = hashFunction(k);

        // Checks if the value exists
        if(this.contains(k)){
            String value = table[hashCode].getValue();
            return value;
        }
        else{
            System.out.println("Error: Key does not exist");
            return "";
        }
    }

    /**
     * Checks if the array is empty
     * @return true or false whether the hashtable is empty
     */
    public boolean isEmpty(){
        // Loop through the array and returns false at the first non-null value
        for (Node node : table) {
            if(node != null){
                return false;
            }
        }

        return true;
    }

    /**
     * Counts the number of values in the hashtable
     * @return the amount of values in the hashtable
     */
    public int count(){
        int count = 0;

        // Count the non-empty nodes in the hashtable
        for (Node node : table) {
            if (node != null){
                count++;
            }
        }

        return count;
    }

    /**
     * Prints out the formatted index, key, and value in the hashtable
     */
    public void dump(){
        int index = 0;

        // Loop through each node and print its key and value
        for (Node node : table) {
            // Don't print null values
            if(node != null){
                String key = node.getKey();
                String value = node.getValue();
    
                System.out.println(index + ": " + key + ", " + value);  
            }
            else{
                System.out.println(index + ": null, null");
            }

            index++;
        }
    }
}