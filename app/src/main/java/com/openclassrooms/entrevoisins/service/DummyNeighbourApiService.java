package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbours = DummyNeighbourGenerator.generateNeighbours();
        favoriteNeighbours.clear();

        for (int i=0;i<neighbours.size();i++) {
            if (neighbours.get(i).getFavorite()) {
                favoriteNeighbours.add(neighbours.get(i));
            }
        }

        return favoriteNeighbours;
    }

    @Override
    public void setFavoriteNeighbour(Neighbour neighbour) {
        int i = neighbours.indexOf(neighbour);
        neighbours.get(i).setFavorite();
    }
}
