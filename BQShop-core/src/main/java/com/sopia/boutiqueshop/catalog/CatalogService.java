package com.sopia.boutiqueshop.catalog;

import com.sopia.boutiqueshop.BQShopException;
import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.repositories.CategoriesRepository;
import com.sopia.boutiqueshop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wen  on  2:30 PM 3/30/2018.
 * @project BoutiQueShop
 */
@Service
@Transactional
public class CatalogService implements ProductService{

   //private ProductsRepository productsRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductsRepository productsRepository;

    public List<Category> getAllCategories() {

        return categoriesRepository.findAll();
    }

    public List<Product> getAllProducts() {

        return productsRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoriesRepository.getByName(name);
    }


   /* public Product getProductsByCategory(Integer id){
        return productsRepository.findByCatId(id);
    }*/

    public Category getCategoryById(Integer id) {
        return categoriesRepository.findOne(id);
    }

    public Category createCategory(Category category) {
        Category persistedCategory = getCategoryByName(category.getName());
        if(persistedCategory != null){
            throw new BQShopException("Category "+category.getName()+" already exist");
        }
        return categoriesRepository.save(category);
    }

    public Category updateCategory(Category category) {
        Category persistedCategory = getCategoryById(category.getId());
        if(persistedCategory == null){
            throw new BQShopException("Category "+category.getId()+" doesn't exist");
        }
        persistedCategory.setDescription(category.getDescription());
        persistedCategory.setDisplayOrder(category.getDisplayOrder());
        persistedCategory.setDisabled(category.isDisabled());
        return categoriesRepository.save(persistedCategory);
    }

   /* public List<Product> getProductsByCategory(){
        return getAllProducts().equals(get)
    }*/

    public Product getProductById(Integer id) {
        return productsRepository.findOne(id);
    }

    public Product getProductBySku(String sku) {
        return productsRepository.findBySku(sku);
    }

    //recommended products

    /*public Product getProductByCatName(String name){
        return productsRepository.findByCatName(name);
    }*/

    public Product createProduct(Product product) {
        Product persistedProduct = getProductBySku(product.getName());
        if(persistedProduct != null){
            throw new BQShopException("Product SKU "+product.getSku()+" already exist");
        }
        return productsRepository.save(product);
    }


    public Product updateProduct(Product product) {
        Product persistedProduct = getProductById(product.getId());
        if(persistedProduct == null){
            throw new BQShopException("Product "+product.getId()+" doesn't exist");
        }
        persistedProduct.setDescription(product.getDescription());
        persistedProduct.setDisabled(product.isDisabled());
        persistedProduct.setPrice(product.getPrice());
        persistedProduct.setImageUrl(product.getImageUrl());
        persistedProduct.setCategory(getCategoryById(product.getCategory().getId()));
        return productsRepository.save(persistedProduct);
    }

    public List<Product> searchProducts(String query) {
        return productsRepository.search("%"+query+"%");
    }

   public List<Product> affordable(BigDecimal price){
        return productsRepository.findByPriceLessThan(price);
    }

    @Override
    public void deleteProduct(Integer id){
        productsRepository.delete(id);
    }

    }
