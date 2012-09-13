package at.ac.iiasa.ime.enrima.client;

import java.util.List;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.MemberDic;
import at.ac.iiasa.ime.enrima.client.jaxws.TupleValue;


//author Hongtao Ren
public class ModelDataHelper {
	
	
	
	public static void printEntityValues(GetEntityValuesResponse getEntityValuesResponse)
	{
		System.out.println("---------------------------------");
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
			System.out.println(getEntityValuesResponse.getEntitySpec().getShortName()+ tuple +" = " +tv.getValue());
		}
		System.out.println("---------------------------------");
	}


}
