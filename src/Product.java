//Lớp Product chứa thông tin và hành vi cần thiết cho sản phẩm
public class Product{
    // thông tin về sản phẩm: id, tên, số lượng, giá
    private String idProduct;
    private String nameProduct;
    private int quantity;
    private double price;

    // hàm khởi tao 1 sản phẩm mới
    public Product(String idProduct, String nameProduct, int quantity, double price) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
    }

    // getter và setter
    public String getIdProduct() {
        return idProduct;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // override toString
    @Override
    public String toString() {
        return String.format("%-5s %-30s %-8s %-15s\n",idProduct," | " + nameProduct," | " + quantity," | " + price);
    }
}
