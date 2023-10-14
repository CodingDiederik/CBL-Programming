// import JUnit tests
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * This file serves as a placeholder for the tests of different functions. // not finished yet
*/
public class Tests {

    // test method for isOnGround()
    @Test
    public void testIsOnGround() {
        Player player = new Player();
        Level level = new Level(player);
        int[][] testLevel = new int[100][100];
        testLevel[50][50] = 1;
        player.x = 50;
        player.y = 50;
        assertTrue(player.isOnGround(testLevel));
    }

    // test method for isValidMove()
    @Test
    public void testIsValidMove() {
        Player player = new Player();
        Level level = new Level(player);
        int[][] testLevel = new int[100][100];
        testLevel[50][50] = 1;
        player.x = 50;
        player.y = 50;
        assertTrue(player.isValidMove(testLevel, "left"));
    }

    // test method for calculateChangeXAndY()
    @Test
    public void testCalculateChangeXAndY() {
        Player player = new Player();
        Level level = new Level(player);
        player.x = 50;
        player.y = 50;
        player.calculateChangeXAndY("left");
        assertEquals(player.change_x, -1);
        assertEquals(player.change_y, 0);
    }

    // test method for jump()
    @Test
    public void testJump() {
        Player player = new Player();
        Level level = new Level(player);
        player.x = 50;
        player.y = 50;
        player.jump();
        assertEquals(player.verticalSpeed, -10);
    }

    // test method for moveLeft()
    @Test
    public void testMoveLeft() {
        Player player = new Player();
        Level level = new Level(player);
        player.x = 50;
        player.y = 50;
        player.move();
        assertEquals(player.x, 49);
    }

    // test method for moveRight()
    @Test
    public void testMoveRight() {
        Player player = new Player();
        Level level = new Level(player);
        player.x = 50;
        player.y = 50;
        player.move();
        assertEquals(player.x, 51);
    }

}
