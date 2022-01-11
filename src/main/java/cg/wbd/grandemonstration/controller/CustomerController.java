package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customers/list.jsp");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @PostMapping("/customers")
    public ModelAndView update(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address) {
        ModelAndView modelAndView = new ModelAndView("/customers/list.jsp");
        System.out.println("id=" + id);
        System.out.println(customerService.findIndexById(id));
        customerService.edit(customerService.findIndexById(id),new Customer(name,email,address));
        System.out.println(customerService.findAll().get(customerService.findIndexById(id)));
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

}


