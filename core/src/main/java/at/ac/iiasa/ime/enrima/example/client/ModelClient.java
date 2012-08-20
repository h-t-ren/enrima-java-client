package at.ac.iiasa.ime.enrima.example.client;
import java.util.List;
import at.ac.iiasa.ime.enrima.example.client.jaxws.EnrimaService;
import at.ac.iiasa.ime.enrima.example.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.example.client.jaxws.EntityValue;
import at.ac.iiasa.ime.enrima.example.client.jaxws.GetModelInstanceRequest;
import at.ac.iiasa.ime.enrima.example.client.jaxws.GetModelInstanceResponse;
import at.ac.iiasa.ime.enrima.example.client.jaxws.IteratorContainer;
import at.ac.iiasa.ime.enrima.example.client.jaxws.MemberDic;
import at.ac.iiasa.ime.enrima.example.client.jaxws.ModelSpecRev;
import at.ac.iiasa.ime.enrima.example.client.jaxws.ObjectFactory;
import at.ac.iiasa.ime.enrima.example.client.jaxws.SetSpec;

public class ModelClient {
	EnrimaService service = new EnrimaService();
	ObjectFactory objectFactory = new ObjectFactory();

	public static void main(String[] args) {
		ModelClient client = new ModelClient();
		client.service.setHandlerResolver(new EnrimaHandlerSolver("hongtao","hongtao"));
		GetModelInstanceResponse response = client.getModelInstance(2);
		ModelSpecRev modelSpecRev = response.getModelSpecRev();
		EntitySpec x =SymbolicModelSpecificationHelper.getEntitySpecByShortName(modelSpecRev,"x");
		System.out.println("entity Spec: ");
		System.out.println("-------------------------------");
		System.out.println("id: " + x.getId());
		System.out.println("shortName: " + x.getShortName());
		System.out.println("name: " + x.getName());
		System.out.println("description: " + x.getDescription());
		System.out.println("unit: " + x.getUnit());
		IteratorContainer itrContainer = x.getIteratorContainer();
		if(itrContainer!=null)
		{
			String idx="["; 
			boolean initItr=true;
			for(int idSetSpec:itrContainer.getIdSetSpec())
			{
				SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(modelSpecRev, idSetSpec);
				if(initItr)
				    {
					   idx +=setSpec.getShortName();
					   initItr=false;
					}
				else{
					idx += ","+ setSpec.getShortName();
				}
			}
			idx += "]";
			System.out.println("index: " + idx );
		}
	
		//for UI validation
		System.out.println("math type: " + x.getMathType());
		if(x.getIdLowerBound()!=null)
		{
			EntitySpec lowerboudEntitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpecRev,x.getIdLowerBound());
			System.out.println("lowerbound: " + lowerboudEntitySpec.getConstantValue());
		}
		if(x.getIdUpperBound()!=null)
		{
		    EntitySpec upperboudEntitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpecRev,x.getIdUpperBound());
		    System.out.println("upperbound: " + upperboudEntitySpec.getConstantValue());
		}
		System.out.println("-------------------------------");
		
		System.out.println(x.getName() +"  values");
		System.out.println("-------------------------------");
		List<EntityValue> entityValues = ModelInstanceHelper.findEntityValuesByEntiySpec(response, x);
		for(EntityValue ev:entityValues)
		{
			String tuple="[";
			boolean initItr=true;
			for(int idMember:ev.getTupleMember())
			{
				MemberDic memberDic = ModelInstanceHelper.findMemberDicById(response, idMember);
				if(initItr) 
					{
					   tuple += memberDic.getCode();
					   initItr=false;
					}
				else
				{
					tuple += "," + memberDic.getCode();
				}
			}
			tuple += "]";
			System.out.println(x.getShortName()+ tuple +" = " +ev.getValue());
		}
		System.out.println("-------------------------------");
	}

    public GetModelInstanceResponse getModelInstance(int idModelInstance)
    {
    	GetModelInstanceRequest request = objectFactory.createGetModelInstanceRequest();
    	request.setIdModelInstance(idModelInstance);
    	return service.getEnrimaSoap11().getModelInstance(request);
    }
    
}
