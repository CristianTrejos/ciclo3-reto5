/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.Repositorio;

import ciclo3.Interface.InterfaceReservation;
import ciclo3.Modelo.Client;
import ciclo3.Modelo.Reservation;
import ciclo3.Reportes.ContadorClients;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kryz
 */
@Repository
public class ReservationRepositorio {
    @Autowired
    private InterfaceReservation crud4;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) crud4.findAll();
    }
    
    public Optional <Reservation> getReservation(int id){
        return crud4.findById(id);
    }
    
    public Reservation save (Reservation reservation){
        return crud4.save(reservation);
    }
    
    public void delete(Reservation reservation){
        crud4.delete(reservation);
    }
   public  List<ContadorClients> getTopClients(){
        List<ContadorClients>res=new ArrayList<>();
        List<Object[]>report=crud4.countTotalReservationsByClient();
        for(int i=0;i<report.size();i++){
            res.add(new ContadorClients((Long)report.get(i)[1],(Client) report.get(i)[0]));
        }
        return res;
    }

    public List<Reservation> getReservationPeriod(Date a, Date b){
        return crud4.findAllByStartDateAfterAndStartDateBefore(a,b);
    }

    public List<Reservation> getReservationsByStatus(String status){
        return crud4.findAllByStatus(status);
    }
   
}
