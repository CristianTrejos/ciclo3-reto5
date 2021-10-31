/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ciclo3.Interface;

import ciclo3.Modelo.Message;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Kryz
 */
public interface InterfaceMessage extends CrudRepository<Message, Integer>{
    
}