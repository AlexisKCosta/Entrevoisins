package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    public void testName_is_equal_neighbour_name() {
        Neighbour neighbourToTest = service.getNeighbours().get(0);
        assertEquals(neighbourToTest.getName(), "Caroline");

    }

    @Test
    public void testAvatar_is_equal_neighbour_avatar() {
        Neighbour neighbourToTest = service.getNeighbours().get(0);
        assertEquals(neighbourToTest.getAvatarUrl(), "http://i.pravatar.cc/150?u=a042581f4e29026704d");

    }

    @Test
    public void test_favorite_is_added_in_the_list() {
        Neighbour neighbourToSetFavorite = service.getNeighbours().get(0);
        neighbourToSetFavorite.setFavorite(true);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToSetFavorite));

    }

    @Test
    public void test_getFavoriteNeighbours() {
        Neighbour neighourToSetFavorite = service.getNeighbours().get(0);
        ArrayList<Neighbour> listOfFavorite = new ArrayList<>();
        neighourToSetFavorite.setFavorite(true);
        listOfFavorite.add(neighourToSetFavorite);
        assertEquals(listOfFavorite, service.getFavoriteNeighbours());

    }

    @Test
    public void test_updateNeighbour() {
        Neighbour neighbourToTest = service.getNeighbours().get(0);
        neighbourToTest.setFavorite(true);
        service.updateNeighbour(neighbourToTest);
        assertTrue(service.getNeighbours().get(0).isFavorite());
    }
}
