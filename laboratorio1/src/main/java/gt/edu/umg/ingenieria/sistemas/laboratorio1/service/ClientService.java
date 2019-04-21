package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josué Barillas (jbarillas)
 */

@Service
public class ClientService {
    
     @Autowired
    private ClientRepository  repclient;    
  
     public List<Client> findAll()
     
     {
        return (List<Client>) this.repclient.findAll();
    }
    
    public Client findNit(String l3)
    {
        return this.repclient.findClientByNit(l3);
    }
    
    public Client findId(Long id) 
    
    {
        return this.repclient.findClientById(id);
    }
   
    public Client setNew(Client l4)
    
    {               
        return this.repclient.save(l4);
    }
    
    public Client setUpdate(Client l4) 
    
    {
        return this.repclient.save(l4);        
    }  
    
  
    
    
    
    
    
    
    
    
    
    

}
