package at.ac.iiasa.ime.enrima.example.client;

import java.util.ArrayList;
import java.util.List;

import at.ac.iiasa.ime.enrima.example.client.jaxws.EntitySpec;
import at.ac.iiasa.ime.enrima.example.client.jaxws.IteratorContainer;
import at.ac.iiasa.ime.enrima.example.client.jaxws.ModelSpecRev;
import at.ac.iiasa.ime.enrima.example.client.jaxws.SetSpec;

//author Hongtao Ren
public class SymbolicModelSpecificationHelper {
	
	public static List<SetSpec> getSetSpecsByIteratorContainer(ModelSpecRev modelSpecRev,IteratorContainer iteratorContainer)
	{
		if(iteratorContainer==null)
			return null;
		else
		{
			List<SetSpec> setSpecs = new ArrayList<SetSpec>(iteratorContainer.getIdSetSpec().size());
			for(int idSetSpec:iteratorContainer.getIdSetSpec())
			{
				setSpecs.add(getSetSpecById(modelSpecRev, idSetSpec));
			}
			return setSpecs;
		}
	}
	
	public static SetSpec getSetSpecByShortName(ModelSpecRev modelSpecRev, String shortName) {
		for (SetSpec s : modelSpecRev.getSetSpec()) {
			if (s.getShortName().equals(shortName))
				return s;
		}
		return null;
	}

	public static SetSpec getSetSpecById(ModelSpecRev modelSpecRev, int id) {
		for (SetSpec s : modelSpecRev.getSetSpec()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	
	
	public static EntitySpec getEntitySpecByShortName(ModelSpecRev modelSpecRev, String shortName) {

		for (EntitySpec e : modelSpecRev.getEntitySpec()) {
			if (e.getShortName().equals(shortName))
				return e;
		}
		return null;
	}

	public static EntitySpec getEntitySpecById(ModelSpecRev modelSpecRev, int id) {
		for (EntitySpec e : modelSpecRev.getEntitySpec()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
