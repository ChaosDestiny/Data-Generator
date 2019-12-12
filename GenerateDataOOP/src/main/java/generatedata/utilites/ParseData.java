package generatedata.utilites;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class ParseData {

    public List<String> getDataFromTxt(String path) {
        ArrayList<String> res = new ArrayList<String>();

        try {
            FileInputStream stream = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;

            while ((line = in.readLine()) != null) {
                res.add(line);
            }
            in.close();

        } catch (Exception e) {

        }

        return res;
    }

    public JsonArray getDataFromJson(String path) {
        Gson gson = new Gson();
        FileInputStream stream;
        try {
            stream = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            return gson.fromJson(in, JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}