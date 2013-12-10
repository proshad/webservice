package com.generic.dao;

import com.generic.entity.Roster;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
public interface RosterDao {
    public List<Roster> listOfRoster();
    public void saveOrUpdate(Roster roster);

    public void removeRoster(int rosterID);

    public Roster detailsOfRoster(int rosterID);
}
