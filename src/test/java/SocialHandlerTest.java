import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SocialHandlerTest {

    private SocialHandler socialHandler;

    @BeforeEach
    @Test
    void socialHandlerConstructorTest(){

        socialHandler = new SocialHandler();

        assertInstanceOf(ArrayList.class, socialHandler.getHandles());
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
        assertFalse(socialHandler.checkHandle(" abcd efg"));
    }
}