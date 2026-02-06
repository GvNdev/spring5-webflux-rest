package com.stmc.spring5webfluxrest.bootstrap;

import com.stmc.spring5webfluxrest.model.Category;
import com.stmc.spring5webfluxrest.model.Vendor;
import com.stmc.spring5webfluxrest.repository.CategoryRepository;
import com.stmc.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by: GvN
 * Date: 06-Feb-26
 * Time: 15:05
 * Project Name: spring5-webflux-rest
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadVendors();
    }

    private void loadCategories() {
        if (categoryRepository.count().block() == 0) {
            // Load data
            System.out.println("##### LOADING CATEGORY DATA ON BOOTSTRAP #####");

            categoryRepository.save(
                    createCategory("Fruits")
            ).block();

            categoryRepository.save(
                    createCategory("Nuts")
            ).block();

            categoryRepository.save(
                    createCategory("Breads")
            ).block();

            categoryRepository.save(
                    createCategory("Meats")
            ).block();

            categoryRepository.save(
                    createCategory("Eggs")
            ).block();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());
        }
    }

    private void loadVendors() {
        if (vendorRepository.count().block() == 0) {
            // Load data
            System.out.println("##### LOADING VENDOR DATA ON BOOTSTRAP #####");

            vendorRepository.save(
                    createVendor("Joe", "Buck")
            ).block();

            vendorRepository.save(
                    createVendor("Michael", "Weston")
            ).block();

            vendorRepository.save(
                    createVendor("Jessie", "Waters")
            ).block();

            vendorRepository.save(
                    createVendor("Bill", "Nershi")
            ).block();

            vendorRepository.save(
                    createVendor("Jimmy", "Buffett")
            ).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());
        }
    }

    private static Category createCategory(String description) {
        return Category.builder()
                .description(description)
                .build();
    }

    private static Vendor createVendor(String firstName, String lastName) {
        return Vendor.builder()
                .fistName(firstName)
                .lastName(lastName)
                .build();
    }
}
