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
}