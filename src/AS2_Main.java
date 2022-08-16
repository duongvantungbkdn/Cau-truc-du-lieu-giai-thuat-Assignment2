import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AS2_Main {
    public static void main(String[] args) {

        // tạo 1 đối tượng OperationToProduct mới tên là op
        // op để lấy ra các method thao tác với chương trình
        OperationToProduct op = new OperationToProduct();

        // tạo 1 danh sách liên kết rỗng chứa kiểu dữ liêu Product
        MyList<Product> list = new MyList();

        // tạo file để lưu trữ output consolse
        File outputConsole = new File("console_output.txt");

        int choice;
        do{
            choice = op.menu(outputConsole);
            switch (choice) {
                case 0:
                    // exit
                    op.printToConsoleAndFile(outputConsole, "\nGoodbye, have a nice day!!!\n");
                    break;
                case 1:
                    //"1. Load data from file and display"
                    // xóa danh sách đang tồn tại
                    list.clearList();
                    // đọc danh sách từ file
                    op.getAllItemsFromFile("data.txt",list,outputConsole);
                    // hiển thị ra danh sách
                    op.displayAll(list,outputConsole);
                    break;
                case 2:
                    //"2. Input & add to the end."
                    Product product = op.createProduct(outputConsole);
                    op.addLast(list,product,outputConsole);
                    break;
                case 3:
                    //"3. Display data"
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\n- Please choose 1 to load products list from file." +
                                "\n- Or choose 2 or 11 to input a product to list.\n"
                        );
                    } else {
                        op.displayAll(list,outputConsole);
                    }
                    break;
                case 4:
                    //"4. Save product list to file."
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\nPlease choose 2 or 11 to input a product to list.\n");
                    } else {
                        op.writeAllItemsToFile("data.txt", list,outputConsole);
                    }
                    break;
                case 5:
                    //"5. Search by ID"
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\nPlease choose 1 to load products list from file.\n"
                        );
                    } else {
                        op.searchByCode(list, outputConsole);
                    }
                    break;
                case 6:
                    //"6. Delete by ID"
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\nPlease choose 1 to load products list from file.\n"
                        );
                    } else {
                        op.deleteByCode(list,outputConsole);
                    }
                    break;
                case 7:
                    //"7. Sort by ID."
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\nPlease choose 1 to load products list from file.\n"
                        );
                    } else {
                        op.quikSortList(list,0,list.getSize()-1);
                        op.printToConsoleAndFile(outputConsole,"Sort list successfully.\n");
                    }
                    break;
                case 8:
                    //"8. Convert to Binary"
                    if(list.isEmpty()) {
                        op.printToConsoleAndFile(outputConsole, "Products list is empty." +
                                "\nPlease choose 1 to load products list from file.\n"
                        );
                    } else {
                        // lấy số lượng của sản phẩm đầu tiên trong danh sách
                        Node<Product> head = list.getHead();
                        int quantityFirst = head.getData().getQuantity();
                        // dùng phương thức đệ quy binaryRecursion() trả về kết quả binary
                        int binary = op.binaryRecursion(quantityFirst);
                        op.printToConsoleAndFile(outputConsole, "Quantity of first product = "
                                + quantityFirst
                                + " => binary = " + binary + "\n");
                    }
                    break;
                case 9:
                    //"9. Load to stack and display"
                    // tạo 1 satck rỗng
                    MyStack<Product> myStack = new MyStack<>();

                    // load danh sách từ file vào stack
                    // và duyệt stack để hiển thị danh sách ra màn hình
                    op.getAllItemsFromFile("data.txt", myStack, outputConsole);
                    break;
                case 10:
                    //"10. Load to queue and display."
                    // tạo 1 queue rỗng
                    MyQueue<Product> myQueue = new MyQueue<>();

                    // load danh sách từ file vào queue
                    // và duyệt queue để hiển thị danh sách ra màn hình
                    op.getAllItemsFromFile("data.txt", myQueue, outputConsole);
                    break;
                case 11:
                    //"11. Input & add to the front."
                    Product product2 = op.createProduct(outputConsole);
                    op.addFirst(list,product2);
                    break;
                default:
                    op.printToConsoleAndFile(outputConsole, "Your choice is invalid. Please choose again!\n");
            }

            // khi choice = 0 thì thoát chương trình
        }while (choice != 0);
    }
}
