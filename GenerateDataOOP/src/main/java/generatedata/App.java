package generatedata;

import generatedata.entity.location.GenerateLocation;
import generatedata.entity.organization.GenerateOrganization;
import generatedata.entity.vietnamesenamedb.GeneratePerson;
import generatedata.fact.article.GenerateArticle;
import generatedata.fact.fact.GenerateFact;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

        GenerateFact genFact = new GenerateFact();
        genFact.generate(10);
        genFact.dump2Json("fact.json");

        GenerateArticle genArt = new GenerateArticle();
        genArt.generate("fact.json");
        genArt.dump2Json("article.json"); 

    }
}
