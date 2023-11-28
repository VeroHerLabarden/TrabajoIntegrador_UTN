
package persistencia;

import logica.Cliente;
import logica.Especialidad;
import logica.Incidente;
import logica.Tecnico;


public class ControladoraPersistencia{
 ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        
    ClienteJpaController CliJpa = new ClienteJpaController();
    AreaComercialJpaController AreaCJpa = new AreaComercialJpaController(); 
    EspecialidadJpaController EspJpa = new EspecialidadJpaController(); 
    IncidenteJpaController IncJpa = new IncidenteJpaController(); 
    RRHHJpaController RRHHJpa = new RRHHJpaController(); 
    TecnicoJpaController TecJpa = new TecnicoJpaController();
//create
    public void createCliente(Cliente cliente) {
        CliJpa.create(cliente);
    }

    public void createEspecialidad(Especialidad especialidad) {
        EspJpa.create(especialidad);
    }

    public void createIncidente(Incidente incidente) {
       IncJpa.create(incidente);
    }

    public void createTecnico(Tecnico tecnico) {
       TecJpa.create(tecnico);
    }
//
    public void findCliente(int id) {
        CliJpa.findCliente(id);
    }

    public void findEspecialidad(int id) {
        EspJpa.findEspecialidad(id);
    }

    public void findIncidente(int id) {
        IncJpa.findIncidente(id);
    }

    public void findTecnico(int id) {
        TecJpa.findTecnico(id);
    }

    public void destroyCliente(int id) {
       CliJpa.destroyCliente(id); 
    }

    public void destroyEspecialidad(int id) {
         EspJpa.destroyEspecialidad(id);
    }

    public void destroyIncidente(int id) {
         IncJpa.destroyIncidente(id);
    }

    public void destroyTecnico(int id) {
        TecJpa.destroyTecnico(id);
    }

    public void editCliente(Cliente cliente) {
        CliJpa.editCliente(cliente);
    }

       public void editEspecialidad(Especialidad especialidad) {
        EspJpa.editEspecialidad(especialidad);
    }

    public void editIncidente(Incidente incidente) {
        IncJpa.editIncidente(incidente);
    }

    public void editTecnico(Tecnico tecnico) {
       TecJpa.editTecnico(tecnico);
    }
    
    
    
    
    
    
    
}
    
        
      