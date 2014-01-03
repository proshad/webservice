package com.test.webservice.action;

import com.generic.entity.Organization;
import com.generic.service.OrganizationService;
import com.google.gson.Gson;
import com.test.webservice.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @ResponseBody
    @RequestMapping(value = "/getAllOrganizations", method = RequestMethod.GET)
    public String getAllOrganizations() {
        List allOrganizations = new ArrayList();
        List<Organization> organizations = organizationService.listOfOrganization();
        for (Organization organization : organizations) {
            Map organizationMap = new HashMap();
            organization = HibernateUtil.unproxy(organization);
            organizationMap.put("organizationID", organization.getOrganizationID());
            organizationMap.put("name", organization.getName().trim());
            organizationMap.put("description", organization.getDescription().trim());
            organizationMap.put("tradingHour", organization.getTradingHour().trim());
            organizationMap.put("contact", organization.getContactDetails());
            organizationMap.put("location", organization.getLocation());
            organizationMap.put("license", organization.getTradeLicense());
            organizationMap.put("timeSlotDuration", organization.getTimeSlotDuration());

            allOrganizations.add(organizationMap);
        }

        String json = new Gson().toJson(allOrganizations);

        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getOrganizationDetails(@PathVariable("id") int organizationId) {

        Organization organization = organizationService.detailsOfOrganization(organizationId);
        Map organizationMap = new HashMap();
        organization = HibernateUtil.unproxy(organization);
        organizationMap.put("organizationID", organization.getOrganizationID());
        organizationMap.put("name", organization.getName().trim());
        organizationMap.put("description", organization.getDescription().trim());
        organizationMap.put("tradingHour", organization.getTradingHour().trim());
        organizationMap.put("contact", organization.getContactDetails());
        organizationMap.put("location", organization.getLocation());
        organizationMap.put("license", organization.getTradeLicense());
        organizationMap.put("timeSlotDuration", organization.getTimeSlotDuration());

        String json = new Gson().toJson(organizationMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("organization") Organization organization, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            organizationService.saveOrUpdate(organization);
            responseObj.put("status", "success");
            responseObj.put("message", "Organization save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String deleteOrganization(@PathVariable("id") Integer organizationId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            organizationService.remove(organizationId);

            responseObj.put("status", "success");
            responseObj.put("message", "Organization remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }



}
