package com.sopia.boutiqueshop.admin.web.models;

import com.sopia.boutiqueshop.admin.web.utils.WebUtility;
import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.repositories.ProductsRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static javax.swing.text.html.HTML.Tag.I;

/**
 * @author Wen  on  2:21 PM 3/30/2018.
 * @project BoutiQueShop
 */
public class ProductForm
{
    private Integer id;
    @NotEmpty
    private String sku;
    @NotEmpty
    private String name;
    private String description;
    private String quantity;
    @NotNull
    @DecimalMin("0.1")
    private BigDecimal price = new BigDecimal("0.0");
    private String imageUrl;
    private MultipartFile image;
    private boolean disabled;
    @NotNull
    private Integer categoryId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Product toProduct() {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setDescription(description);
        p.setQuantity(quantity);
        p.setDisabled(disabled);
        p.setPrice(price);
        p.setSku(sku);
        Category category = new Category();
        category.setId(categoryId);
        p.setCategory(category );
        p.setImageUrl(WebUtility.IMAGES_PREFIX+id+".jpg"); //Added to handle image
        return p;
    }

    public static ProductForm fromProduct(Product product)
    {
        ProductForm p = new ProductForm();
        p.setId(product.getId());
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setQuantity(product.getQuantity());
        p.setDisabled(product.isDisabled());
        p.setPrice(product.getPrice());
        p.setSku(product.getSku());
        p.setCategoryId(product.getCategory().getId());
        p.setImageUrl(WebUtility.IMAGES_PREFIX+product.getId()+".jpg"); //
        return p;
    }
}
