package at.ac.iiasa.ime.enrima.client;
import java.util.List;
import at.ac.iiasa.ime.enrima.client.jaxws.BatchValues;
import at.ac.iiasa.ime.enrima.client.jaxws.EnrimaService;
import at.ac.iiasa.ime.enrima.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.client.jaxws.GetBatchValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.MathType;
import at.ac.iiasa.ime.enrima.client.jaxws.MemberDic;
import at.ac.iiasa.ime.enrima.client.jaxws.ModelSpec;
import at.ac.iiasa.ime.enrima.client.jaxws.ObjectFactory;
import at.ac.iiasa.ime.enrima.client.jaxws.Value;


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
		
		
		//example for get building parameters	
		GetBatchValuesResponse getBuildingParameters = proxy.getBatchValues(2, "buildingPara");
		//all the entitySpecs belong the group buildingPara_h, in this case: ext, solar
		List<EntitySpec> entitySpecs = getBuildingParameters.getEntitySpec();
		System.out.println("Building parameters:");
		for(BatchValues bacthValues:getBuildingParameters.getBatchValues())
		{
			//print the short time period
			for(MemberDic memberDic: bacthValues.getTupleMember())
			{
				System.out.print(memberDic.getCode());
			}
			//print ext and solar
			int i=0;
			for(Value value :bacthValues.getValue())
			{
				MathType mathType = entitySpecs.get(i).getMathType();
				//String entityShortName=entitySpecs.get(i).getShortName();
				if(mathType.equals(MathType.INTEGER))
				{
					System.out.print(" " +value.getIntValue() + " ");
				}else{  //REAL type
					System.out.print(" " +value.getDoubleValue() + " ");
				}
				
				i++;
			}
			System.out.println();
		}


	}
	


}
