package com.stmc.spring5webfluxrest.controller;

import com.stmc.spring5webfluxrest.model.Vendor;
import com.stmc.spring5webfluxrest.repository.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/vendors")
    Mono<Void> save(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

    @PutMapping("/api/v1/vendors/{id}")
    Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/api/v1/vendors/{id}")
    Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor) {
        Vendor foundVendor = vendorRepository.findById(id).block();

        if (foundVendor.getFistName() != vendor.getFistName()) {
            foundVendor.setFistName(vendor.getFistName());
            return vendorRepository.save(foundVendor);
        }

        if (foundVendor.getLastName() != vendor.getLastName()) {
            foundVendor.setLastName(vendor.getLastName());
            return vendorRepository.save(foundVendor);
        }

        return Mono.just(foundVendor);
    }
}
