package at.ac.iiasa.ime.enrima.client;

import java.util.List;

import at.ac.iiasa.ime.enrima.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.MathType;
import at.ac.iiasa.ime.enrima.client.jaxws.MemberDic;
import at.ac.iiasa.ime.enrima.client.jaxws.TupleValue;


//author Hongtao Ren
public class ModelDataHelper {
	
	public static void printEntityValues(GetEntityValuesResponse getEntityValuesResponse)
	{
		System.out.println("---------------------------------");
		EntitySpec entitySpec = getEntityValuesResponse.getEntitySpec();
		List<TupleValue> tupleValues = getEntityValuesResponse.getTupleValue();
		for(TupleValue tv:tupleValues)
		{
			
			String tuple="[";
			boolean initItr=true;
			for(MemberDic memberDic:tv.getTupleMember())
			{
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
			System.out.print(getEntityValuesResponse.getEntitySpec().getShortName()+ tuple +" = " );
			if(entitySpec.getMathType().equals(MathType.INTEGER))
			{
				System.out.println(tv.getValue().getIntValue());
			}
			else if(entitySpec.getMathType().equals(MathType.REAL))
			{
				System.out.println(tv.getValue().getDoubleValue());
			}

		}
		System.out.println("---------------------------------");
	}


}
