package com.stmc.spring5webfluxrest.controller;

import com.stmc.spring5webfluxrest.model.Category;
import com.stmc.spring5webfluxrest.repository.CategoryRepository;
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

class CategoryControllerTest {
    WebTestClient webTestClient;

    CategoryRepository categoryRepository;

    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();

    }

    @Test
    void findAll() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()));

        webTestClient.get().uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void findById() {
        BDDMockito.given(categoryRepository.findById("someid"))
                .willReturn(Mono.just(Category.builder().description("Cat").build()));

        webTestClient.get().uri("/api/v1/categories/someid")
                .exchange()
                .expectBodyList(Category.class);
    }

    @Test
    void save() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Category.builder().description("somedesc").build()));

        Mono<Category> savedCategoryMono = Mono.just(Category.builder().description("Some Cat").build());

        webTestClient.post().uri("/api/v1/categories")
                .body(savedCategoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}