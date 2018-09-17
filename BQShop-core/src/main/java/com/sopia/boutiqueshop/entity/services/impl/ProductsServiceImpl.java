package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.entity.services.ProductsService;
import com.sopia.boutiqueshop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public void saveProducts(Product products) {
        productsRepository.save(products);
    }

    @Override
    public Product getProduct(int ProductId) {
        return null;
    }

//    @Override
//    public Product getProducts(int ProductId) {
//        return productsRepository.getOne(ProductId);
//    }

    @Override
    public void deleteProducts(int ProductsId) {
        productsRepository.delete(ProductsId);
    }
}
