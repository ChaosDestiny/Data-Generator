package generatedata.fact.article;

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

public class GenerateArticle {

    public JsonArray result;

    public GenerateArticle() {
        result = new JsonArray();
    }

    JsonArray genArticle(List<String> article, JsonArray facts) {
        JsonArray res = new JsonArray();
        Random random = new Random();

        int length = facts.size();
        for (int i = 0; i < length; ++i) {
            JsonObject fact = facts.get(i).getAsJsonObject();
            int idxArt = random.nextInt(article.size());
            long ms = (long) idxArt * 280219990 + (long) random.nextInt(100000) * 280219990;

            String link = article.get(idxArt) + Integer.toString(random.nextInt(1000000000));
            String title = "";
            String date = RandomDate.genData(ms);
            String factId = fact.get("id").getAsString();

            JsonObject obj = new JsonObject();
            obj.addProperty("article_id", "art" + Integer.toString(i));
            obj.addProperty("fact_id", factId);
            obj.addProperty("link", link);
            obj.addProperty("title", title);
            obj.addProperty("publication_date", date);

            res.add(obj);
        }

        return res;
    }

    public void generate(String factPath) {
        ParseData parse = new ParseData();
        List<String> articles = parse.getDataFromTxt("data/article/article.txt");
        JsonArray facts = parse.getDataFromJson(factPath);

        result.addAll(genArticle(articles, facts));
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