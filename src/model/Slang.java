package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

    public void printOut(String path){
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(path);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            for (String key : slangs.keySet()) {
                String value = slangs.get(key);
                bw.write(key + "`" + value + "\n");
            }
            bw.flush();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                bw.close();
                osw.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String searchByKey(String key) {
        return slangs.get(key);
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

    public String removeSlangByKey(String key){
        return slangs.remove(key);
    }

    public void reset(String path){
        slangs.clear();
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
                arr = line.split("`");
                if (arr.length < 2) {
                    slangs.put(preKey, preValue + "|" + arr[0]);
                    preValue = preValue + "|" + arr[0];
                } else {
                    slangs.put(arr[0], arr[1]);
                    preKey = arr[0];
                    preValue = arr[1];
                }
            }
        } catch (IOException ex) {
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

    public String[] getRandom(){
        ArrayList<String> keys = new ArrayList(slangs.keySet());
        Random r = new Random();
        String[] res = new String[2];
        res[0] = keys.get(r.nextInt(keys.size()));
        res[1] = slangs.get(res[0]);
        return res;
    }
}
