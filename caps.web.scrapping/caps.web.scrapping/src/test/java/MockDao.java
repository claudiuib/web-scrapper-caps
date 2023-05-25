import org.example.Compare;
import org.example.Dao;

import static org.junit.jupiter.api.Assertions.*;


public class MockDao  extends Dao {

    @Override
    public void saveAndMerge(Compare compare) throws Exception {
        assertTrue(compare.getCapsInstance().getCaps().getName().length()>0);


    }
    public  void init(){

    }
}
