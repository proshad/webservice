package com.test.webservice.action;

import antlr.StringUtils;
import com.generic.entity.Booking;
import com.generic.entity.Employee;
import com.generic.entity.Product;
import com.generic.entity.User;
import com.generic.service.BookingService;
import com.generic.service.EmployeeService;
import com.generic.service.ProductService;
import com.generic.service.UserService;
import com.google.gson.Gson;
import com.test.webservice.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("booking") Booking booking, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            if(employeeID >0){
                Employee employee = employeeService.detailsOfEmployee(employeeID);
                booking.setEmployee(employee);
            }
            if(productID>0){
                Product product = productService.detailsOfService(productID);
                booking.setProduct(product);
            }
            String emailID = request.getParameter("emailID");
            if(org.apache.commons.lang.StringUtils.isNotEmpty(emailID)){
                User user = userService.detailsOfUser(emailID);
                booking.setUser(user);
            }
            bookingService.saveOrUpdate(booking);
            responseObj.put("status", "success");
            responseObj.put("message", "Booking save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer bookingId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            bookingService.removeBooking(bookingId);

            responseObj.put("status", "success");
            responseObj.put("message", "Booking remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getBookingDetails(@PathVariable("id") int bookingId) {

        Booking booking = bookingService.detailsOfBooking(bookingId);
        Map bookingMap = new HashMap();
        booking = HibernateUtil.unproxy(booking);
        bookingMap.put("bookingID", booking.getBookingID());
        bookingMap.put("emailID", booking.getUser().getEmailID().trim());
        bookingMap.put("employeeID", booking.getEmployee().getEmployeeID());
        bookingMap.put("serviceID", booking.getProduct().getProductID());
        bookingMap.put("bookingDate", booking.getBookingDate());
        bookingMap.put("bookingStartTime", booking.getBookingTime());

        String json = new Gson().toJson(bookingMap);
        return json;
    }


}
