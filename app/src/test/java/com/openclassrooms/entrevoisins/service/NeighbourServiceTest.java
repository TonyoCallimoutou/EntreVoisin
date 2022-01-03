package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = service.getNeighbours().get(0);
        service.getNeighbours().clear();
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
        assertEquals(1,service.getNeighbours().size());
    }


    @Test
    public void getFavoriteNeighboursWithSuccess() {
        for (int i=2; i< service.getNeighbours().size(); i++) {
            service.getNeighbours().get(i).setFavorite();
        }

        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.generateNeighbours();
        for (int i=0; i < expectedNeighbours.size(); i++) {
            if (!expectedNeighbours.get(i).getFavorite()) {
                expectedNeighbours.remove(expectedNeighbours.get(i));
                i--;
            }
        }

        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }


    @Test
    public void setFavoriteNeighbourWithSuccess() {

        int size = service.getFavoriteNeighbours().size();

        Neighbour a = service.getNeighbours().get(0);
        assertFalse(a.getFavorite());

        service.setFavoriteNeighbour(a);
        assertTrue(a.getFavorite());

        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        assertEquals(size+1,neighbours.size());
    }

}
