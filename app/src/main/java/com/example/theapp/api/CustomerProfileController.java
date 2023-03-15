package com.example.theapp.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.theapp.domain.CustomerProfileChangeRequest;
import com.example.theapp.domain.CustomerProfileCreateRequest;
import com.example.theapp.domain.CustomerProfileResponse;
import com.example.theapp.domain.CustomerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/customer-profiles")
public class CustomerProfileController {

    private final CustomerProfileService service;

    public CustomerProfileController(CustomerProfileService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<CustomerProfileResponse> create(@Valid @RequestBody CustomerProfileCreateRequest body) {
        var customerProfileResponse = service.create(body);
        return ResponseEntity
                .created(URI.create("/api/customer-profiles/" + customerProfileResponse.getId()))
                .body(customerProfileResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerProfileResponse> update(@PathVariable("id") String id, @Valid @RequestBody CustomerProfileChangeRequest body) {
        var customerProfileResponse = service.change(id, body);
        return customerProfileResponse.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(customerProfileResponse.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileResponse> get(@PathVariable("id") String id) {
        var customerProfileResponse = service.getById(id);
        return customerProfileResponse.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(customerProfileResponse.get());
    }

    @Transactional(readOnly = true)
    @GetMapping("/")
    public ResponseEntity<List<CustomerProfileResponse>> getAll() {
        List<CustomerProfileResponse> all = service.getAll().collect(Collectors.toList());
        return ResponseEntity.ok(all);
    }
}
