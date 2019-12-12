package generatedata;

import generatedata.entity.location.GenerateLocation;
import generatedata.entity.organization.GenerateOrganization;
import generatedata.entity.vietnamesenamedb.GeneratePerson;
import generatedata.fact.article.GenerateArticle;
import generatedata.fact.fact.GenerateFact;

import java.util.ArrayList;
import java.util.List;

import generatedata.entity.aggrement.GenerateAggrement;
import generatedata.entity.country.GenerateCountry;
import generatedata.entity.event.GenerateEvent;

public class App 
{
    public static void main(String[] args)
    {
        GeneratePerson genPer = new GeneratePerson();
        genPer.dumpPerson2Json("person.json", 10);

        GenerateLocation genLo = new GenerateLocation();
        genLo.dumpLocation2Json("location.json", 10);

        GenerateCountry genCt = new GenerateCountry();
        genCt.dumpCountry2Json("country.json", 10);

        GenerateAggrement genAgg = new GenerateAggrement();
        genAgg.dump2Json("aggrement.json", 20);

        GenerateEvent genEvent = new GenerateEvent();
        genEvent.dump2Json("event.json", 10);

        GenerateOrganization genOr = new GenerateOrganization();
        genOr.dump2Json("organization.json", 5);

        List<String> relationships = new ArrayList<String>(){
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
        

        List<String> subjects = new ArrayList<String>() {
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
                add("person.josn");
                add("event.json");
                add("person.json");
            }
        };

        List<String> objects = new ArrayList<String>() {
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
                add("person.josn");
                add("location.json");
                add("location.json");
            }
        };

        GenerateFact genFact = new GenerateFact();
        for (int i = 0; i < relationships.size(); ++i) {
            
            genFact.setRelationship(relationships.get(i));
            genFact.setSubjectPath(subjects.get(i));
            genFact.setObjectPath(objects.get(i));

            System.out.println(i);
            
            genFact.generate(10);
        }
        genFact.dump2Json("fact.json");


        GenerateArticle genArt = new GenerateArticle();
        genArt.generate("fact.json");
        genArt.dump2Json("article.json");
        
        

    }
}
