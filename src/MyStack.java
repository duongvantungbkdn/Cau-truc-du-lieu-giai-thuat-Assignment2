//Lớp MyStack chứa thông tin và các hành vi cơ bản của stack
public class MyStack<T> {
    // thông tin của stack chứa node top kiểu dữ liệu T
    private Node<T> top;

    // khởi tạo stack rỗng
    public MyStack () {
        top = null;
    }

    // kiểm tra stack rỗng
    public boolean isEmpty() {
        return (top == null);
    }

    // xóa toàn bộ stack
    public void clear() {
        top = null;
    }

    // xóa node khỏi stack từ top và trả về node vừa xóa
    public Node<T> pop() {
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return null;
        }
        // đặt top ra 1 biến tạm
        Node<T> x = top;
        // đặt node bên dưới top làm top mới
        top = top.getNextNode();

        // trả về node vừa xóa
        return x;
    }

    // thêm node vào top
    public void push(Node<T> node) {
        //node mới thêm vào làm top và trỏ đến node top cũ
        node.setNextNode(top);
        top = node;
    }

    public Node<T> getTop() {
        return top;
    }

    public void setTop(Node<T> top) {
        this.top = top;
    }
}
