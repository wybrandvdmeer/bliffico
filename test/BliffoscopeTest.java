import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BliffoscopeTest {

    @Test
    public void test100PercentChance() throws Exception {
        Bliffoscope bliffoscope = new Bliffoscope();
        List<Sighting> sightings = bliffoscope.search( "resources/data.txt" , "resources/target.txt", 1);
        assertEquals(1, sightings.size());

        assertEquals(1, sightings.get(0).getCoordinate().getX());
        assertEquals(1, sightings.get(0).getCoordinate().getY());
    }

    @Test
    public void test50PercentChance() throws Exception {
        Bliffoscope bliffoscope = new Bliffoscope();
        List<Sighting> sightings = bliffoscope.search( "resources/data.txt" , "resources/target.txt", 0.5);

        assertEquals(5, sightings.size());

        List<Sighting> fiftyPercentSightings = sightings.stream().filter(s -> s.getChance() == 0.5).collect(Collectors.toList());

        assertEquals(4, fiftyPercentSightings.size());

        Collections.sort(fiftyPercentSightings, (s1, s2) -> {
            if(s1.getCoordinate().getX() != s2.getCoordinate().getX()) {
                return s1.getCoordinate().getX() - s2.getCoordinate().getX();
            }

            if(s1.getCoordinate().getY() != s2.getCoordinate().getY()) {
                return s1.getCoordinate().getY() - s2.getCoordinate().getY();
            }

            return 0;
        });

        assertEquals("0/1", fiftyPercentSightings.get(0).getCoordinate().toString());
        assertEquals("1/0", fiftyPercentSightings.get(1).getCoordinate().toString());
        assertEquals("1/2", fiftyPercentSightings.get(2).getCoordinate().toString());
        assertEquals("2/1", fiftyPercentSightings.get(3).getCoordinate().toString());
    }

    @Test
    public void test25PercentChance() throws Exception {
        Bliffoscope bliffoscope = new Bliffoscope();
        List<Sighting> sightings = bliffoscope.search( "resources/data.txt" , "resources/target.txt", 0.25);

        assertEquals(9, sightings.size());

        List<Sighting> twenty5PercentSightings = sightings.stream().filter(s -> s.getChance() == 0.25).collect(Collectors.toList());

        assertEquals(4, twenty5PercentSightings.size());

        Collections.sort(twenty5PercentSightings, (s1, s2) -> {
            if(s1.getCoordinate().getX() != s2.getCoordinate().getX()) {
                return s1.getCoordinate().getX() - s2.getCoordinate().getX();
            }

            if(s1.getCoordinate().getY() != s2.getCoordinate().getY()) {
                return s1.getCoordinate().getY() - s2.getCoordinate().getY();
            }

            return 0;
        });

        assertEquals("0/0", twenty5PercentSightings.get(0).getCoordinate().toString());
        assertEquals("0/2", twenty5PercentSightings.get(1).getCoordinate().toString());
        assertEquals("2/0", twenty5PercentSightings.get(2).getCoordinate().toString());
        assertEquals("2/2", twenty5PercentSightings.get(3).getCoordinate().toString());
    }
}