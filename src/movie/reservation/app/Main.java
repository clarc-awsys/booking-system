package movie.reservation.app;
/**
 * Main class.
 */
public final class Main {
  private Main() {
    super();
  }
  /**
   * Main method.
   * @param args
   */
  public static void main(final String[] args) {
    final int five = 5;
    final int oneHundred = 100;
    final int two = 2;
    final int three = 3;
    MovieBookingSystem movieBookingSystem = new MovieBookingSystem();
    movieBookingSystem.addMovieSchedules();
    movieBookingSystem.showAvailableShows();
    movieBookingSystem.bookTicket("10:00 AM", five);
    movieBookingSystem.bookTicket("10:00 AM", oneHundred);
    movieBookingSystem.cancelReservation("10:00 AM", three);
    movieBookingSystem.bookTicket("1:00 PM", two);
    movieBookingSystem.cancelReservation("1:00 PM", five);
  }
}
