package at.ac.iiasa.ime.enrima.example.client;

import java.util.ArrayList;
import java.util.List;

import at.ac.iiasa.ime.enrima.example.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.example.client.jaxws.EntityValue;
import at.ac.iiasa.ime.enrima.example.client.jaxws.GetModelInstanceResponse;
import at.ac.iiasa.ime.enrima.example.client.jaxws.MemberDic;
import at.ac.iiasa.ime.enrima.example.client.jaxws.SetMember;
import at.ac.iiasa.ime.enrima.example.client.jaxws.SetSpec;

public class ModelInstanceHelper {
	
    public static List<EntityValue> findEntityValuesByEntiySpec(GetModelInstanceResponse modelInstanceResponse,EntitySpec entiySpec)
    {
    	List<EntityValue> allEntityValues = modelInstanceResponse.getEntityValue();
    	List<EntityValue> entityValues = new ArrayList<EntityValue>();
    	boolean found=false;
    	for(EntityValue ev:allEntityValues)
    	{
    		if(ev.getIdEntitySpec()==entiySpec.getId())
    		{
    			found=true;
    			entityValues.add(ev);
    		}
    		else
    		{
    			if(found)break;
    		}
    	}
    	return entityValues;
    	
    }

    public static List<SetMember> findSetMembers(GetModelInstanceResponse modelInstanceResponse,SetSpec setSpec)
    {
    	List<SetMember> setMembers = new ArrayList<SetMember>();
    	List<SetMember> allSetMembers = modelInstanceResponse.getSetMember();
    	boolean found=false;
    	for(SetMember s:allSetMembers)
    	{
    		if(s.getIdSetSpec()==setSpec.getId())
    		{
    			found=true;
    			setMembers.add(s);
    		}
    		else
    		{
    			if(found)break;
    		}
    	}
    	return setMembers;
    }
    
    public static MemberDic findMemberDicById(GetModelInstanceResponse modelInstanceResponse,int idMember)
    {
    	for(MemberDic d:modelInstanceResponse.getMemberDic())
    	{
    		if(d.getId()==idMember)
    			return d;
    	}
    	return null;
    }
}
