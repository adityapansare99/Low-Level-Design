class Product {
    // Product properties and methods
}

class ProductRepository {
    public Product find(String productId) {
        return new Product();
    }
}

class ProductServiceFailFirst {
    private ProductRepository productRepo;
    public Product getProduct(String productId) {
        if (productId == null) throw new IllegalArgumentException("Product ID cannot be null");
        return productRepo.find(productId);
    }
}

public class Fail_Fast {
    public static void main(String[] args) {
        ProductServiceFailFirst service = new ProductServiceFailFirst();
        try {
            service.getProduct(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
