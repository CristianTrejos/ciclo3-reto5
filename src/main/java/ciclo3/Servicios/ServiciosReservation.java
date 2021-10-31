/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3.Servicios;

import ciclo3.Modelo.Reservation;
import ciclo3.Reportes.ContadorClients;
import ciclo3.Reportes.StatusReservas;
import ciclo3.Repositorio.ReservationRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kryz
 */
@Service
public class ServiciosReservation {
    @Autowired
    private ReservationRepositorio metodosCrud;
    
    public List<Reservation> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservation> getReservation(int idReservation) {
        return metodosCrud.getReservation(idReservation);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservation> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int idReservation) {
        Boolean aBoolean = getReservation(idReservation).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
public List<ContadorClients> getTopClients(){
        return metodosCrud.getTopClients();
    }

    public List<Reservation> getReservationsPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date a= new Date();
        Date b=new Date();
        try {
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(a.before(b)){
            return metodosCrud.getReservationPeriod(a,b);
        }else{
            return new ArrayList<>();
        }

    }
    public StatusReservas getReservationsStatusReport(){
        List<Reservation>completed=metodosCrud.getReservationsByStatus("completed");
        List<Reservation>cancelled=metodosCrud.getReservationsByStatus("cancelled");
        return new StatusReservas (completed.size(),cancelled.size());

    }
    
}
