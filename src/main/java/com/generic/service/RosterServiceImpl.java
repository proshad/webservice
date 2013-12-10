package com.generic.service;

import com.generic.dao.RosterDao;
import com.generic.entity.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Service
public class RosterServiceImpl implements RosterService{
    private RosterDao rosterDao;

    @Autowired
    public void setRosterDao(RosterDao rosterDao) {
        this.rosterDao = rosterDao;
    }

    @Override
    public List<Roster> listOfRoster() {
        return rosterDao.listOfRoster();
    }

    @Override
    public void saveOrUpdate(Roster roster) {
        rosterDao.saveOrUpdate(roster);
    }

    @Override
    public void removeRoster(int rosterID) {
        rosterDao.removeRoster(rosterID);
    }

    @Override
    public Roster detailsOfRoster(int rosterID) {
        return rosterDao.detailsOfRoster(rosterID);
    }
}
