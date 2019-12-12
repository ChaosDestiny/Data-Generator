package generatedata.entity.country;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;

public class GenerateCountry {

    public JsonArray result;

    JsonArray genCountry(List<String> countries, List<String> capitals, List<String> description, int length) {
        JsonArray res = new JsonArray();
        for (int i = 0; i < length; ++i) {
            Random random = new Random();
            int idxCountry = random.nextInt(countries.size());
            int idxCapital = random.nextInt(capitals.size());
            int idxDesc = random.nextInt(description.size());

            String country = countries.get(idxCountry);
            String capital = capitals.get(idxCapital);
            String desc = description.get(idxDesc);

            JsonObject obj = new JsonObject();
            obj.addProperty("name", country);
            obj.addProperty("description", desc);
            obj.addProperty("capital", capital);

            res.add(obj);
        }

        return res;
    }

    JsonArray setId(JsonArray arr) {
        for (int i = 0; i < arr.size(); ++i) {
            arr.get(i).getAsJsonObject().addProperty("entityId", "ctry" + Integer.toString(i));
            arr.get(i).getAsJsonObject().addProperty("entityName", "country");
        }

        return arr;
    }

    public void dumpCountry2Json(String filePath, int length) {
        ParseData parse = new ParseData();
        List<String> countries = parse.getDataFromTxt("data/country/countries.txt");
        List<String> description = parse.getDataFromTxt("data/country/description.txt");
        List<String> capitals = parse.getDataFromTxt("data/country/capitals.txt");

        JsonArray res = new JsonArray();
        for (int i = 0; i < Math.min(length, countries.size()); ++i) {
            Random random = new Random();
            String country = countries.get(i);
            int idxDesc = random.nextInt(description.size());
            String desc = description.get(idxDesc);
            String capital = capitals.get(i);

            JsonObject obj = new JsonObject();
            obj.addProperty("name", country);
            obj.addProperty("description", desc);
            obj.addProperty("capital", capital);

            res.add(obj);
        }

        if (length > countries.size()) {
            length = length - countries.size();
            res.addAll(genCountry(countries, capitals, description, length));
        }
        res = setId(res);
        result = res;
        
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
            
            Gson gson = new Gson();
            out.write(gson.toJson(res));
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}