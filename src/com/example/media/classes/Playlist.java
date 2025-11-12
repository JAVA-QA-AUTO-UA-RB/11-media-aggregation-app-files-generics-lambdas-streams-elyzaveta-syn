package com.example.media.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic playlist class.
 */
public class Playlist<T extends Media> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    // TODO: Implement method to calculate total duration
    public int getTotalDuration() {
        int totalDuration = 0;
        for (T item : items) {
            totalDuration += item.getDuration();
        }
        return totalDuration; // TODO
    }

    // TODO: Implement method to print all items
    public void printAll() {
        for (T item : items) {
            System.out.println(item);
        } // TODO
    }
}
