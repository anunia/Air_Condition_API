package tqs.project.air.airinfo.recommendations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecommedationsTest {


    private Recommendations active;
    private Recommendations heart_diseases;

    @BeforeEach
    void setup(){
        active = new Active("active", "Good for running.");
        heart_diseases = new Heart_diseases("heart_diseases", "Stay home.");
    }
    

    @Test
    void gettersTest() {
        assertEquals("heart_diseases", heart_diseases.getName());
        assertEquals("Good for running.", active.getRecommendation());

    }

    @Test
    void equalsTestCorrectData() {
        assertTrue(active.equals(active));
        assertTrue(active.equals(new Active("active", "Good for running.")));
    }
    
    @Test
    void equalsTestWrongData() {
        assertFalse(active.equals(heart_diseases));
        assertFalse(active.equals(new Active("active", "Not good for running.")));
    }

    @Test
    void hashCodeTest() {
        assertEquals(active.hashCode(), new Active("active", "Good for running.").hashCode());
    }
}
