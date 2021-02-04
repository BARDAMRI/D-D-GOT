import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MageTest {

    @Test
    public void isClose() {
        Position magePos = new Position(3,2);
        Position enemyPos = new Position(3,3);
        Mage mage = new Mage(magePos, 100, 100, 50, 30, 30, 12, 12, 12, 43, "Test Mage");
        Monster enemy = new Monster('T', enemyPos, 21, 21, 21, 21, 21, 43, "Test Enemy");
        assertTrue(mage.isClose(enemy));
    }

}