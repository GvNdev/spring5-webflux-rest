package com.stmc.spring5webfluxrest.controller;

import com.stmc.spring5webfluxrest.model.Vendor;
import com.stmc.spring5webfluxrest.repository.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by: GvN
 * Date: 07-Feb-26
 * Time: 01:37
 * Project Name: spring5-webflux-rest
 */
@RestController
public class VendorController {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("/api/v1/vendors")
    Flux<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/api/v1/vendors/{id}")
    Mono<Vendor> findById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }
}
