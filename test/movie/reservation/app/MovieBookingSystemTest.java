package movie.reservation.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieBookingSystemTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  MovieBookingSystem system = new MovieBookingSystem();
  
  @BeforeEach
  void setup() {
    outContent.reset();
    System.setOut(new PrintStream(outContent));
    system.getMovieSchedule().clear();
    system.addMovieSchedules();
  }
  
  @Test
  void testCheckAvailability_IfInputIsValid_ReturnsTrue() {
    assertTrue(system.checkAvailability("9:00 PM"));
  }
  
  @Test
  void testCheckAvailability_IfInputIsInvalid_ReturnsFalse() {
    assertFalse(system.checkAvailability("9:00 AM"));
  }
  
  @Test
  void testCancelReservation_IfShowIsNotFound_DisplayShowIsNotFound() {
    system.cancelReservation("110:00 AM", 2);
    assertTrue(outContent.toString().contains("No shows has been found."));
  }
  
  @Test
  void testBookTicket_IfTicketsToBuyIsValid_DisplaysSuccessfulReservation() {
    system.bookTicket("12:00 PM", 4);
    
    assertTrue(outContent.toString().contains("ticket(s) successfully booked for"));
  }
  
  @Test
  void testBookTicket_IfTicketsToBuyIsZero_DisplaysNoTicketsAvailable() {
    system.bookTicket("8:00 PM", 1);
    
    assertTrue(outContent.toString().contains("No tickets available for this showtime"));
  }
  
  @Test
  void testBookTicket_IfTicketsToBuyIsGreaterThanAvailableTickets_DisplaysUnsucessfulReservation() {
    system.bookTicket("10:00 AM", 100);
    assertTrue(outContent.toString().contains("Not enough tickets available for this showtime"));
  }
  
  @Test
  void testCancelReservation_IfCancellationIsValid_DisplaysSuccessfulCancellation() {
    system.bookTicket("10:00 AM", 2);
    system.cancelReservation("10:00 AM", 2);
    
    assertTrue(outContent.toString().contains("ticket(s) successfully cancelled for"));
  }
  
  @Test
  void testCancelReservation_IfCancellationIsInvalid_DisplaysUnsuccessfulCancellation() {
    system.bookTicket("10:00 AM", 2);
    system.cancelReservation("10:00 AM", 3);
    
    assertTrue(outContent.toString().contains("Invalid operation (Attempt to cancel more tickets than booked)"));
  }
  
  @Test
  void testShowAllAvailableShows_DisplaysAllSchedules() {
    system.showAvailableShows();
  }
  
  @Test
  void testMainMethod_ReturnsVoid() {
    Main.main(null);
  }

}
