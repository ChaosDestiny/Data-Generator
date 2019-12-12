package app;

import generatedata.entity.aggrement.GenerateAggrement;
import generatedata.entity.country.GenerateCountry;
import generatedata.entity.event.GenerateEvent;
import generatedata.entity.location.GenerateLocation;
import generatedata.entity.organization.GenerateOrganization;
import generatedata.entity.vietnamesenamedb.GeneratePerson;
import generatedata.fact.article.GenerateArticle;
import generatedata.fact.fact.GenerateFact;

public class Insertion {
	public static void main(String[] args)
    {
        GeneratePerson genPer = new GeneratePerson();
        genPer.dumpPerson2Json("json/person.json", 10);

        GenerateLocation genLo = new GenerateLocation();
        genLo.dumpLocation2Json("json/location.json", 10);

        GenerateCountry genCt = new GenerateCountry();
        genCt.dumpCountry2Json("json/country.json", 10);

        GenerateAggrement genAgg = new GenerateAggrement();
        genAgg.dump2Json("json/aggrement.json", 20);

        GenerateEvent genEvent = new GenerateEvent();
        genEvent.dump2Json("json/event.json", 10);

        GenerateOrganization genOr = new GenerateOrganization();
        genOr.dump2Json("json/organization.json", 5);

        GenerateFact genFact = new GenerateFact();
        genFact.generate(10);
        genFact.dump2Json("json/fact.json");

        GenerateArticle genArt = new GenerateArticle();
        genArt.generate("json/fact.json");
        genArt.dump2Json("json/article.json"); 

    }
}
