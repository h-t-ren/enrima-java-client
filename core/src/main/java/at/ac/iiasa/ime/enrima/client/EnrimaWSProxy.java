package at.ac.iiasa.ime.enrima.client;

import at.ac.iiasa.ime.enrima.client.jaxws.EnrimaService;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.jaxws.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.jaxws.GetSMSRequest;
import at.ac.iiasa.ime.enrima.client.jaxws.ModelSpec;
import at.ac.iiasa.ime.enrima.client.jaxws.ObjectFactory;

//author Hongtao Ren
public class EnrimaWSProxy {
	EnrimaService service = new EnrimaService();
	ObjectFactory objectFactory = new ObjectFactory();

    public ModelSpec getSms(int idSMS)
    {
    	GetSMSRequest request = objectFactory.createGetSMSRequest();
    	request.setIdModelSpec(1);
    	return service.getEnrimaSoap11().getSMS(request).getModelSpec();
    }
    
    public GetEntityValuesResponse getEntityValues(int idModelData,String entityShortName)
    {
    	GetEntityValuesRequest request = objectFactory.createGetEntityValuesRequest();
    	request.setEntityShortName(entityShortName);
    	request.setIdModelData(idModelData);
    	return service.getEnrimaSoap11().getEntityValues(request);
    }
    
    

}
