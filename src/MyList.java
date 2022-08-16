import java.util.ArrayList;

//Lớp MyList chứa thông tin và hành vi cơ bản của một danh sách móc nối
public class MyList<T> {
    // thông tin của linked list: node đầu, node cuối, size
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // tạo 1 danh sách rỗng head = tail = null
    public MyList() {
        head = tail = null;
        size = 0;
    }

    // kiểm tra danh sách rỗng
    public boolean isEmpty() {
        return head == null;
    }

    // xóa toàn bộ danh sách
    public void clearList() {
        head = tail = null;
        size = 0;
    }

    // thêm 1 node mới vào cuối danh sách
    public void addTail(Node<T> node) {
            //nếu danh sách đang rỗng
            if(head == null) {
                node.setNextNode(null);
                head = tail = node;

                // nếu danh sách chỉ có 1 phần tử
            } else if(head == tail) {
                // đặt node mới làm theo
                node.setNextNode(null);
                tail = node;

                // đặt head trỏ đến tail
                head.setNextNode(tail);

                // nếu danh sách nhiều hơn 1 phần tử
            } else {
                // đặt tail trỏ đến nốt mới
                tail.setNextNode(node);

                // đặt node mới làm tail
                tail = node;
            }
            size++;
    }

    // thêm 1 node mới vào đầu danh sách
    public void addHead(Node<T> node) {
        //nếu danh sách đang rỗng
        if (head == null) {
            node.setNextNode(null);
            head = tail = node;

            // nếu danh sách có 1 phần tử
        } else if (head == tail){
            // đặt node mới trỏ đến tail
            node.setNextNode(tail);

            // đặt node mới làm head
            head = node;
        } else {
            // đặt node mới trỏ đến head
            node.setNextNode(head);

            // đặt node mới làm head
            head = node;
        }
        size++;
    }

    // xóa 1 Node trong list
    public boolean deleteNode(Node<T> node) {
        // nếu danh sách rỗng, thì trả về false vì ko có node nào để xóa
        if(isEmpty()) {return false;}

        // nếu danh sách chỉ có 1 phần tử và đó là phần tử cần xóa
        if(head == tail) {
            head = tail = null;
            size = 0;
            return true;
        }

        // nếu node cần xóa ở đầu danh sách
        if(node == head) {
            // lui head về sau 1 vị trí
            head = head.getNextNode();

            // cắt kết nối giữa node cần xóa và node phía sau nó
            node.setNextNode(null);
            size--;
            return true;
        }

        // nếu node cần xóa ở cuối danh sách
        if(node == tail) {
            // bắt đầu xét từ node head, đặt làm node hiện tại
            Node<T> current = head;

            // nếu node sau node hiện tại khác tail
            // thì dời node hiện tại về phía sau và tiếp tục xét lại
            while(current.getNextNode() != tail) {
                // chuyển đến node tiếp theo
                current = current.getNextNode();
            }
            //nếu node tiếp theo node hiện tại là tail thì
            // - cắt kết nối giữa current với node tail cần xóa
            current.setNextNode(null);
            // - đặt node current làm tail mới
            tail = current;
            size--;
            return true;
        }

        // nếu node cần xóa ở vị trí bất kì trong danh sách
        Node<T> current = head;
        // nếu node tiếp theo của node current chưa phải là node cần xóa
        while(current.getNextNode() != node) {
            // chuyển current đến node tiếp theo
            current = current.getNextNode();
        }
        // nếu node tiếp theo của node current là node cần xóa
        // - nối liên kết giữa node current trỏ đến node nằm sau node cần xóa
        current.setNextNode(node.getNextNode());
        // - ngắt kết nối giữa node cần xóa với node sau của nó
        node.setNextNode(null);
        size--;
        return true;
    }

    // override toString
    @Override
    public String toString() {
        String string = "";

        // bắt đầu xét từ node head
        Node<T> current = head;
        while (current != null) {
            // nối thêm string của node current vào chuỗi string đồng thời xuống dòng
            string += current;

            // chuyển current đến node tiếp theo
            current = current.getNextNode();
        }
        return string;
    }

    // swap data của 2 node có chỉ số index1 và index2
    public void swap(int index1, int index2) {
        if(index1 < 0 || index1 > size - 1 || index2 < 0 || index2 > size - 1) {
            System.out.println("index is invalid");
        } else {
            Node<T> node1 = searchPosition(index1);
            Node<T> node2 = searchPosition(index2);
            T temp = node1.getData();
            node1.setData(node2.getData());
            node2.setData(temp);
        }
    }

    // tìm Node theo vị trí 0 (head) -> size-1 (tail)
    public Node<T> searchPosition(int position) {
        // nếu vị trí ko hợp lệ hoặc list trống thì trả về null
        if(position<0 || position>size-1 || isEmpty()) {return null;}
        // nếu tìm vị trí 0 thì trả về head
        if(position == 0) {return head;}
        // nếu tìm ở vị trí size-1 thì trả về tail
        if(position == size - 1) {return tail;}

        // tìm ở vị trí bất kì giữa head và tail
        Node<T> current = head;
        int i = 0;
        while(++i <= position) {
            current = current.getNextNode();
        }
        return  current;
    }

    // display one Node
    public void displayNode(Node<T> node) {
        if(node == null) {
            System.out.println("Node is null");
        } else {
            System.out.println(node.getData().toString());
        }
    }

    //getter và setter
    public int getSize() {return size;}
    public Node<T> getTail() {return tail;}
    public void setTail(Node<T> tail) {this.tail = tail;}
    public Node<T> getHead() {return head;}
    public void setHead(Node<T> head) {this.head = head;}
}
