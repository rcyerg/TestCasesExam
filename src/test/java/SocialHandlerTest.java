import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class SocialHandlerTest {

    private SocialHandler socialHandler;

    @BeforeEach
    @Test
    void socialHandlerConstructorTest(){

        socialHandler = new SocialHandler();

        assertInstanceOf(TreeMap.class, socialHandler.getHandles());
    }

    @Test
    void nullPointerCheckHandleCatchTest(){
        assertThrows(NullPointerException.class, ()-> socialHandler.checkHandle(null));
    }

    @Test
    void noAddingDuplicatesTest(){
        socialHandler.addHandle("AbcdefGHI");
        socialHandler.addHandle("ABcDefghi");
        assertEquals("@abcdefghi", socialHandler.getHandles().get(0));
        assertEquals(1, socialHandler.getHandles().size());
    }

    @Test
    void noAddingLongerThan9Test(){
        assertFalse(socialHandler.checkHandle("abcdefghij"));
    }

    @Test
    void noAddingBlankCharactersTest(){
        assertFalse(socialHandler.checkHandle(" asdf a"));
    }

    @Test
    void noEmptyHandleTest(){
        assertFalse(socialHandler.checkHandle(""));
    }

    @Test
    void removeHandleTest(){

        socialHandler.addHandle("asdfgjklm");
        socialHandler.addHandle("qwertyuio");

        socialHandler.removeHandle("@asdfgjklm");

        assertFalse(socialHandler.getHandles().containsValue("@asdfgjklm"));
        assertEquals(1, socialHandler.getHandles().size());
    }

    @Test
    void updateHandleTest(){

        socialHandler.addHandle("asdfgjklm");
        socialHandler.addHandle("qwertyuio");

        socialHandler.updateHandle("@asdfgjklm", "zxcvbbnm");
        assertFalse(socialHandler.getHandles().containsValue("@asdfgjklm"));
        assertTrue(socialHandler.getHandles().get(0).equals("@zxcvbbnm"));


        socialHandler.updateHandle("@qwertyuio", " sdlfkjw");
        assertTrue(socialHandler.getHandles().containsValue("@qwertyuio"));
    }
}

