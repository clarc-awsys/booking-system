package movie.reservation.app;
import java.util.ArrayList;
public final class MovieBookingSystem extends BookingSystem {
  MovieBookingSystem() {
    super();
  }
  /**
   * Stores all available shows.
   */
  private static ArrayList<Movie> movieSchedule = new ArrayList<>();
  /**
   * Getter of movieSchedule.
   * @return ArrayList of available shows.
   */
  public static ArrayList<Movie> getMovieSchedule() {
    return movieSchedule;
  }
  /**
   * Add all shows.
   */
  public void addMovieSchedules() {
    final int thirtySeven = 37;
    final int sixtySeven = 67;
    movieSchedule.add(new Movie(thirtySeven, "10:00 AM"));
    movieSchedule.add(new Movie(sixtySeven, "11:00 AM"));
    movieSchedule.add(new Movie(sixtySeven, "12:00 PM"));
    movieSchedule.add(new Movie(thirtySeven, "1:00 PM"));
    movieSchedule.add(new Movie(sixtySeven, "2:00 PM"));
    movieSchedule.add(new Movie(sixtySeven, "3:00 PM"));
    movieSchedule.add(new Movie(sixtySeven, "4:00 PM"));
    movieSchedule.add(new Movie(thirtySeven, "5:00 PM"));
    movieSchedule.add(new Movie(thirtySeven, "6:00 PM"));
    movieSchedule.add(new Movie(sixtySeven, "7:00 PM"));
    movieSchedule.add(new Movie(sixtySeven, "8:00 PM"));
    movieSchedule.add(new Movie(thirtySeven, "9:00 PM"));
  }
  /**
   * Returns the availability of the given showTime.
   */
  @Override
  public boolean checkAvailability(final String showTime) {
    for (Movie movie:movieSchedule) {
      if (movie.thisSchedule.equals(showTime)
          && movie.thisTicketsAvailable > 0) {
        return true;
      }
    }
    System.out.println("No tickets available for this showtime");
    return false;
  }

  @Override
  public void bookTicket(final String showTime, final int tickets) {
    if (!checkAvailability(showTime)) {
      return;
    }
    for (Movie movies : movieSchedule) {
      if (movies.thisSchedule.equals(showTime)) {
        if (movies.thisTicketsAvailable < tickets) {
          System.out.println("Not enough tickets available for this showtime");
          return;
        }
        movies.setTicketsBooked(tickets);
        movies.setTicketsAvailable(movies.getTicketsAvailable() - tickets);
        System.out.println(tickets + " ticket(s) successfully booked for "
        + showTime);
      }
    }
  }

  @Override
  void cancelReservation(final String showTime, final int tickets) {
    for (Movie movies : movieSchedule) {
      if (movies.thisSchedule.equals(showTime)) {
        if (tickets > movies.getTicketsBooked()) {
          System.out.println("Invalid operation (Attempt to cancel "
              + "more tickets than booked)");
          return;
        }
        movies.setTicketsBooked(movies.getTicketsBooked() - tickets);
        movies.setTicketsAvailable(movies.getTicketsAvailable() + tickets);
        System.out.println(tickets + " ticket(s) successfully cancelled for "
        + showTime);
      }
    }
  }
  /**
   * Displays all available shows.
   */
  public void showAvailableShows() {
    for (Movie movies : movieSchedule) {
      System.out.println("Time: " + movies.getSchedule()
      + " Tickets Available - " + movies.getTicketsAvailable());
    }
  }
  /**
   * Movie Class.
   */
  class Movie {
    /**
     * Number of tickets available.
     */
    private int thisTicketsAvailable;
    /**
     * Schedule of show.
     */
    private String thisSchedule;
    /**
     * Number of tickets booked.
     */
    private int thisTicketsBooked;
    /**
     * Public Constructor.
     * @param ticketsAvailable
     * @param schedule
     */
    Movie(final int ticketsAvailable, final String schedule) {
      thisTicketsAvailable = ticketsAvailable;
      thisSchedule = schedule;
      thisTicketsBooked = 0;
    }
    public int getTicketsBooked() {
      return thisTicketsBooked;
    }
    public void setTicketsBooked(final int ticketsBooked) {
      thisTicketsBooked = ticketsBooked;
    }
    public int getTicketsAvailable() {
      return thisTicketsAvailable;
    }
    public void setTicketsAvailable(final int ticketsAvailable) {
      thisTicketsAvailable = ticketsAvailable;
    }
    public String getSchedule() {
      return thisSchedule;
    }
    public void setSchedule(final String schedule) {
      thisSchedule = schedule;
    }
  }

}
