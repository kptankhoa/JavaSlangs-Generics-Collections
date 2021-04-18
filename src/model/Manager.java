package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private final Slang slangs;
    private final ArrayList<String> searchHistory;

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

    public Manager(Slang slangs, String searchHistorypath) {
        super();
        this.slangs = slangs;
        searchHistory = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(searchHistorypath)));
            String line;
            while((line = br.readLine()) != null){
                searchHistory.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean checkAnsInput(String str) {
        if (str.length() > 1)
            return false;
        int c = str.charAt(0);
        return c >= 65 && c <= 68;
    }

    public void saveSearchHistory(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
            searchHistory.forEach(data -> {
                try {
                    bw.write(data + "\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchBySlang() {
        System.out.print("Nhập slang cầm tìm: ");
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String str = br.readLine();
            String res = slangs.searchByKey(str);
            if (res != null) {
                System.out.println("Nghĩa từ cần tìm: " + res);
            } else {
                System.out.println("Không tìm thấy!");
            }
            if (searchHistory.contains(str)) {
                searchHistory.remove(str);
            }
            searchHistory.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByDefinition() {
        System.out.print("Nhập từ cầm tìm:");
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
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
        System.out.print("Lịch sử tìm kiếm: ");
        System.out.println(searchHistory.toString());
    }

    public void addSlang() {
        BufferedReader br = null;
        InputStreamReader isr = null;
        String slang, def;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            System.out.print("Nhập slang bạn muốn thêm: ");
            slang = br.readLine();
            System.out.print("Nhập nghĩa của slang bạn muốn thêm: ");
            def = br.readLine();
            if (slangs.searchByKey(slang) != null) {
                System.out.println("Slang đã tồn tại, bạn có muốn tiếp tục thêm không? (1):tiếp tục, (0):hủy");
                System.out.print("Nhập lựa chọn: ");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0) {
                    System.out.println("Đang hủy.....");
                    return;
                } else if (choice == 1) {
                    System.out.println("Bạn muốn thực hiện ghi đè slang đã tồn tại(1) hay thêm nghĩa cho slang(2)?");
                    System.out.print("Nhập lựa chọn: ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice == 1) {
                        String res = slangs.addSlang(slang, def);
                        if (res != null) {
                            System.out.println("Thêm thành công!");
                        } else {
                            System.out.println("Thêm không thành công!");
                        }
                        return;
                    } else if (choice == 2) {
                        String res = slangs.addSlang(slang, slangs.searchByKey(slang) + " | " + def);
                        if (res != null) {
                            System.out.println("Thêm thành công!");
                        } else {
                            System.out.println("Thêm không thành công!");
                        }
                        return;
                    }
                }
            } else {
                String res = slangs.addSlang(slang, def);
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

    public void editSlang() {
        BufferedReader br = null;
        InputStreamReader isr = null;
        String slang;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            System.out.print("Nhập slang bạn muốn chỉnh sửa: ");
            slang = br.readLine();
            if (slangs.searchByKey(slang) != null) {
                System.out.print("Nhập định nghĩa mới: ");
                String newDef = br.readLine();
                if (slangs.addSlang(slang, newDef) != null) {
                    System.out.println("Chỉnh sửa slang thành công!");
                } else {
                    System.out.println("Chỉnh sửa slang không thành công!");
                }
            } else {
                System.out.println("Slang không tồn tại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteSlang() {
        BufferedReader br = null;
        InputStreamReader isr = null;
        String slang;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            System.out.print("Nhập slang bạn muốn xóa: ");
            slang = br.readLine();
            if (slangs.searchByKey(slang) != null) {
                System.out.println("Bạn có chắc chắn muốn thực hiện xóa không? (1):Có / (0):Không");
                System.out.print("Nhập lựa chọn: ");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 1) {
                    if (slangs.removeSlangByKey(slang) != null) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Xóa không thành công!");
                    }
                } else if (choice == 0) {
                    System.out.println("Đang hủy.....");
                }
            } else {
                System.out.println("Slang không tồn tại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSlangs() {
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            System.out.println("Bạn có chắc chắn muốn reset danh sách slangs không? (1):Có / (0):Không");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(br.readLine());
            if (choice == 1) {
                slangs.reset("src/data/slang-backup.txt");
                System.out.println("Reset thành công!");
            } else if (choice == 0) {
                System.out.println("Đang hủy.....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRandomSlang() {
        String[] slang = slangs.getRandom();
        System.out.println("Slang word random: " + slang[0]);
        System.out.println("Definition: " + slang[1]);
    }

    public void genSlangQuestion() {
        ArrayList<String[]> questionData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            questionData.add(slangs.getRandom());
        }
//        questionData.forEach((data) -> System.out.println(data[0] + " - " + data[1]));
        int chosen = 0;
        Random r = new Random();
        int correctAns = r.nextInt(questionData.size());
//        System.out.println(correctAns);
        System.out.println("Chọn định nghĩa đúng cho \"" + questionData.get(correctAns)[0] + "\":");
        for (int i = 0; i < questionData.size(); i++) {
//            String[] ans = questionData.get(i);
            System.out.print((char) (65 + i));
            System.out.println(". " + questionData.get(i)[1]);
        }
        System.out.print("Nhập đáp án đúng (A/B/C/D): ");
        try {
            String str = br.readLine();
            while (!checkAnsInput(str)) {
                System.out.print("Nhập không hợp lệ! Vui lòng nhập lại (A/B/C/D): ");
                str = br.readLine();
            }
            chosen = str.charAt(0);
//            System.out.println(chosen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((chosen - 65) == correctAns) {
            System.out.println("Đáp án đúng!");
        } else {
            System.out.println("Đáp án sai! Đáp án đúng là " + (char) (correctAns + 65) + ". " + questionData.get(correctAns)[1]);
        }
    }

    public void genDefQuestionn() {
        ArrayList<String[]> questionData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            questionData.add(slangs.getRandom());
        }
//        questionData.forEach((data) -> System.out.println(data[0] + " - " + data[1]));
        int chosen = 0;
        Random r = new Random();
        int correctAns = r.nextInt(questionData.size());
//        System.out.println(correctAns);
        System.out.println("Chọn slang đúng cho \"" + questionData.get(correctAns)[1] + "\":");
        for (int i = 0; i < questionData.size(); i++) {
//            String[] ans = questionData.get(i);
            System.out.print((char) (65 + i));
            System.out.println(". " + questionData.get(i)[0]);
        }
        System.out.print("Nhập đáp án đúng (A/B/C/D): ");
        try {
            String str = br.readLine();
            while (!checkAnsInput(str)) {
                System.out.print("Nhập không hợp lệ! Vui lòng nhập lại (A/B/C/D): ");
                str = br.readLine();
            }
            chosen = str.charAt(0);
//            System.out.println(chosen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((chosen - 65) == correctAns) {
            System.out.println("Đáp án đúng!");
        } else {
            System.out.println("Đáp án sai! Đáp án đúng là " + (char) (correctAns + 65) + ". " + questionData.get(correctAns)[0]);
        }
    }

}







