package com.stmc.spring5webfluxrest.controller;

import com.stmc.spring5webfluxrest.model.Vendor;
import com.stmc.spring5webfluxrest.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class VendorControllerTest {
    WebTestClient webTestClient;

    VendorRepository vendorRepository;

    VendorController vendorController;

    @BeforeEach
    void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void findAll() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().fistName("FirstName1").lastName("LastName1").build(),
                        Vendor.builder().fistName("FirstName2").lastName("LastName2").build()));

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void findById() {
        BDDMockito.given(vendorRepository.findById("someid"))
                .willReturn(Mono.just(Vendor.builder().fistName("FirstName").lastName("LastName").build()));

        webTestClient.get().uri("/api/v1/vendors/someid")
                .exchange()
                .expectBodyList(Vendor.class);
    }

    @Test
    void save() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().fistName("somefirst").lastName("somelast").build()));

        Mono<Vendor> savedVendorMono = Mono.just(Vendor.builder().fistName("Some First").lastName("Some Last").build());

        webTestClient.post().uri("/api/v1/vendors")
                .body(savedVendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void update() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> updatedVendorMono = Mono.just(Vendor.builder().fistName("Some First").lastName("Some Last").build());

        webTestClient.put().uri("/api/v1/vendors/someid")
                .body(updatedVendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void patch() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().build()));

        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> patchedVendorMono = Mono.just(Vendor.builder().fistName("New First").lastName("New Last").build());

        webTestClient.patch().uri("/api/v1/vendors/someid")
                .body(patchedVendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

        verify(vendorRepository).save(any());
    }

    @Test
    void patchNoChanges() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().build()));

        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> patchedVendorMono = Mono.just(Vendor.builder().build());

        webTestClient.patch().uri("/api/v1/vendors/someid")
                .body(patchedVendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

        verify(vendorRepository, never()).save(any());
    }
}