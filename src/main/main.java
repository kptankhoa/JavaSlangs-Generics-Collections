package main;


import model.Manager;
import model.Slang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public static void main(String[] args) {
        int choice;
        Slang slangs = new Slang("src/data/slang.txt");
        Manager manager = new Manager(slangs);
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, "utf-8");
            br = new BufferedReader(isr);
            do {
                System.out.println("=========================================");
                System.out.println("1. Tìm kiếm theo slang word");
                System.out.println("2. Tìm kiếm theo definition");
                System.out.println("3. Hiển thị lịch sử tìm kiểm");
                System.out.println("4. Thêm slang word");
                System.out.println("5. Chỉnh sửa 1 slang word");
                System.out.println("6. Xóa 1 slang word");
                System.out.println("7. Reset danh sách slang words");
                System.out.println("8. Hiển thị slang word ngẫu nhiên");
                System.out.println("9. Đố vui (chọn definition đúng cho slang word)");
                System.out.println("10. Đố vui (chọn slang word đúng cho definition)");
                System.out.println("0: Thoát");
                System.out.print("Nhập lựa chọn: ");
                while (true) {
                    try {
                        choice = Integer.parseInt(br.readLine());
                        if (choice >= 0 && choice <= 10)
                            break;
                        System.out.print("Giá trị nhập không hợp lệ! Vui lòng chọn lại: ");
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.print("Giá trị nhập không hợp lệ! Vui lòng chọn lại: ");
                    }
                }
                if(choice == 0){
                    System.out.println("Thoát chương trình.....");
                    slangs.printOut("src/data/slang.txt");
                    break;
                }
                int next = -1;
                do {
                    switch (choice) {
                        case 1: //tim kiem theo slang
                            manager.searchBySlang();
                            break;
                        case 2: //tim kiem theo def
                            manager.searchByDefinition();
                            break;
                        case 3:
                            manager.showSearchHistory();
                            break;
                        case 4:
                            manager.addSlang();
                            break;
                        case 5:
                            manager.editSlang();
                            break;
                        case 6:
                            manager.deleteSlang();
                            break;
                        case 7:
                            manager.resetSlangs();
                        case 8:
                            manager.getRandomSlang();
                        default:
                            break;


                    }
                    System.out.print("Bạn có muốn tiếp tục(1) hay trờ về menu(0): ");
                    while(true) {
                        try {
                            next = Integer.parseInt(br.readLine());
                            if(next == 0 || next == 1)
                                break;
                            System.out.print("Giá trị nhập không hợp lệ! Vui lòng chọn lại: ");
                        } catch (Exception e) {
                            System.out.print("Giá trị nhập không hợp lệ! Vui lòng chọn lại: ");
                        }
                    }


                } while (next == 1);

            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }
}