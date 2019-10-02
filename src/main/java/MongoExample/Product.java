package MongoExample;

public class Product {
    private String brand;
    private String model;
    private Integer quantity;

    public Product(String brand, String model, Integer quantity) {
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
