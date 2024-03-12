package com.example.eventsystem.Controller;

import com.example.eventsystem.APIResponse.APIResponse;
import com.example.eventsystem.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    /*• Create a new event (ID , description , capacity, startDate , endDate)
• Display all event .
• Update a event
• Delete a event
• Change capacity
• Search for a event by given id
Hint ( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )*/

    ArrayList<Event> events = new ArrayList<>();
    @PostMapping("/addEvent")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
        if(errors.hasErrors()){
            String info=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(info);
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("EVENT "+event.getID()+" IS ADDED SUCCESSFULLY"));
    }//{"ID":"4321","description":"descriptionEvent","capacity":20,"startDate":"2024-10-03","endDate":"2024-10-13"}

    @GetMapping("/allevents")
    public ResponseEntity displayAll() {
        if (events.isEmpty()){
            return ResponseEntity.status(400).body(new APIResponse("Empty"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    //----
    @PutMapping("/update/{ID}")
    public ResponseEntity update(@PathVariable String ID, @RequestBody @Valid Event event,Errors errors) {
        if (errors.hasErrors()){
            String inf=errors.getFieldError().getField();
            return ResponseEntity.status(400).body(inf);
        }
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getID().equalsIgnoreCase(ID)) {
                events.set(i, event);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new APIResponse(events.get(i).getID() + "  (Updated with)  " + event.toString()));
            }
        }
        return ResponseEntity.status(400).body(new APIResponse("This ID: " + ID + " NOT FOUND"));
    }

    //----------------------
    @DeleteMapping("/delete/{ID}")
    public ResponseEntity delete(@PathVariable String ID) {

        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID)) {
                String info = event.getID();
                events.remove(event);
                return ResponseEntity.status(HttpStatus.OK).body( new APIResponse("Event with ID: " + info + " is deleted successfully"));
            }
        }
        return ResponseEntity.status(400).body( new APIResponse("This ID" + ID + " NOT FOUND"));
    }

    //---• Change capacity

    @PutMapping("/changeCapacity/{ID}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable String ID, @PathVariable @Valid Integer capacity,Errors errors) {
        if (errors.hasErrors()){
            String info=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(info);
        }
        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID)) {
                event.setCapacity(capacity);
                return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Change Capacity with" + capacity + "Successfully"));
            }
        }
        return ResponseEntity.status(400).body(new APIResponse("ID " + ID + " NOT FOUND"));
    }

    //
    @GetMapping("/serach/{ID}")
    public ResponseEntity search(@PathVariable String ID) {
        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID))
                return ResponseEntity.status(HttpStatus.OK).body( event);
        }
        return ResponseEntity.status(400).body(new APIResponse("Not found"));
    }


}
