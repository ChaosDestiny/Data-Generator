package generatedata.entity.location;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;

public class GenerateLocation {
    
    public JsonArray result;

    private JsonArray getName(List<String> names, String tail, int length) {
        JsonArray res = new JsonArray();
        for (int i = 0; i < length; ++i) {
            Random random = new Random();
            int idx = random.nextInt(names.size());
            String so = Integer.toString(random.nextInt(500) + 1);

            String name = "Số " + so + " " + names.get(idx) + ", " + tail;
            String country = "Việt Nam";

            JsonObject obj = new JsonObject();
            obj.addProperty("name", name);
            obj.addProperty("description", "");
            obj.addProperty("country", country);

            res.add(obj);
        }
        return res;
    }

    JsonArray setId(JsonArray arr) {
        for (int i = 0; i < arr.size(); ++i) {
            arr.get(i).getAsJsonObject().addProperty("id", "loc" + Integer.toString(i));
            arr.get(i).getAsJsonObject().addProperty("entity_name", "location");
        }

        return arr;
    }

    public void dumpLocation2Json(String filePath, int length) {
        ParseData parse = new ParseData();
        List<String> hanois = parse.getDataFromTxt("data/location/hanoi.txt");
        List<String> hochiminhs = parse.getDataFromTxt("data/location/hochiminh.txt");
    
        Random random = new Random();
        int lenHanoi = random.nextInt(length);
        int lenHochiminh = length - lenHanoi;

        JsonArray res = new JsonArray();
        res.addAll(getName(hanois, "Hà Nội", lenHanoi));
        res.addAll(getName(hochiminhs, "Hồ Chí Minh", lenHochiminh));

        res = setId(res);
        result = res;

        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
            
            Gson gson = new Gson();
            out.write(gson.toJson(res));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}