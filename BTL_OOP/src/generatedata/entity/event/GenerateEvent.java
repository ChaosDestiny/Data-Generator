package generatedata.entity.event;

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

public class GenerateEvent {
    public JsonArray result;

    JsonArray genEvent(List<String> events, List<String> descriptions, int length) {
        JsonArray res = new JsonArray();

        for (int i = 0; i < length; ++i) {
            Random random = new Random();
            int idxE = random.nextInt(events.size());
            int idxDesc = random.nextInt(descriptions.size()); 
            long ms = ((long) idxE * 280219990 + (long) idxDesc * 280219990 + (long) random.nextInt(idxE + idxDesc + 10000) * 280219990);
            String date = RandomDate.genData(ms);

            JsonObject obj = new JsonObject();
            obj.addProperty("name", events.get(idxE));
            obj.addProperty("description", descriptions.get(idxDesc));
            obj.addProperty("time", date);

            res.add(obj);

        }

        return res;
    }

    JsonArray setId(JsonArray arr) {
        for (int i = 0; i < arr.size(); ++i) {
            arr.get(i).getAsJsonObject().addProperty("id", "event" + Integer.toString(i));
            arr.get(i).getAsJsonObject().addProperty("entity_name", "event");
        }

        return arr;
    }

    public void dump2Json(String filePath, int length) {
        ParseData parse = new ParseData();
        List<String> festivals = parse.getDataFromTxt("data/event/festival.txt");
        List<String> description = parse.getDataFromTxt("data/event/description.txt");
    
        JsonArray res = genEvent(festivals, description, length);
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