package app;

import java.util.ArrayList;
import java.util.List;

import dao.AgreementDAO;
import dao.ArticleDAO;
import dao.CountryDAO;
import dao.EntityDAO;
import dao.EventDAO;
import dao.FactDAO;
import dao.LocationDAO;
import dao.OrganizationDAO;
import dao.PersonDAO;
import model.Agreement;
import model.Article;
import model.Country;
import model.Entity;
import model.Event;
import model.Fact;
import model.Location;
import model.Organization;
import model.Person;

public class Insertion {
	static final int maxBatch = 1000;
	public void person() {
		InsertionUtilities iu = new InsertionUtilities<Person>(Person.class);
		List<Person> p = iu.readJson("json/person.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		PersonDAO pd = new PersonDAO();
		ed.createBatch(e);
		pd.createBatch(p);
 	}
	
	public void organization() {
		InsertionUtilities iu = new InsertionUtilities<Organization>(Organization.class);
		List<Organization> p = iu.readJson("json/organization.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		OrganizationDAO pd = new OrganizationDAO();
		ed.createBatch(e);
		pd.createBatch(p);
	}
	
	public void location() {
		InsertionUtilities iu = new InsertionUtilities<Location>(Location.class);
		List<Location> p = iu.readJson("json/location.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		LocationDAO pd = new LocationDAO();
		ed.createBatch(e);
		pd.createBatch(p);
	}
	
	public void event() {
		InsertionUtilities iu = new InsertionUtilities<Event>(Event.class);
		List<Event> p = iu.readJson("json/event.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		EventDAO pd = new EventDAO();
		ed.createBatch(e);
		pd.createBatch(p);
	}
	
	public void country() {
		InsertionUtilities iu = new InsertionUtilities<Country>(Country.class);
		List<Country> p = iu.readJson("json/country.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		CountryDAO pd = new CountryDAO();
		ed.createBatch(e);
		pd.createBatch(p);
	}
	public void agreement() {
		InsertionUtilities iu = new InsertionUtilities<Agreement>(Agreement.class);
		List<Agreement> p = iu.readJson("json/agreement.json");
		List<Entity> e = new ArrayList<Entity>(p.size());
		for (int i = 0; i < p.size(); i++) {
			e.add(new Entity(p.get(i).getEntityId(), p.get(i).getName(),p.get(i).getDescription(),p.get(i).getEntityName()));
		}
		EntityDAO ed = new EntityDAO();
		AgreementDAO pd = new AgreementDAO();
		ed.createBatch(e);
		pd.createBatch(p);
	}
	public void article() {
		InsertionUtilities iu = new InsertionUtilities<Article>(Article.class);
		List<Article> p = iu.readJson("json/article.json");
		ArticleDAO ad = new ArticleDAO();
		ad.createBatch(p);
	}
	public void fact() {
		InsertionUtilities iu = new InsertionUtilities<Fact>(Fact.class);
		List<Fact> p = iu.readJson("json/fact.json");
		FactDAO ad = new FactDAO();
		ad.createBatch(p);
	}
//	public static void main(String[] args) {
//		/**
//		 * Số lượng cụ thể:
//		 * person : 10
//		 * country: 10
//		 * agreement: 10
//		 * event: 10
//		 * article 50
//		 * location, fact, org : 100
//		 * - 3 lần lặp sau cứ gấp 10 lần lên.
//		 * Chú ý: Trước khi insert lại thì thực hiện xóa cơ sở dữ liệu đã
//		 * Câu lệnh:
//		 * 
//		 * 	use oop;
//			SET SQL_SAFE_UPDATES = 0;
//			delete from fact;
//			delete from person;
//			delete from location;
//			delete from event;
//			delete from organization;
//			delete from country;
//			delete from agreement;
//			delete from entity;
//			delete from article;
//		 */
//		Insertion insertion = new Insertion();
//		GeneDataJson.run();
//		insertion.person();
//		insertion.organization();
//		insertion.location();
//		insertion.event();
//		insertion.country();
//		insertion.agreement();
//		insertion.article();
//		insertion.fact();
//		
//		
//	}
}
