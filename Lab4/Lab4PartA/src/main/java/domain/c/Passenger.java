package domain.c;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderColumn(name = "flight_sequence")
    private List<Flight> flights = new ArrayList<>();

    public Passenger() {
    }
    public Passenger(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
}
