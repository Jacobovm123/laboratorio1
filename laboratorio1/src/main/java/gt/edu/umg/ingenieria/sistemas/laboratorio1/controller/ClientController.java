package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@RestController

public class ClientController

{
   @Autowired

   private ClientService l5;
    
    @GetMapping("/clientes/buscarTodos")
   
    public List<Client> todos() 
    
    {
        return this.l5.findAll();
    }
    
    @GetMapping("/clientes/buscarPorNit")
    
    public Client nitc(@RequestParam(name = "nit", required = true) String l2) 
    
    {
        
        
        
        
        
        return this.l5.findNit(l2);
    }
    
   @GetMapping("/clientes/buscarPorNombreApellido")
   
    public List<Client> getByName(@RequestParam(name = "query", required = true) String query) 
    
    {
        
        List<Client> l6= new ArrayList<Client>();
        List<Client> aux= null;
        
        int j1=query.indexOf(" ");
  
        if(j1>0)
        {
         aux = this.l5.findAll();
     
         for (int j=0; j<aux.size();j++)
         
         {
         if(aux.get(j).getFirstName().equals(query.substring(0,j1)) && aux.get(j).getFirstName().equals(query.substring(j1+1)))
         
         {
          l6.add(aux.get(j));
         } }
        }
        
        
        else
        
        
        {
            int aste=query.indexOf("*");
            if(aste>=0)
            {
           if(aste==0)
           {
           aux = this.l5.findAll();
           for (int j=0; j<aux.size();j++)
           
           {
           if(aux.get(j).getFirstName().equals(query.substring(j1+1)))
           
           {
            l6.add(aux.get(j));
           
           }
           
           }
             }
       
           else
           
           {
          if(aste==(query.length()-1))
          
          {
             aux = this.l5.findAll();
             for (int b=0; b<aux.size();b++)
             
             {
          if(aux.get(b).getLastName().equals(query.substring(j1+1)))
          
          {
           l6.add(aux.get(b));
             }
            }
             }
    
          else
          
          {
         aux = this.l5.findAll();
        String j2="";
        for (int i=0; i<aux.size();i++){
        j2 = aux.get(i).getFirstName()+aux.get(i).getLastName();
        if(j2.startsWith(query.substring(0,aste)) && j2.endsWith(query.substring(aste+1)))
             
             {
            l6.add(aux.get(i));
           }
            }
         }
        }
       }
         else
            
        {
           aux = this.l5.findAll();
             
           String combinado="";
            for (int i=0; i<aux.size();i++)
            
            {
                       
                 combinado = aux.get(i).getFirstName()+aux.get(i).getLastName();
                        
                    if(combinado.equals(query)){
                        l6.add(aux.get(i));
                    }
                }
            }
        }
        return l6;
    }
    @PostMapping("/clientes/crearCliente")
    public Client nuevo(@RequestBody(required = true) Client nuevocliente) {
        
        Client clie= new Client();
        if(nuevocliente.getNit().length()>10){
            clie.setId(Long.parseLong("0"));
            clie.setFirstName("");
            clie.setLastName("");
            clie.setAddress("");
            clie.setPhone("");
            clie.setNit("NIT Invalido");
        }
        else if(nuevocliente.getNit().isEmpty()==true){
            clie.setNit("Lo sentimos. El sistema solo permite el registro de usuarios mayores de edad.");
            clie.setId(Long.parseLong("0"));
            clie.setFirstName("");
            clie.setLastName("");
            clie.setAddress("");
            clie.setPhone("");
        }
        else{
            try {
                nuevocliente.setFirstName(nuevocliente.getFirstName().substring(0,1).toUpperCase()+nuevocliente.getFirstName().substring(1).toLowerCase());
                nuevocliente.setLastName(nuevocliente.getLastName().substring(0,1).toUpperCase()+nuevocliente.getLastName().substring(1).toLowerCase());
            } catch (Exception e) {
            }
            
            clie = this.l5.setNew(nuevocliente);
           
            
        }
        return clie;
    }

    
    @PutMapping("/clientes/editarCliente")
    public Client update(@RequestBody(required = true) Client cli) {
        
        Client clie = new Client();
        
        if(cli.getNit().length()>10){
            clie.setId(Long.parseLong("0"));
            clie.setFirstName("");
            clie.setLastName("");
            clie.setAddress("");
            clie.setPhone("");
            clie.setNit("NIT Invalido");
        }
        else if(cli.getNit().isEmpty()==true){
            clie.setNit("Lo sentimos. El sistema solo permite el registro de usuarios mayores de edad.");
            clie.setId(Long.parseLong("0"));
            clie.setFirstName("");
            clie.setLastName("");
            clie.setAddress("");
            clie.setPhone("");
        }
        else{
            cli.setFirstName(cli.getFirstName().substring(0,1).toUpperCase()+cli.getFirstName().substring(1).toLowerCase());
            cli.setLastName(cli.getLastName().substring(0,1).toUpperCase()+cli.getLastName().substring(1).toLowerCase());
            clie = this.l5.setNew(cli);
           
            
        }
        return clie;
    }
    
   @PutMapping("/clientes/editarCliente/{identificador}/{numero}")
    public Client setUpdateNit(@PathVariable(required = true) long identificador,@PathVariable(required = true) String numero) {
        
        Client clie= new Client();
        clie = this.l5.findId(identificador);
        if(clie.getNit().length()>10){
            clie.setId(Long.parseLong("0"));
            clie.setFirstName("");
            clie.setLastName("");
            clie.setAddress("");
            clie.setPhone("");
            clie.setNit("NIT Invalido");
        }
        else{
            clie.setNit(numero);
            this.l5.setUpdate(clie);
        }
        return clie;
    }
    
    @PutMapping("/clientes/editarCliente/{identificador}/{nombre}/{apellido}")
    public Client setUpdateNombre(@PathVariable(required = true) long identificador,@PathVariable(required = true) String nombre,@PathVariable(required = true) String apellido) {
        
        Client clie= new Client();
        
        clie = this.l5.findId(identificador);
        try{
            clie.setFirstName(nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase());
            clie.setLastName(apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase());
        }
        catch(Exception a){
            
        }
        
        return this.l5.setUpdate(clie);
    }
 
    @GetMapping("/clientes/generarReporteClientes")
    public String reporte() {
        
        String htmlCadena = "Reporte Clientes <br><br>";
        
        String carpeta = "/var/www/";
        File f = new File(carpeta);
        File[] ficheros = f.listFiles();
        int contador=1;
        for (int x=0;x<ficheros.length;x++){
            contador++;            
        }
        
        File reportes = new File("/var/www/Clientes_"+String.valueOf(contador)+".html");
        List<Client> respuesta = this.l5.findAll();
        
        try{
            BufferedWriter buffer = new BufferedWriter(new FileWriter(reportes));
        for(int i=0; i<respuesta.size();i++){
            htmlCadena= htmlCadena+"  \t" +
            respuesta.get(i).getId()+"  \t" +
            respuesta.get(i).getFirstName()+"  \t" +
            respuesta.get(i).getLastName()+"  \t" +
            respuesta.get(i).getNit()+"  \t" +
            respuesta.get(i).getPhone()+"  \t" +
            respuesta.get(i).getAddress()+"  \t" +
            "<br>" ;
        }
        
        buffer.write(htmlCadena);
        buffer.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return "El reporte  var/www/Clientes_"+String.valueOf(contador)+".html ha sido generado.";
    }
    
    
}
