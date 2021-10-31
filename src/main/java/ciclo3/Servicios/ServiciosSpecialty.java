/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.Servicios;

import ciclo3.Modelo.Specialty;
import ciclo3.Repositorio.SpecialtyRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kryz
 */
@Service
public class ServiciosSpecialty {
    @Autowired
    private SpecialtyRepositorio metodosCrud;
    
    public List<Specialty> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Specialty> getSpecialty(int idSpecialty){
        return metodosCrud.getSpecialty(idSpecialty);
    }
    
    public Specialty save(Specialty specialty){
        if(specialty.getId()==null){
            return metodosCrud.save(specialty);
        }else{
            Optional<Specialty> evt=metodosCrud.getSpecialty(specialty.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(specialty);
            }else{
                return specialty;
            }
        }
    }
    public Specialty update(Specialty specialty){
        if(specialty.getId()!=null){
            Optional<Specialty>g=metodosCrud.getSpecialty(specialty.getId());
            if(!g.isEmpty()){
                if(specialty.getDescription()!=null){
                    g.get().setDescription(specialty.getDescription());
                }
                if(specialty.getName()!=null){
                    g.get().setName(specialty.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return specialty;
    }
    public boolean deleteSpecialty(int Idspecialty){
        Boolean d=getSpecialty(Idspecialty).map(specialty -> {
            metodosCrud.delete(specialty);
            return true;
        }).orElse(false);
        return d;
    }
}
    

