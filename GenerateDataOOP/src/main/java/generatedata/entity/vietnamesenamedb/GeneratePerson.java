package generatedata.entity.vietnamesenamedb;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import generatedata.utilites.ParseData;

public class GeneratePerson {
    public JsonArray result;

    private JsonArray generateNewPerson(List<String> ho, List<String> ten, List<String> desc, List<String> jobs, String gender, int length) {
        JsonArray res = new JsonArray();

        for (int i = 0; i < length; ++i) {
            Random random = new Random();
            int idxHo = random.nextInt(ho.size());
            int idxTen = random.nextInt(ten.size());
            int idxDesc = random.nextInt(desc.size());
            int idxJob = random.nextInt(jobs.size());
            int age = random.nextInt(60) + 13;

            String fullName = ho.get(idxHo) + " " + ten.get(idxTen);
            List<String> lsWord = Arrays.asList(fullName.split(" "));
            int szLsWord = lsWord.size();

            JsonObject newPerson = new JsonObject();
            newPerson.addProperty("first_name", lsWord.get(szLsWord - 1));
            // newPerson.addProperty("last_name", String.join(" ", lsWord.subList(0, szLsWord - 1)));
            newPerson.addProperty("full_name", fullName);
            // newPerson.addProperty("last_name_group", lsWord.get(0));
            newPerson.addProperty("gender", gender);
            newPerson.addProperty("description", desc.get(idxDesc));
            newPerson.addProperty("job", jobs.get(idxJob));
            newPerson.addProperty("age", age);
            

            res.add(newPerson);
        }

        return res;
    }

    JsonArray setId(JsonArray arr) {
        for (int i = 0; i < arr.size(); ++i) {
            arr.get(i).getAsJsonObject().addProperty("id", "per" + Integer.toString(i));
            arr.get(i).getAsJsonObject().addProperty("entity_name", "person");
        }

        return arr;
    }

    public void dumpPerson2Json(String filePath, int length) {
        ParseData parse = new ParseData();
        List<String> boyName = parse.getDataFromTxt("data/vietnamese-namedb/boy.txt");
        List<String> girlName = parse.getDataFromTxt("data/vietnamese-namedb/girl.txt");
        List<String> girlNameOneWord = parse.getDataFromTxt("data/vietnamese-namedb/girl_one_word.txt");
        List<String> ho = parse.getDataFromTxt("data/vietnamese-namedb/ho.txt");
        List<String> description = parse.getDataFromTxt("data/vietnamese-namedb/description.txt");
        List<String> jobs = parse.getDataFromTxt("data/vietnamese-namedb/jobs.txt");

        JsonArray arr = parse.getDataFromJson("data/vietnamese-namedb/uit_member.json");
        JsonArray res = new JsonArray();


        for (int i = 0; i < Math.min(arr.size(), length); ++i) {
            JsonObject obj = arr.get(i).getAsJsonObject();
            obj.remove("gender");
            obj.remove("last_name");
            obj.remove("last_name_group");
            if (girlNameOneWord.contains(obj.get("first_name").getAsString())) {
                obj.addProperty("gender", "0");
            } else {
                obj.addProperty("gender", "1");
            }

            Random random = new Random();
            int idxDesc = random.nextInt(description.size());
            int idxJob = random.nextInt(jobs.size());
            int age = random.nextInt(60) + 13;

            obj.addProperty("description", description.get(idxDesc));
            obj.addProperty("job", jobs.get(idxJob));
            obj.addProperty("age", age);
            res.add(obj);
        }

        if (res.size() < length) {
            length = length - res.size();
            int maleLength = length / 2;
            int femaleLength = length - maleLength;

            for (int i = 0; i < maleLength; ++i) {
                res.addAll(generateNewPerson(ho, boyName, description, jobs, "1", maleLength));
            }

            for (int i = 0; i < maleLength; ++i) {
                res.addAll(generateNewPerson(ho, girlName, description, jobs, "0", femaleLength));
            }
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