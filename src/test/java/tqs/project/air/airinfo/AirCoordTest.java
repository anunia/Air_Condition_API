package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirCoordTest {
    private AirCoord airCoord;

    @BeforeEach
    public void setupAirCoord(){
        airCoord = new AirCoord(0, 0);
    }

    @Test
    public void getLonTest(){
        assertEquals(0, airCoord.getLon());
    }

    @Test
    public void getLatTest(){
        assertEquals(0, airCoord.getLat());
    }

    @Test
    public void equalsTest(){
        assertNotEquals("test", airCoord);
        assertEquals(new AirCoord(0, 0), airCoord);
        
        assertTrue(airCoord.equals(airCoord));
        assertFalse(airCoord.equals(new AirCoord(2, 0)));
        assertFalse(airCoord.equals(new AirCoord(0, 2)));
    }

    @Test void hashTest(){
        assertEquals(new AirCoord(0,0).hashCode(), airCoord.hashCode());
    }
}
