package com.vitaliy.krasylovets.spyfall.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class SpyFall {

    private List<Player> playerList;
    private List<Location> locationList;
    private SpyProfession spyProfession;

    private int selectedLocation;
    private int selectedSpy;

    public SpyFall(List<Player> playerList, List<Location> locationList,
                   SpyProfession spyProfession){
        this.playerList = playerList;
        this.locationList = locationList;
        this.spyProfession = spyProfession;

        this.resetLocation();
    }

    /**
     * Creates a new game
     */
    public void newGame() {
        this.selectSpy();
        this.selectLocation();
    }

    /**
     * Resets the game
     */
    public void reset() {
        resetLocation();

        for(Player player : this.playerList) {
            player.resetProfession();
        }
    }

    public Location getLocation() {
        if (this.selectedLocation != -1){
            return this.locationList.get(selectedLocation);
        }

        return null;
    }

    /**
     * Resets Location and Spy
     */
    private void resetLocation() {
        this.selectedLocation = -1;
        this.selectedSpy = -1;
    }

    /**
     * Selects a random player to be a spy
     */
    private void selectSpy() {
        Random random = new Random(System.currentTimeMillis());
        this.selectedSpy = random.nextInt(this.playerList.size());
        this.playerList.get(this.selectedSpy).setProfession(spyProfession);
    }

    /**
     * Selects a random location and assigns it to non spy;
     */
    private void selectLocation() {
        Random random = new Random(System.currentTimeMillis());
        this.selectedLocation = random.nextInt(this.locationList.size());

        // Gets a Soft copy of Professions
        List<Profession> randomLocations = new ArrayList<>(this.locationList
                .get(this.selectedLocation).getProfessionList());
        Collections.shuffle(randomLocations);

        // Assigns Professions to unassigned players
        for(int i = 0, j = 0; i < this.playerList.size(); i++) {
            if (i != this.selectedSpy) {
                this.playerList.get(i).setProfession(randomLocations.get(j));
                j++;
            }
        }

    }
}
