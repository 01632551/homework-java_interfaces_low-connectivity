package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void addTicket(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] getAll() {
        return repository.findAll();
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] tickets = new Ticket[0];
        Ticket[] tmp = new Ticket[counterArrayLength(from, to)];
        int index = 0;
        for (Ticket ticket : repository.findAll()) {
            if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
                tmp[index] = ticket;
                index++;
            }
        }
        Arrays.sort(tmp);
        tickets = tmp;
        return tickets;
    }

    private int counterArrayLength(String from, String to) {
        int counter = 0;
        for (Ticket ticket : repository.findAll()) {
            if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
                counter++;
            }
        }
        return counter;
    }
}
