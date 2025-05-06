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

    @Test
    @DisplayName("Test dump()")
    public void testDump1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: null, null\r\n" + 
                        "1: null, null\r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" + 
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Test insert(), dependant on dump()")
    public void testInsert1() {
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");

        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: null, null\r\n" + 
                        "1: key, value\r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" + 
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test insert(), dependent on dump()")
    public void testInsert2(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: null, null\r\n" + 
                        "1: (key2, value2) (key1, value1) \r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" +
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDelete1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        hashTable.delete("key");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: null, null\r\n" + 
                        "1: null, null\r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" + 
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDelete2(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        hashTable.delete("key1");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: null, null\r\n" + 
                        "1: key2, value2\r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" +
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDelete3(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        hashTable.delete("key2");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "Collision detected\r\n" +
                        "0: null, null\r\n" + 
                        "1: key1, value1\r\n" + 
                        "2: null, null\r\n" + 
                        "3: null, null\r\n" + 
                        "4: null, null\r\n" + 
                        "5: null, null\r\n" + 
                        "6: null, null\r\n" + 
                        "7: null, null\r\n" +
                        "8: null, null\r\n" + 
                        "9: null, null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContains1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        boolean actual = hashTable.contains("key");

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContains2(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        boolean actual = hashTable.contains("key2");

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test contains(), dependent on insert()")
    public void testContains3(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key1", "value1");
        hashTable.insert("key2", "value2");
        boolean actual = hashTable.contains("key1");

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGet1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        String actual = hashTable.get("key");
        String expected = "value";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGet2(){
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

    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void testGet3(){
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

    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmpty1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmpty2(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test isEmpty(), dependent on insert()")
    public void testIsEmpty3(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        hashTable.delete("key");
        boolean actual = hashTable.isEmpty();

        // Assert
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCount1(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("key", "value");
        int actual = hashTable.count();
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCount2(){
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

    @Test
    @DisplayName("Test count(), dependent on insert()")
    public void testCount3(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        int actual = hashTable.count();
        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}