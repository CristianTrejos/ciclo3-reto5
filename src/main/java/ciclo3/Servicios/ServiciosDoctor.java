/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.Servicios;

import ciclo3.Modelo.Doctor;
import ciclo3.Repositorio.DoctorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kryz
 */
@Service
public class ServiciosDoctor {
    @Autowired
    private DoctorRepositorio metodosCrud;
    
    public List<Doctor> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Doctor> getDoctor(int idDoctor){
        return metodosCrud.getDoctor(idDoctor);
    }
    
    public Doctor save(Doctor doctor){
        if(doctor.getId()==null){
            return metodosCrud.save(doctor);
        }else{
            Optional<Doctor> evt=metodosCrud.getDoctor(doctor.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(doctor);
            }else{
                return doctor;
            }
        }
    }
    public Doctor update(Doctor doctor){
        if(doctor.getId()!=null){
            Optional<Doctor> e=metodosCrud.getDoctor(doctor.getId());
            if(!e.isEmpty()){
                if(doctor.getName()!=null){
                    e.get().setName(doctor.getName());
                }
                if(doctor.getDepartment()!=null){
                    e.get().setDepartment(doctor.getDepartment());
                }
                if(doctor.getYear()!=null){
                    e.get().setYear(doctor.getYear());
                }
                if(doctor.getDescription()!=null){
                    e.get().setDescription(doctor.getDescription());
                }
                if(doctor.getSpecialty()!=null){
                    e.get().setSpecialty(doctor.getSpecialty());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return doctor;
            }
        }else{
            return doctor;
        }
    }


    public boolean deleteDoctor(int IdDoctor) {
        Boolean aBoolean = getDoctor(IdDoctor).map(doctor -> {
            metodosCrud.delete(doctor);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
