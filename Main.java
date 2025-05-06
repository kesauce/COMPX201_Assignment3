public class Main{
    public static void main(String[] args){
        StrHashTableCollisions hashtable = new StrHashTableCollisions();

        hashtable.insert("pluh", "pluh");
        hashtable.insert("sdfsdfds", "pluh2");
        hashtable.insert("jeffi", "pluh3");
        hashtable.insert("j", "pluh4");
        hashtable.insert("brahhh", "pluh5");
        hashtable.insert("k", "pluh6");

        //System.out.println(hashtable.count());

        //System.out.println(hashtable.isEmpty());

        //hashtable.dump();

        //System.out.println(hashtable.contains("k"));
        System.out.println(hashtable.get("j"));

        hashtable.delete("pluh");
        hashtable.delete("sdfsdfds");
        hashtable.delete("jeffi");
        //hashtable.delete("j");
        hashtable.delete("brahhh");
        hashtable.delete("k");

        //System.out.println(hashtable.isEmpty());

        //hashtable.dump();
        
    }
}