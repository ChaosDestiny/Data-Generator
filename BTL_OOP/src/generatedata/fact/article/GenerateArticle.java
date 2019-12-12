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

    JsonArray genArticle(List<String> article, int length) {
        JsonArray res = new JsonArray();
        Random random = new Random();

//        int length = facts.size();
        for (int i = 0; i < length; ++i) {
//            JsonObject fact = facts.get(i).getAsJsonObject();
            int idxArt = random.nextInt(article.size());
            long ms = (long) idxArt * 280219990 + (long) random.nextInt(100000) * 280219990;

            String link = article.get(idxArt) + Integer.toString(random.nextInt(1000000000));
            String title = "";
            String date = RandomDate.genData(ms);
            String articleId = "art" + Integer.toString(i);

            // Có vẻ ổn áp r đấy
            // hinhf nhuw chưa, tại vì anh phải chạy article trước chứ
            // Lý thuyết là thế cơ mà nếu thầy k đọc code gen thì ok;v để e thử
            // e vẫn d dhieeru lắm :v a chỉ cần tạo article xong rồi có cái biến len set từ đầu cho bọn article r
            // Thế chỉnh trong cái fact cũng thốn
            // a chỉnh trong article thôi, fact em làm cho
            // tại cái article lắm biến quá :v a tạo ra e chả biết gì
            // k sao a cứ run thử xem tạo ổn k đi chứ e vừa chỉnh cái kia là ok  rồi Ok
            JsonObject obj = new JsonObject();
            obj.addProperty("articleId", articleId);
//            obj.addProperty("factId", factId);
            obj.addProperty("link", link);
            obj.addProperty("title", title);
            obj.addProperty("publicationDate", date);

            res.add(obj);
        }

        return res;
    }

    public void generate(int length) {
        ParseData parse = new ParseData();
        List<String> articles = parse.getDataFromTxt("data/article/article.txt");

        result.addAll(genArticle(articles, length));
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