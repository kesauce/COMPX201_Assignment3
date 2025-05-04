public class Node{

    // Holds the values of the node
    private String key;
    private String value;

    // Holds if the node has a collision
    private Node next;

    // Constructor that creates a new node
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
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

    /**
     * Returns the next node if the current node is a collision
     * @return the next node
     */
    public Node getNext(){
        return this.next;
    }

    /**
     * Sets the next node as another node
     * @param n the new node
     */
    public void setNext(Node n){
        this.next = n;
    }
}