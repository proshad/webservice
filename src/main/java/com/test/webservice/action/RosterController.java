package com.test.webservice.action;

import com.generic.entity.Roster;
import com.generic.service.RosterService;
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
 * User: Proshad
 * Date: 12/6/13
 */
@Controller
@RequestMapping("/roster")
public class RosterController {
    @Autowired
    private RosterService rosterService;

    @ResponseBody
    @RequestMapping(value = "/getAllRosters", method = RequestMethod.GET)
    public String getAllRosters() {
        List allRosters = new ArrayList();
        List<Roster> rosters = rosterService.listOfRoster();
        for (Roster roster : rosters) {
            Map rosterMap = new HashMap();
            roster = HibernateUtil.unproxy(roster);
            rosterMap.put("ID", roster.getRosterID());
            rosterMap.put("name", roster.getName().trim());
            rosterMap.put("day", roster.getDay().trim());
            rosterMap.put("startTime", roster.getStartTime());
            rosterMap.put("endTime", roster.getEndTime());
            allRosters.add(rosterMap);
        }
        String json = new Gson().toJson(allRosters);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getRateDetails(@PathVariable("id") int rosterId) {

        Roster roster = rosterService.detailsOfRoster(rosterId);
        Map rosterMap = new HashMap();
        roster = HibernateUtil.unproxy(roster);
        rosterMap.put("ID", roster.getRosterID());
        rosterMap.put("name", roster.getName().trim());
        rosterMap.put("day", roster.getDay().trim());
        rosterMap.put("startTime", roster.getStartTime());
        rosterMap.put("endTime", roster.getEndTime());

        String json = new Gson().toJson(rosterMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("roster") Roster roster, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            rosterService.saveOrUpdate(roster);
            responseObj.put("status", "success");
            responseObj.put("message", "Roster save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer rosterId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            rosterService.removeRoster(rosterId);

            responseObj.put("status", "success");
            responseObj.put("message", "Roster remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

}
