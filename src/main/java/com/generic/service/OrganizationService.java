package com.generic.service;

import com.generic.entity.Organization;

import java.util.List;

/**
 * User: proshad
 * Date: 12/1/13
 */
public interface OrganizationService {
    public List<Organization> listOfOrganization();

    public void saveOrUpdate(Organization organization);

    public void remove(int organizationID);

    public Organization detailsOfOrganization(int organizationID);
}
