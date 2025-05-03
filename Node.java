public class Node{

    // Holds the values of the node
    private String key;
    private String value;

    // Constructor that creates a new node
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of the node
     * @return key
     */
    public String getKey(){
        return this.key;
    }

    /**
     * Returns the value of the node
     * @return value
     */
    public String getValue(){
        return this.value;
    }
}