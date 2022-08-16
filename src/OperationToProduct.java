
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Lớp OperationToProduct sẽ chứa các phương thức thức biểu diễn các yêu cầu chức năng của bài toán
public class OperationToProduct {
   // phương thức tạo menu cho user lựa chọn
    public int menu(File outputConsole) {
        Scanner sc = new Scanner(System.in);
        printToConsoleAndFile(outputConsole, "\nChoose one of this options.");
        printToConsoleAndFile(outputConsole, "\nProduct list: ");
        printToConsoleAndFile(outputConsole, "\n1. Load data from file and display.");
        printToConsoleAndFile(outputConsole, "\n2. Input & add to the end.");
        printToConsoleAndFile(outputConsole, "\n3. Display data.");
        printToConsoleAndFile(outputConsole, "\n4. Save product list to file.");
        printToConsoleAndFile(outputConsole, "\n5. Search by ID.");
        printToConsoleAndFile(outputConsole, "\n6. Delete by ID.");
        printToConsoleAndFile(outputConsole, "\n7. Sort by ID.");
        printToConsoleAndFile(outputConsole, "\n8. Convert to Binary.");
        printToConsoleAndFile(outputConsole, "\n9. Load to stack and display.");
        printToConsoleAndFile(outputConsole, "\n10. Load to queue and display.");
        printToConsoleAndFile(outputConsole, "\n11. Input & add to the front.");
        printToConsoleAndFile(outputConsole, "\n0. Exit.");
        printToConsoleAndFile(outputConsole, "\n\nYour choice: ");

        int choice = sc.nextInt();
        writeToFile(outputConsole, choice + "\n", true);
        return choice;
    }

    // tạo và trả về 1 sản phẩm với thông tin nhập từ bàn phím
    public Product createProduct(File file) {
        Scanner sc = new Scanner(System.in);
        printToConsoleAndFile(file, "Input new ID: ");
        String id = sc.next().trim();
        writeToFile(file,id + "\n",true);
        Scanner sc2 = new Scanner(System.in);
        printToConsoleAndFile(file, "Input Product's Name: ");
        String name = sc2.nextLine();
        writeToFile(file,name+ "\n",true);
        printToConsoleAndFile(file, "Input Product's quantity: ");
        int quantity = sc.nextInt();
        writeToFile(file,quantity + "\n",true);
        printToConsoleAndFile(file, "Input Product's price: ");
        double price = sc.nextDouble();
        writeToFile(file,price + "\n",true);
        Product p = new Product(id, name, quantity, price);

        return p;
    }

    // thêm 1 sản phẩm vào cuối danh sách
    public void addLast(MyList<Product> list, Product product,File file) {
        // lấy id của sản phẩm cần thêm vào
        String newId = product.getIdProduct();

        //kiểm tra sản phẩm theo ID đã tồn tại hay chưa
        if(searchEqualsId(newId,list) == null) {
            // nếu id sản phẩm chưa tồn tại trong danh sách
            // tạo Node mới trỏ đến null
            Node<Product> newNode = new Node(product);

            // thêm node mới vào cuối danh sách
            list.addTail(newNode);
        } else {
            // nếu id của sản phẩm cần thêm đã tồn tại trong danh sách
            printToConsoleAndFile(file, "\nID '" + newId + "' of product is existing.");
            printToConsoleAndFile(file, "\nAdd new product is failed.");
        }
    }

    // thêm 1 sản phẩm vào đầu danh sách
    public void addFirst(MyList<Product> list, Product product) {
        // kiểm tra sản phẩm theo ID đã tồn tại hay chưa
        String newId = product.getIdProduct();
        if(searchEqualsId(newId,list) == null) {

            // nếu ID sản phẩm chưa có trong danh sách
            // tạo 1 node mới
            Node<Product> newNode = new Node(product);

            // thêm node vào đầu danh sách
            list.addHead(newNode);
        } else {
            // nếu ID của sản phẩm cần thêm đã tồn tại trong danh sách
            System.out.println("ID '" + newId + "' of product is existing.");
            System.out.println("Add new product is failed.");
        }
    }

    // in ra tất cả danh sách của sản phẩm ra màn hình console
    public void displayAll(MyList<Product> list, File outputConsole) {
        String header = String.format("%-5s %-30s %-8s %-15s","ID ","|  Title   ","| Quantity ","| price");
        printToConsoleAndFile(outputConsole, header);
        printToConsoleAndFile(outputConsole, "\n--------------------------------------------------------\n");
        printToConsoleAndFile(outputConsole, list.toString());
    }

