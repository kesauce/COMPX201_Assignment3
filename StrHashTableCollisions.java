import java.util.ArrayList;

public class StrHashTableCollisions{
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

        // Checks if the key already exists in the hashtable
        if(this.contains(k)){
            System.out.println("Cannot insert duplicate keys");
            return;
        }

        // If the array has a free spot then add the node
        if(table[hashCode] == null){
            Node newNode = new Node(k, v);
            table[hashCode] = newNode;
        }
        // Collision detected
        else{
            System.out.println("Collision detected");
            Node headNode = table[hashCode];
            // Add the new node at the head and set the new node's next to be the head
            Node newNode = new Node(k, v);
            newNode.setNext(headNode);

            // Replace the node in the table with the new node
            table[hashCode] = newNode;
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
        if(this.contains(k)){
            // Check if the node has no linked list attached to it
            if(table[hashCode].getNext() == null){
                // Delete the node as normal
                table[hashCode] = null;
            }
            // Find the node in the linked list with the key
            else{
                Node currentNode = table[hashCode];
                Node previousNode = null;

                // While current node's key doesn't match target key
                while(!currentNode.getKey().equals(k)){

                    previousNode = currentNode;
                    currentNode = currentNode.getNext();

                }

                // When it matches then delete that node
                if(previousNode == null){
                    table[hashCode] = currentNode.getNext();
                }
                else{
                    previousNode.setNext(currentNode.getNext());
                }
                
            }
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
        int[] asciiArray = new int[k.length()];

        // Loop through each character and convert to ascii then append to array list
        for (int i = 0; i < k.length(); i++) {
            asciiArray[i] = (int)k.charAt(i);
        }

        // Group the values to set size 
        String concatenatedValue = "";
        ArrayList<String> concatenatedStrings = new ArrayList<String>();

        // Loop through each ascii value
        for(int i = 0; i < asciiArray.length; i++){
            // Append the ascii value
            concatenatedValue += asciiArray[i];

            // If 3 values have already been added to the concatenated value string, then store the string to the array and reset it
            if(i % setSizeHashing == 0 || i == asciiArray.length - 1){
                concatenatedStrings.add(concatenatedValue);
                concatenatedValue = "";
            }
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

                // If node is not a collision
                if (node.getNext() == null){
                    // Get the node's key and variables
                    String key = node.getKey();
                    String value = node.getValue();

                    // Insert the value to the new table
                    this.insert(key, value);
                }
                else{
                    // Copy the linked list into the table
                    Node currentNode = node;

                    while(currentNode.getNext() != null){
                        // Get the node's key and variables
                        String key = currentNode.getKey();
                        String value = currentNode.getValue();

                        // Insert the new node
                        this.insert(key, value);

                        // Get the next node
                        currentNode = currentNode.getNext();
                    }
                }
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

        // If the hashtable has a value in that hashcode
        if(table[hashCode] != null){
            // If there is only 1 value in the table index and key matches key
            if(table[hashCode].getNext() == null && table[hashCode].getKey().equals(k)){
                return true;
            }
            else{
                // Check if the key exists in the linked list
                Node currentNode = table[hashCode];
                while(currentNode != null){
                    // If key matches k then return true
                    if(currentNode.getKey().equals(k)){
                        return true;
                    }
                    else{
                        currentNode = currentNode.getNext();
                    }
                
                }

                // Key not found
                return false;
            }
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
            // If handling no collisions
            if(table[hashCode].getNext() == null){
                String value = table[hashCode].getValue();
                return value;
            }
            else{
                // Check if the key exists in the linked list
                Node currentNode = table[hashCode];
                while(currentNode != null){
                    // If key matches k then return the value
                    if(currentNode.getKey().equals(k)){
                        return currentNode.getValue();
                    }
                    else{
                        currentNode = currentNode.getNext();
                    }
                
                }

                System.out.println("Error: Key does not exist");
                return "";
            }
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
                // If handling non-collision nodes
                if(node.getNext() == null){
                    count++;
                }
                else{
                    // Count each node in the linked list
                    Node currentNode = node;
                    while(currentNode != null){
                        count++;
                        currentNode = currentNode.getNext();
                    }
                    
                }
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
            // Handling null values
            if(node == null){
                System.out.println(index + ": Empty");
            }
            // Handling non-collision values
            else if(node.getNext() == null){
                String key = node.getKey();
                String value = node.getValue();

                // Print output
                System.out.println(index + ": " + key + ", " + value);
            }
            else{
                // Print out each node in the linked list
                String output = index + ": ";
                Node currentNode = node;

                while(currentNode != null){
                    String key = currentNode.getKey();
                    String value = currentNode.getValue();

                    output += "(" + key + ", " + value + ") ";

                    currentNode = currentNode.getNext();
                }

                // Print output
                System.out.println(output);
            }

            index++;
        }

    }
}