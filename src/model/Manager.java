package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Manager {
    private Slang slangs;
    private ArrayList<String> searchHistory;

    public Manager() {
        super();
        slangs = null;
        searchHistory = null;
    }

    public Manager(Slang slangs) {
        super();
        this.slangs = slangs;
        searchHistory = new ArrayList<>();
    }

    public void searchBySlang() {
        System.out.print("Nhập slang cầm tìm:");
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, "UTF-8");
            br = new BufferedReader(isr);
            String str = br.readLine();
            String res = slangs.searchByKey(str);
            if (res != null) {
                System.out.println("Nghĩa từ cần tìm: " + res);
            } else {
                System.out.println("Không tìm thấy!");
            }
            searchHistory.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                br.close();
//                isr.close();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }

        }
    }

    public void searchByDefinition() {
        System.out.print("Nhập từ cầm tìm:");
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, "UTF-8");
            br = new BufferedReader(isr);
            String str = br.readLine();
            String res = slangs.searchByValue(str);
            if (res == null) {
                System.out.println("Không có kết quả tìm kiếm!");
            } else {
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSearchHistory() {
        System.out.println("Lịch sử tìm kiếm: ");
        System.out.println(searchHistory.toString());
    }

    public void addSlang() {
        BufferedReader br = null;
        InputStreamReader isr = null;
        String slang, def;
        try {
            isr = new InputStreamReader(System.in, "UTF-8");
            br = new BufferedReader(isr);
            System.out.print("Nhập slang bạn muốn thêm: ");
            slang = br.readLine();
            System.out.print("Nhập nghĩa của slang bạn muốn thêm: ");
            def=br.readLine();
            if (slangs.searchByKey(slang) != null) {
                System.out.println("Slang đã tồn tại, bạn có muốn tiếp tục thêm không? (1): tiếp tục, (0): hủy");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0) {
                    return;
                } else if (choice == 1) {
                    System.out.println("Bạn muốn thực hiện override slang đã tồn tại(1) hay duplicate slang mới(2)?");
                    System.out.print("Nhập lựa chọn: ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice == 1) {
                        String res = slangs.addSlang(slang, def);
                        System.out.println(res);

                        if (res != null) {
                            System.out.println("Thêm thành công!");
                        } else {
                            System.out.println("Thêm không thành công!");
                        }
                        return;
                    } else if (choice == 2) {
                        System.out.println("Chức năng chưa được hỗ trợ");
                        return;
                    }
                }
            } else {
                String res = slangs.addSlang(slang, def);
                System.out.println(res);
                if (res != null) {
                    System.out.println("Thêm không thành công!");
                } else {
                    System.out.println("Thêm thành công!");
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
