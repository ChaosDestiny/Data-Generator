package app;

import java.util.Scanner;

import generatedata.entity.agreement.GenerateAgreement;
import generatedata.entity.country.GenerateCountry;
import generatedata.entity.event.GenerateEvent;
import generatedata.entity.location.GenerateLocation;
import generatedata.entity.organization.GenerateOrganization;
import generatedata.entity.vietnamesenamedb.GeneratePerson;
import generatedata.fact.article.GenerateArticle;
import generatedata.fact.fact.GenerateFact;

public class GeneDataJson {
	public static void run() {
		String[] str = {"person", "location", "country", "agreement","event", "organization", "article" , "fact", };
		int[] n = new int[8];
		int num = 0;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 8; i++) {
			System.out.println("Nhập số lượng " + str[i] + ": ");
			n[i] = scanner.nextInt();
			num += n[i];
		}
		long s1 = System.currentTimeMillis();
        GeneratePerson genPer = new GeneratePerson();
        genPer.dumpPerson2Json("json/person.json", n[0]);

        GenerateLocation genLo = new GenerateLocation();
        genLo.dumpLocation2Json("json/location.json", n[1]);

        GenerateCountry genCt = new GenerateCountry();
        genCt.dumpCountry2Json("json/country.json", n[2]);

        GenerateAgreement genAgg = new GenerateAgreement();
        genAgg.dump2Json("json/agreement.json", n[3]);

        GenerateEvent genEvent = new GenerateEvent();
        genEvent.dump2Json("json/event.json", n[4]);

        GenerateOrganization genOr = new GenerateOrganization();
        genOr.dump2Json("json/organization.json", n[5]);
        
        GenerateArticle genArt = new GenerateArticle();
        genArt.generate(n[6]);
        genArt.dump2Json("json/article.json");
        
        GenerateFact genFact = new GenerateFact();
        genFact.generate(n[7], n[6]);
        genFact.dump2Json("json/fact.json");

         
        
        long s2 = System.currentTimeMillis();
        System.out.println("Số lượng thực thể: " + (num - n[7]));
        System.out.println("Số lượng quan hệ: " + n[7]);
        System.out.println("Thời gian sinh dữ liệu: " + (s2 - s1));
	}
}
