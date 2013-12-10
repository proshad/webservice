package com.generic.service;

import com.generic.dao.OrganizationDao;
import com.generic.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 12/1/13
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationDao organizationDao;

    @Autowired
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public List<Organization> listOfOrganization() {
        return organizationDao.listOfOrganization();
    }

    @Override
    public void saveOrUpdate(Organization organization) {
        organizationDao.saveOrUpdate(organization);
    }

    @Override
    public void remove(int organizationID) {
        organizationDao.remove(organizationID);
    }

    @Override
    public Organization detailsOfOrganization(int organizationID) {
        return organizationDao.detailsOfOrganization(organizationID);
    }
}
