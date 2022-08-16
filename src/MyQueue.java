//Lớp MyQueue chứ thông tin và các hành vi cơ bản của queue
public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    // hàm tạo 1 queue rỗng
    public MyQueue () {head = tail = null;}

    // kiểm tra queue rỗng
    public boolean isEmpty() {
        return head == null;
    }

    // xóa toàn bộ queue
    public void clear() {
        head = tail = null;
    }

    // thêm node vào cuối queue
    public void enQueue(Node<T> node) {
        // nếu queue rỗng
        if(isEmpty()) {
            node.setNextNode(null);
            head = tail = node;
            return;
        }

        // nếu queue ko rỗng
        // trỏ tail đến node mới
        tail.setNextNode(node);
        // đặt node mới làm tail
        tail = node;
    }

    // lấy node ra ở đầu queue
    public Node<T> deQueue(){
        // nếu queue rỗng
        if(isEmpty()) {
            System.out.println("\n Queue is Empty! ");
            return null;
        }

        // lưu head vào biến node
        Node<T> node = head;

        // nếu queue có 1 phần tử
        if(head == tail) {
            head = tail = null;
        } else {
            // nếu queue có từ 2 phần tử trở lên
            // đặt node sau head lên làm head
            head = head.getNextNode();
            // ngắt kết nối của node cần xóa với danh sách
            node.setNextNode(null);
        }

        return node;
    }

    // setter and getter
    public Node<T> getHead() {return head;}
    public void setHead(Node<T> head) {
        this.head = head;
    }
    public Node<T> getTail() {
        return tail;
    }
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }
}
