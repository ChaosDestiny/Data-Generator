package generatedata.entity.organization;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;
import generatedata.utilites.RandomDate;

public class GenerateOrganization {
    public JsonArray result;

    JsonArray genOrganization(List<String> organizations, int length) {
        JsonArray res = new JsonArray();

        for (int i = 0; i < length; ++i) {
            Random random = new Random();
            int idxOr = random.nextInt(organizations.size());
            long ms = ((long) idxOr * 280219990 + (long) random.nextInt(idxOr + 10500) * 280219990);
            String date = RandomDate.genData(ms);

            JsonObject obj = new JsonObject();
            obj.addProperty("name", organizations.get(idxOr));
            obj.addProperty("description", "");
            obj.addProperty("headquater", "");
            obj.addProperty("founding_date", date);

            res.add(obj);
        }

        return res;
    }

    JsonArray setId(JsonArray arr) {
        for (int i = 0; i < arr.size(); ++i) {
            arr.get(i).getAsJsonObject().addProperty("id", "org" + Integer.toString(i));
            arr.get(i).getAsJsonObject().addProperty("entity_name", "organization");
        }

        return arr;
    }

    public void dump2Json(String filePath, int length) {
        ParseData parse = new ParseData();
        List<String> organizations = parse.getDataFromTxt("data/organization/organizations.txt");

        JsonArray res = genOrganization(organizations, length);
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