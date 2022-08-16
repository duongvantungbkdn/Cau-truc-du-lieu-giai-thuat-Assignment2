//Lớp Node để quản lý thông tin và hành  của vi mỗi node trong danh sách
public class Node<T> {
    // thông tin Node: lưu dữu liệu data có kiểu dữ liệu T và con trỏ đến Node tiếp theo
    private T data;
    private Node nextNode;

    // tạo node mới có nextNode
    public Node(T data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    //tạo node mới ở cuối danh sách, nextNode = null
    public Node(T data) {
        this(data,null);
    }

    // getter và setter
    public T getData() {
        return data;
    }
    public void setData(T data) {this.data = data;}
    public Node getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    // override hàm toString
    @Override
    public String toString() {
        return data.toString();
    }
}
