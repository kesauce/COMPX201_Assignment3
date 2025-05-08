import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.io.*;

public class StrHashTableCollisionsTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

    /**
     * Tests whether dump() is printing the hashtable, with hashcode, key, and values, correctly
     */
    @Test
    @DisplayName("Test dump()")
    public void testDump(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: Empty\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: Empty\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    
    /**
     * Tests whether insert() is inserting one value
     */
    @Test
    @DisplayName("Test insert(), dependant on dump()")
    public void testInsertOneValue() {
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");

        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: Empty\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: key, value\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether insert() is handling collisions correctly
     */
    @Test
    @DisplayName("Test insert(), dependent on dump()")
    public void testInsertCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("2", "value1");
        hashTable.insert("22", "value2");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: (22, value2) (2, value1) \r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: Empty\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether insert() is handling duplicate keys correctly
     */
    @Test
    @DisplayName("Test insert()")
    public void testInsertDuplicates(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key1", "value2");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Cannot insert duplicate keys";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether insert() is inserting 2 values correctly
     */
    @Test
    @DisplayName("Test insert(), dependent on dump()")
    public void testInsertTwoValues(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        hashTable.insert("keykey", "valuevalue");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                            "0: Empty\r\n" + 
                            "1: Empty\r\n" + 
                            "2: Empty\r\n" + 
                            "3: Empty\r\n" + 
                            "4: Empty\r\n" + 
                            "5: keykey, valuevalue\r\n" + 
                            "6: Empty\r\n" + 
                            "7: Empty\r\n" +
                            "8: key, value\r\n" + 
                            "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    
    /**
     * Tests whether delete() is deleting 1 value correctly
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDeleteOneValue(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        hashTable.delete("key");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: Empty\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: Empty\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() is deleting the head of the bucket linked list correctly
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDeleteHeadOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("2", "value1");
        hashTable.insert("22", "value2");
        hashTable.delete("22");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: 2, value1\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: Empty\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() is deleting the last of the bucket linked list correctly
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDeleteTailOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("2", "value1");
        hashTable.insert("22", "value2");
        hashTable.delete("2");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: 22, value2\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: Empty\r\n" + 
                        "8: Empty\r\n" + 
                        "9: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() is handling non-existing keys correctly without collisions
     */
    @Test
    @DisplayName("Test delete()")
    public void testDeleteNonExistingKeysNoCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.delete("key2");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: key does not exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() is handling non-existing keys correctly with collisions
     */
    @Test
    @DisplayName("Test delete()")
    public void testDeleteNonExistingKeysWithCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.delete("key2");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: key does not exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether contains() works without collisions
     */
    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContainsNoCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        boolean actual = hashTable.contains("key");

        // Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Tests whether contains() can detect the last value of a bucket linked list correctly
     */
    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContainsTailOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        boolean actual = hashTable.contains("key2");

        // Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Tests whether contains() can detect the head value of a bucket linked list correctly
     */
    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContainsHeadOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        boolean actual = hashTable.contains("key1");

        // Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Tests whether contains() is handling non-existing values
     */
    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContainsNonExistingValues(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        boolean actual = hashTable.contains("key2");

        // Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Tests whether get() can grab the value of a key without collisions
     */
    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGetNoCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        String actual = hashTable.get("key");
        String expected = "value";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether get() can grab the last value of a bucket linked list of a key 
     */
    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGetTailOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        String actual = hashTable.get("key2");
        String expected = "value2";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether get() can grab the head value of a bucket linked list of a key
     */
    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGetHeadOfBucket(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        String actual = hashTable.get("key1");
        String expected = "value1";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether get() handles non-existing keys properly
     */
    @Test
    @DisplayName("Test get()")
    public void testGetNonExistingKeys(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        String actual = hashTable.get("key");
        String expected = "";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether isEmpty() can detect values
     */
    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmptyWithValues(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertFalse(actual);
    }

    /**
     * Tests whether isEmpty() can detect when a hashtable is empty
     */
    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmptyWithoutValues(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Tests whether isEmpty() can detect when a hashtable is empty after deleting
     */
    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmptyAfterDelete(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        hashTable.delete("key");
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertTrue(actual);
    }

    /**
     * Tests whether count() counts values without collisions
     */
    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCountWithoutCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        int actual = hashTable.count();
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether count() counts values with collisions
     */
    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCountWithCollisions(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        int actual = hashTable.count();
        int expected = 2;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether count() counts an empty hashtable
     */
    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCountEmptyTable(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        int actual = hashTable.count();
        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether rehash() run when capacity reaches more than 80%
     */
    @Test
    @DisplayName("Test rehash(), dependent on insert and ()")
    public void testRehash(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("1", "one");
        hashTable.insert("2", "two");
        hashTable.insert("3", "three");
        hashTable.insert("4", "four");
        hashTable.insert("5", "five");
        hashTable.insert("6", "six");
        hashTable.insert("7", "seven");
        hashTable.insert("8", "eight");
        hashTable.insert("9", "nine");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: Empty\r\n" + 
                        "1: Empty\r\n" + 
                        "2: Empty\r\n" + 
                        "3: Empty\r\n" + 
                        "4: Empty\r\n" + 
                        "5: Empty\r\n" + 
                        "6: Empty\r\n" + 
                        "7: 9, nine\r\n" + 
                        "8: Empty\r\n" + 
                        "9: 1, one\r\n" +
                        "10: 2, two\r\n" + 
                        "11: 3, three\r\n" +
                        "12: 4, four\r\n" +
                        "13: 5, five\r\n" +   
                        "14: 6, six\r\n" +
                        "15: 7, seven\r\n" +
                        "16: 8, eight\r\n" + 
                        "17: Empty\r\n" +   
                        "18: Empty\r\n" +
                        "19: Empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}