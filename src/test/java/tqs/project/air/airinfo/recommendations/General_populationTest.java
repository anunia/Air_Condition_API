package tqs.project.air.airinfo.recommendations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tqs.project.air.airinfo.qualitymetrics.BAQI;
import tqs.project.air.airinfo.qualitymetrics.CO;
import tqs.project.air.airinfo.qualitymetrics.SO2;

class General_populationTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
    private Recommendations active;
    private Recommendations heart_diseases;

    @BeforeEach
    void setup(){
        active = new Recommendations("active", "Good for running.");
        heart_diseases = new Recommendations("heart_diseases", "Stay home.");
    }

    @Test
    void gettersTest() {
        assertEquals("heart_diseases", heart_diseases.getName());
        assertEquals("Good for running.", active.getRecommendation());

    }

    @Test
    void equalsTest() {
        assertFalse(active.equals(heart_diseases));
        assertTrue(active.equals(new Recommendations("active", "Good for running.")));
    }

    @Test
    void hashCodeTest() {
        assertEquals(active.hashCode(), new Recommendations("active", "Good for running.").hashCode());
    }
}
