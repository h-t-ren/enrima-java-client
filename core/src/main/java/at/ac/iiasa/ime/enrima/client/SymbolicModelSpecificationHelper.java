package at.ac.iiasa.ime.enrima.client;

import java.util.ArrayList;
import java.util.List;

import at.ac.iiasa.ime.enrima.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.client.jaxws.IteratorContainer;
import at.ac.iiasa.ime.enrima.client.jaxws.ModelSpec;
import at.ac.iiasa.ime.enrima.client.jaxws.SetSpec;


//author Hongtao Ren
public class SymbolicModelSpecificationHelper {
	
	public static List<SetSpec> getSetSpecsByIteratorContainer(ModelSpec sms,IteratorContainer iteratorContainer)
	{
		if(iteratorContainer==null)
			return null;
		else
		{
			List<SetSpec> setSpecs = new ArrayList<SetSpec>(iteratorContainer.getIdSetSpec().size());
			for(int idSetSpec:iteratorContainer.getIdSetSpec())
			{
				setSpecs.add(getSetSpecById(sms, idSetSpec));
			}
			return setSpecs;
		}
	}
	
	public static SetSpec getSetSpecByShortName(ModelSpec sms, String shortName) {
		for (SetSpec s : sms.getSetSpec()) {
			if (s.getShortName().equals(shortName))
				return s;
		}
		return null;
	}

	public static SetSpec getSetSpecById(ModelSpec sms, int id) {
		for (SetSpec s : sms.getSetSpec()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	
	
	public static EntitySpec getEntitySpecByShortName(ModelSpec sms,String shortName) {

		for (EntitySpec e : sms.getEntitySpec()) {
			if (e.getShortName().equals(shortName))
				return e;
		}
		return null;
	}

	public static EntitySpec getEntitySpecById(ModelSpec sms, int id) {
		for (EntitySpec e : sms.getEntitySpec()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
	
	
	public static void printSMSHeader(ModelSpec sms)
	{
		System.out.println("---------------------------------");
		System.out.println("SMS shortname: "+sms.getShortName());
		System.out.println("SMS name: " + sms.getName());
		System.out.println("SMS description: " + sms.getDescription());
		System.out.println("SMS status: " + sms.getStatus());
		System.out.println("---------------------------------");
		System.out.println();
	}
	
	
	public static void printEntitySpec(ModelSpec sms, EntitySpec entitySpec)
	{
		System.out.println("---------------------------------");
		
		System.out.println("id: " + entitySpec.getId());
		System.out.println("shortName: " + entitySpec.getShortName());
		System.out.println("name: " + entitySpec.getName());
		System.out.println("unit: " + entitySpec.getUnit());
		IteratorContainer itrContainer = entitySpec.getIteratorContainer();
		if(itrContainer!=null)
		{
			String idx="["; 
			boolean initItr=true;
			for(int idSetSpec:itrContainer.getIdSetSpec())
			{
				SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(sms, idSetSpec);
				
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
	
		//math type, lowerbound, upperbound can be used for UI validation
		System.out.println("math type: " + entitySpec.getMathType());
		if(entitySpec.getIdLowerBound()!=null)
		{
			EntitySpec lowerboudEntitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(sms,entitySpec.getIdLowerBound());
			System.out.println("lowerbound: " + lowerboudEntitySpec.getConstantValue());
			
		}
		
		if(entitySpec.getIdUpperBound()!=null)
		{
		    EntitySpec upperboudEntitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(sms,entitySpec.getIdUpperBound());
		    System.out.println("upperbound: " + upperboudEntitySpec.getConstantValue());
		}
		System.out.println("---------------------------------");
	}


}