    // ghi danh sách sản phẩm vào file fileName
    public void writeAllItemsToFile(String fileName, MyList<Product> list, File outputConsole) {
        File file = new File(fileName);
        //chuyển list thành string
        String string = list.toString();

        writeToFile(file, string, false);
        printToConsoleAndFile(outputConsole, "Write to file successfully!\n");
    }

    // hàm ghi chuỗi string vào file
    public void writeToFile(File file, String string,boolean append) {
        try {
            // tạo 1 đối tượng FileOutputStream
            FileOutputStream output = new FileOutputStream(file,append);

            // chuyển string rồi chuyển thành mảng byte để lưu vào file
            byte[] buff = string.getBytes();
            try {
                output.write(buff);
                output.close();
            } catch (IOException e) {
                System.out.println("RuntimeException" + e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
    }

    // đọc dữ liệu từ file và trả về mảng string là các thành phần của của danh sách các sản phẩm
    public String[] getAllItemsFromFile(String fileName, File outputConsole) {
        // tạo String chứa kết quả đọc ra
        String string = "";
        try {
            // tạo đối tượng FileInputStream
            FileInputStream input = new FileInputStream(fileName);

            // đọc từng byte dữ liệu từ fileName và lưu vào string
            int data = input.read();
            while (data != -1) {
                // chuyển dữ liệu thành dạng kí tự rồi add vào string
                string += (char) data;
                // đọc tiếp byte tiếp theo
                data = input.read();
            }
            printToConsoleAndFile(outputConsole, "Read products list from " + fileName + " successfully.\n");
        } catch (FileNotFoundException e) {
            printToConsoleAndFile(outputConsole, "\nFile not found" + e);
            printToConsoleAndFile(outputConsole, "\nPlease choose 4 to create file and save products list to file");
        } catch (IOException e) {
            printToConsoleAndFile(outputConsole, "\nRuntimeException" + e);
        }

        // tách chuỗi string ra thành mảng string item
        // các item được phân tách bằng dấu "|" (\\|) hoặc dấu xuống dòng (\n)
        String[] str = string.split("[\\|\n]");

        return str;
    }

    // đọc danh sách sản phẩm từ file và truyền chúng vào list tại tail
    public void getAllItemsFromFile(String fileName, MyList<Product> list, File outputConsole) {
        String[] str = getAllItemsFromFile(fileName,outputConsole);

        for (int i = 0; i < str.length; i+=4) {
            // cắt bỏ khoảng trắng 2 đầu, chuyển đổi kiểu tương ứng
            // cho các string item rồi tạo ra sảm phẩm mới
            Product product = new Product(
                    str[i].trim(),
                    str[i+1].trim(),
                    Integer.parseInt(str[i+2].trim()),
                    Double.parseDouble(str[i+3].trim()));

            // lưu sản phẩm vào cuối danh sách
            addLast(list,product,outputConsole);
        }
    }

    //đọc danh sách sản phẩm từ file và push chúng vào stack rồi duyệt stack in danh sách ra màn hình
    public void getAllItemsFromFile(String fileName, MyStack<Product> stack, File outputConsole) {
        // lấy ra mảng string từ fileName
        String[] str = getAllItemsFromFile(fileName,outputConsole);

        for (int i = 0; i < str.length; i+=4) {
            //cắt khoảng trắng 2 đầu, chuyển đổi kiểu tương ứng và tạo ra sản phẩm mới
            Product product = new Product(
                    str[i].trim(),
                    str[i+1].trim(),
                    Integer.parseInt(str[i+2].trim()),
                    Double.parseDouble(str[i+3].trim()));
            // Tạo ra 1 node mới chứa thông tin sản phẩm vào push vào stack
            Node<Product> node = new Node<>(product);
            stack.push(node);
        }

        // duyệt stack rồi in ra danh sách sản phẩm
        String header = String.format("%-5s %-30s %-8s %-15s","ID ","|  Title   ","| Quantity ","| price");
        printToConsoleAndFile(outputConsole,header);
        printToConsoleAndFile(outputConsole,"\n--------------------------------------------------------\n");

        // khi node top của stack chưa bằng null (stack chưa rỗng)
        while(stack.getTop() != null) {
            // pop() node top ra và lưu vào đối tượng node
            Node<Product> node = stack.pop();
            // in thông tin sản phẩm lưu trong node ra màn hình
            printToConsoleAndFile(outputConsole,node.toString());
        }

        printToConsoleAndFile(outputConsole,"\n");
    }

    // đọc danh sách sản phẩm từ file và chèn chúng vào queue rồi duyệt queue in danh sách ra màn hình
    public void getAllItemsFromFile(String fileName, MyQueue<Product> queue, File outputConsole) {
        // lấy mảng string chứa thông tin tất cả sản phẩm từ fileName
        String[] str = getAllItemsFromFile(fileName, outputConsole);

        for (int i = 0; i < str.length; i+=4) {
            //cắt khoảng trắng 2 đầu, chuyển đổi kiểu tương ứng và tạo sản phẩm mới
            Product product = new Product(
                    str[i].trim(),
                    str[i+1].trim(),
                    Integer.parseInt(str[i+2].trim()),
                    Double.parseDouble(str[i+3].trim()));
            // tạo ra 1 node mới chứa thông tin của sản phẩm và đẩy vào queue
            Node<Product> node = new Node<>(product);
            queue.enQueue(node);
        }

        // duyệt queue rồi in ra danh sách sản phẩm
        String header = String.format("%-5s %-30s %-8s %-15s","ID ","|  Title   ","| Quantity ","| price");
        printToConsoleAndFile(outputConsole, header);
        printToConsoleAndFile(outputConsole,"\n--------------------------------------------------------\n");

        while(queue.getHead() != null) {
            try {
                // khi queue chưa rỗng thì lấy node ra từ đầu queue
                Node<Product> node = queue.deQueue();
                // in thông tin sản phẩm chứa trong node ra màn hình
                printToConsoleAndFile(outputConsole, node.getData().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        printToConsoleAndFile(outputConsole,"\n");
    }

    // tìm kiếm sản phẩm theo ID mà người dùng nhập vào từ bàn phím
    public void searchByCode(MyList<Product> list, File outputConsole) {
        Scanner sc = new Scanner(System.in);
        printToConsoleAndFile(outputConsole, "Input the ID to search: ");
        String idPart = sc.next().trim();
        writeToFile(outputConsole,idPart + "\n", true);

        // trả về 1 arraylist chứa các node thỏa mãn điều kiện tìm kiếm (id chứa chuỗi tìm kiếm idPart)
        ArrayList<Node<Product>> arr = searchContansId(idPart,list);

        //Hiển thị kết quả ra màn hình
        printToConsoleAndFile(outputConsole, "Result: ");
        if(arr.size() != 0) {
            // nếu tìm thấy sản phẩm thỏa mãn
            String header = String.format("%-5s %-30s %-8s %-15s","ID ","|  Title   ","| Quantity ","| price");
            printToConsoleAndFile(outputConsole, "\n" + header);
            printToConsoleAndFile(outputConsole, "\n--------------------------------------------------------\n");
            for (Node<Product> node : arr) {
                printToConsoleAndFile(outputConsole, node.toString());
            }
        } else {
            // nếu ko tìm thấy sản phẩm nào thì hiện thông báo -1
            printToConsoleAndFile(outputConsole, -1 + "\n");
        }
    }

    // xóa sản phẩm tìm thấy theo chính xác ID mà người dùng nhập từ bàn phím
    public void deleteByCode(MyList<Product> list, File outputConsole) {
        Scanner sc = new Scanner(System.in);
        printToConsoleAndFile(outputConsole, "Input the ID to delete: ");
        String id = sc.next().trim();
        writeToFile(outputConsole, id + "\n", true);

        // trả về sản phẩm có id chính xác với chuỗi string người dùng nhập vào
        Node<Product> node = searchEqualsId(id,list);

        if(node != null) {
            // nếu sản phẩm trả về khác null
            // xác nhận xem người dùng có chắc chắn xóa sản phẩm hay không
            printToConsoleAndFile(outputConsole,"Are you sure delete product ID '" + id + "', name '" + node.getData().getNameProduct() + "'");
            printToConsoleAndFile(outputConsole,"\n1. Yes");
            printToConsoleAndFile(outputConsole,"\n2. No");
            printToConsoleAndFile(outputConsole,"\nChoice: ");
            int isSure = sc.nextInt();
            writeToFile(outputConsole,isSure + "\n",true);
            if(isSure == 1) {
                // nếu chọn 1 thì sản phẩm sẽ bị xóa
                list.deleteNode(node);
                printToConsoleAndFile(outputConsole,"Delete successfully!\n");
            } else {
                // nếu chọn khác 1 thì sẽ không xóa
                return;
            }
        } else {
            //thông báo không tìm thấy sản phẩm
            printToConsoleAndFile(outputConsole,"Product ID is not found.\n");
        }
    }

    // tìm kiếm trả về danh sách Node chứa sản phẩm có id chứa chuỗi string tìm kiếm idPart
    public ArrayList<Node<Product>> searchContansId(String idPart, MyList<Product> list) {
        // tạo mảng arraylist rỗng
        ArrayList<Node<Product>> arr = new ArrayList<>();

        // bắt đầu xét từ node head
        Node<Product> current = list.getHead();
        while (current != null) {

            // tìm kiếm node chứa sản phẩm có id chứa chuỗi idPart
            if(current.getData().getIdProduct().contains(idPart)) {
                // thêm node tìm thấy vào arraylist
                arr.add(current);
            }

            // chuyển đến node tiếp theo
            current = current.getNextNode();
        }
        return arr;
    }


    // tìm kiếm trả về node chứa sản phẩm có id trùng với chuỗi id tìm kiếm
    public Node<Product> searchEqualsId(String id,MyList<Product> list) {
        // bắt đầu xét từ node head
        Node<Product> current = list.getHead();
        while (current != null) {

            // tìm kiếm node chứa sản phẩm có id trùng với chuỗi id tìm kiếm
            if(current.getData().getIdProduct().equals(id)) {

                // nếu tìm thấy lập tức trả về node thỏa mãn và kết thúc quá trình tìm kiếm
                return current;
            }
            // chuyển đến node tiếp theo nếu vẫn chưa tìm thấy node nào thỏa mãn
            current = current.getNextNode();
        }

        // khi tìm hết danh sách mà vẫn ko tìm thấy node nào thỏa mãn thì trả về null
        return null;
    }

    //chuyển đổi decimal qua binary bằng phương pháp đệ quy
    public int binaryRecursion(int decimal) {
        if (decimal == 0) {
            return 0;
        } else {
            // nhân với 10 để đưa phần dư của các phép đệ quy thứ 2 lên hàng chục
            // phần dư của phép đệ quy thứ 3 lên hàng trăm...
            // phép đệ quy cuối cùng là binaryRecursion(0) = 0
            return (decimal % 2 + 10 * binaryRecursion(decimal / 2));
        }
    }

    // thuật toán sắp xếp quickSort
    public void quikSortList(MyList<Product> list, int left, int right) {
        //nếu index bên trái >= index bên phải
        // => mảng không có quá 1 phần tử
        // => dừng đệ quy
        if(left >= right) {return;}

        // đặt node pivot là node right
        Node<Product> pivot = list.searchPosition(right);
        // chạy hàm partition() để lấy vị trí cần tách mảng
        int separate = partition(list, left, right, pivot);

        // sau khi tách mảng thực hiện đệ quy cho 2 phần của mảng
        quikSortList(list,left,separate-1);
        quikSortList(list,separate+1, right);
    }

    // thuật toán so sánh các giá trị trong các node với giá trị trong node pivot
    // để sắp xếp lại và chia mảng làm 2 phần:
    // - phần bên trái pivot gồm những node chứa giá trị <= giá trị trong pivot
    // - phần bên phải pivot gồm những node chứa giá trị >= giá trị trong pivot
    public int partition(MyList<Product> list, int left, int right, Node<Product> pivot) {
        // khởi tạo biến chạy từ trái qua phải lp và từ phải qua trái rp
        int lp = left - 1;
        int rp = right;

        while(true) {
            // biến chạy lp chạy từ index=0 qua phải, nếu gặp idNode > idPivot thì dừng lại
            while (
                    list.searchPosition(++lp).getData().getIdProduct().compareTo(pivot.getData().getIdProduct()) < 0
            ) {}
            // biến chạy rp chạy từ index=right-1 qua trái, nếu gặp idNode < idPivot hoặc rp=0 thì dừng lại
            while (rp > 0
                    && list.searchPosition(--rp).getData().getIdProduct().compareTo(pivot.getData().getIdProduct()) > 0
            ) {}

            // nếu biến chạy lp và rp đã gặp nhau thì thoát vòng lặp
            if(lp >= rp) {break;}
            else {
                // nếu 2 biến chạy chưa gặp nhau thì đổi data của 2 node mà 2 biến chạy đang dừng lại
                // sau khi đổi chỗ xong thì tiếp tục chạy tiếp (thực hiện lại vòng lặp while)
                list.swap(lp,rp);
            }
        }

        // nếu biến chạy lp và rp đã gặp nhau thì đổi chỗ giá trị trong node lp với node right
        list.swap(lp, right);

        // trả về vị trí mới của node pivot để thực hiện tách mảng
        return lp;
    }

    public void printToConsoleAndFile(File file, String string) {
        System.out.print(string);
        writeToFile(file, string, true);
    }
}
