package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Slang {
    private HashMap<String, String> slangs;

    public Slang() {
        super();
        slangs = null;
    }

    public Slang(String path) {
        super();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            slangs = new HashMap<String, String>();
            String line = br.readLine();
            String[] arr;
            String preKey = "";
            String preValue = "";


            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                arr = line.split("`");
                if (arr.length < 2) {
//                    System.out.println(arr[0]);
                    slangs.put(preKey, preValue + "|" + arr[0]);
                    preValue = preValue + "|" + arr[0];
                } else {
                    slangs.put(arr[0], arr[1]);
                    preKey = arr[0];
                    preValue = arr[1];
                }
            }

//            System.out.println(slangs.toString());

        } catch (IOException ex) {
//            Log.e(ex.toString());
            System.out.printf(ex.toString());
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    public String searchByKey(String slang) {
        return slangs.get(slang);
    }

    public String searchByValue(String def) {
        ArrayList<String> res = new ArrayList<String>();

        for (String key : slangs.keySet()) {
            if (slangs.get(key).contains(def)) {
                res.add(key + ": " + slangs.get(key));
            }
        }
        if (res.size() < 1) {
            return null;
        }
        return String.join(", ", res);
    }

    public String addSlang(String key, String value){
        return slangs.put(key, value);
    }
}
