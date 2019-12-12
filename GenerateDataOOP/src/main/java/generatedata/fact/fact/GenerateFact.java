package generatedata.fact.fact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;
import generatedata.utilites.RandomDate;

public class GenerateFact {

    public JsonArray result;
    private String relationship;
    private String subjectPath, objectPath;

    public GenerateFact() {
        result = new JsonArray();
    }

    public String getRelationship() {
        return relationship;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    public String getSubjectPath() {
        return subjectPath;
    }

    public void setSubjectPath(String subjectPath) {
        this.subjectPath = subjectPath;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    JsonArray genFact(JsonArray subjects, JsonArray objects, int length) {
        JsonArray res = new JsonArray();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0 ; i < length; ++i) {
            Random random = new Random();
            int idxSub = random.nextInt(subjects.size());
            int idxObj = random.nextInt(objects.size());
            Date date = new Date();
            long ms = ((long) idxSub * 280219990 + (long) idxObj * 280219990 + (long) random.nextInt(idxSub + idxObj + 10000) * 280219990);
            String time = RandomDate.genData(ms);
            String subjectId = subjects.get(idxSub).getAsJsonObject().get("id").getAsString();
            String objectId = objects.get(idxObj).getAsJsonObject().get("id").getAsString();

            JsonObject obj = new JsonObject();
            obj.addProperty("id", "fact" + Integer.toString(i));
            obj.addProperty("relationship", relationship);
            obj.addProperty("subject_id", subjectId);
            obj.addProperty("object", objectId);
            obj.addProperty("extract_time", formatter.format(date));
            obj.addProperty("time", time);

            res.add(obj);
        }
        return res;
    }

    public void generate(int length) {
        Gson gson = new Gson();
        ParseData parse = new ParseData();
        try {
            JsonArray subjects = parse.getDataFromJson(subjectPath);
            JsonArray objects = parse.getDataFromJson(objectPath);

            JsonArray res = genFact(subjects, objects, length);
            result.addAll(res);

        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    public void dump2Json(String filePath) {
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
            
            Gson gson = new Gson();
            out.write(gson.toJson(result));
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}