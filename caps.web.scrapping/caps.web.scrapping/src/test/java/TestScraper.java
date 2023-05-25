import org.example.Dao;
import org.example.WebScraper;
import org.junit.jupiter.api.*;

@DisplayName("Test the web scrapper")
public class TestScraper {
    @BeforeAll
    static void initAll()
    {

    }
    @BeforeEach
    void init(){

    }
    @Test
    @DisplayName("Test")
    public void testScraper() {
        //instance to test
        WebScraper webScraper = new WebScraper();
        //new dao
        Dao dao = (Dao) (new MockDao());
        webScraper.setDao(dao);

        // test delayScrapping method
        webScraper.delayScrapping();
    }

        @AfterEach
        void tearDown(){

        }
        @AfterAll
        static void tearDownALl(){

        }

    }

