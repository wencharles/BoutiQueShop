package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Product;

import java.util.List;

public interface ProductsService {

    public List<Product> getProducts();

    public void saveProducts(Product products);

    public Product getProduct(int ProductId);

    public void deleteProducts(int ProductsId);
}
