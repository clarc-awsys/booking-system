package movie.reservation.app;

public abstract class BookingSystem {
  abstract boolean checkAvailability(String showTime);
  abstract void bookTicket(String showTime, int tickets);
  abstract void cancelReservation(String showTime, int tickets);
}
