/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.Repositorio;

import ciclo3.Interface.InterfaceSpecialty;
import ciclo3.Modelo.Specialty;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kryz
 */
@Repository
public class SpecialtyRepositorio {
    @Autowired
    private InterfaceSpecialty crud2;
    
    public List<Specialty> getAll(){
        return (List<Specialty>) crud2.findAll();
    }
    
    public Optional <Specialty> getSpecialty(int id){
        return crud2.findById(id);
    }
    
    public Specialty save (Specialty specialty){
        return crud2.save(specialty);
    }
    
    public void delete(Specialty specialty){
        crud2.delete(specialty);
    }
}
