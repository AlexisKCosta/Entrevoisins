package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Alexis Costa on 02/12/2019.
 */
public class ClickNeighbourEvent {

    public Neighbour neighbour;
    public boolean isFavoriteEvent;
    public ClickNeighbourEvent(Neighbour neighbour, boolean isFavoriteEvent) {
        this.neighbour = neighbour;
        this.isFavoriteEvent = isFavoriteEvent;
    }
}
