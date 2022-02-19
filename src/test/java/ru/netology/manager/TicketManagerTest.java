package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);
    private Ticket toLapland = new Ticket(134234, 100_000, "DME","LLA", 120_000);
    private Ticket toVladivostok = new Ticket(167859, 1_000_000, "GCA","VVO", 99_999);
    private Ticket toNarnia = new Ticket(0, 0, "Wardrobe","Fairy tale", 0);
    private Ticket toChad = new Ticket(983754, 100_000, "DME","CIA", 60_000);
    private Ticket toMoscow = new Ticket(85311, 900_000, "JFK", "SVO", 15);

    @BeforeEach
    private void addTickets() {
        manager.addTicket(toLapland);
        manager.addTicket(toVladivostok);
        manager.addTicket(toNarnia);
        manager.addTicket(toChad);
        manager.addTicket(toMoscow);
    }

    @Test
// TEST1: FROM DME TO CIA (TO CHAD)
    public void shouldSearchWithExistAirports() {
        Ticket[] expected = {toChad};
        Ticket[] actual = manager.findAll("DME", "CIA");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST2: FROM TUR TO PRO (both not exist)
    public void shouldSearchWithNotExistAirports() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("TUR", "PRO");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST3: FROM GCA TO PINEAPPLE TOWN (first is right)
    public void shouldSearchWithFirstRightAirport() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("GCA", "Pineapple town");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST4: FROM VVO TO SVO (second is right)
    public void shouldSearchWithSecondRightAirport() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("VVO", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST5: FROM JFK TO VVO
    public void shouldSearchWithAirportsFromDifferentTickets() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("JFK", "VVO");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST6: FROM NEW-YORK (JFK) TO MOSCOW (SVO)
    public void shouldSearchByTownName() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("New-york", "Moscow");

        assertArrayEquals(expected, actual);
    }

    @Test
// TEST7: EMPTY STRINGS. Expected behaviour - returned all sorted tickets by price
    public void shouldSearchByEmptyStringsAndSort() {
        Ticket[] expected = {toNarnia, toLapland, toChad, toMoscow, toVladivostok};
        Ticket[] actual = manager.findAll("", "");

        assertArrayEquals(expected, actual);
    }
}