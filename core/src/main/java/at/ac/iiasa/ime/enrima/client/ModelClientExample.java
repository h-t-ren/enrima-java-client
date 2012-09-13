package at.ac.iiasa.ime.enrima.client;
import at.ac.iiasa.ime.enrima.client.jaxws.EnrimaService;
import at.ac.iiasa.ime.enrima.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.ModelSpec;
import at.ac.iiasa.ime.enrima.client.jaxws.ObjectFactory;


//author Hongtao Ren
public class ModelClientExample {
	EnrimaService service = new EnrimaService();
	ObjectFactory objectFactory = new ObjectFactory();

	public static void main(String[] args) {
		EnrimaWSProxy proxy = new EnrimaWSProxy();
		//add resolvers, log, auth, etc.
		//proxy.service.setHandlerResolver(new EnrimaHandlerResolver());
		
		//get SMS by id
		System.out.println("get SMS by id=1");
		ModelSpec sms = proxy.getSms(1);
		SymbolicModelSpecificationHelper.printSMSHeader(sms);
		System.out.println();
		

		System.out.println("Entity CI");
		EntitySpec entitySpec =SymbolicModelSpecificationHelper.getEntitySpecByShortName(sms,"CI");
		SymbolicModelSpecificationHelper.printEntitySpec(sms, entitySpec);
		System.out.println();
		
		System.out.println("Values of Entity CI");
		GetEntityValuesResponse getEntityValuesResponse = proxy.getEntityValues(2, "CI");
		ModelDataHelper.printEntityValues(getEntityValuesResponse);


	}
	


}
