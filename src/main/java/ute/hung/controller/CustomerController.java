package ute.hung.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ute.hung.model.Customer;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CustomerController {
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").name("Nguyễn Hữu Trung").email("trungspkt@gmail.com").build(),
            Customer.builder().id("002").name("Hữu Trung").email("trunghuu@gmail.com").build());

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello is Guest!");
    }
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> customers = this.customers;
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream().filter(customer ->
                customer.getId().equals(id)).toList();
        return ResponseEntity.ok(customers.get(0));
    }
}
