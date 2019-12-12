package generatedata.fact.fact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;
import generatedata.utilites.RandomDate;

public class GenerateFact {

    public JsonArray result;
    private List<String> relationship;
    private List<String> subjectPath, objectPath;

    public GenerateFact() {
        this.result = new JsonArray();
        this.relationship = new ArrayList<String>(){
            {
                add("Gặp gỡ");
                add("Đi chơi cùng");
                add("Ủng hộ");
                add("Ủng hộ");
                add("Phản đối");
                add("Tổ chức");
                add("Kết hợp");
                add("Đàm phán");
                add("Liên minh");
                add("Tham gia");
                add("Đi");
                add("Chiến tranh");
                add("Gặp gỡ");
                add("Tổ chức tại");
                add("Đi");
            }
        };
        
        this.subjectPath = new ArrayList<String>() {
            {
                add("person.json");
                add("person.json");
                add("organization.json");
                add("country.json");
                add("country.json");
                add("organization.json");
                add("person.json");
                add("organization.json");
                add("organization.json");
                add("person.json");
                add("person.json");
                add("country.json");
                add("person.json");
                add("event.json");
                add("person.json");
            }
        };

        this.objectPath = new ArrayList<String>() {
            {
                add("person.json");
                add("person.json");
                add("aggrement.json");
                add("aggrement.json");
                add("aggrement.json");
                add("event.json");
                add("organization.json");
                add("organization.json");
                add("organization.json");
                add("organization.json");
                add("event.json");
                add("event.json");
                add("person.json");
                add("location.json");
                add("location.json");
            }
        };
    }

    public List<String> getRelationship() {
        return relationship;
    }

    public List<String> getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(List<String> objectPath) {
        this.objectPath = objectPath;
    }

    public List<String> getSubjectPath() {
        return subjectPath;
    }

    public void setSubjectPath(List<String> subjectPath) {
        this.subjectPath = subjectPath;
    }

    public void setRelationship(List<String> relationship) {
        this.relationship = relationship;
    }

    JsonArray genFact(JsonArray subjects, JsonArray objects, String relationship, int length) {
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
            obj.addProperty("object_id", objectId);
            obj.addProperty("extract_time", formatter.format(date));
            obj.addProperty("time", time);

            res.add(obj);
        }
        return res;
    }

    public void generate(int length) {
        ParseData parse = new ParseData();

        int nbRela = subjectPath.size();
        for (int i = 0; i < nbRela; ++i) {
            JsonArray subjects = parse.getDataFromJson(subjectPath.get(i));
            JsonArray objects = parse.getDataFromJson(objectPath.get(i));
            String rela = relationship.get(i);

            JsonArray res = genFact(subjects, objects, rela, length);
            result.addAll(res);
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