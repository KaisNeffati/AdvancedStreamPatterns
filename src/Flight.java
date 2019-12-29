import java.time.LocalDate;

public class Flight {
    private LocalDate localDate;
    private String number;
    private String origin;
    private String dest;

    public Flight(LocalDate localDate, String number, String origin, String dest) {
        this.localDate = localDate;
        this.number = number;
        this.origin = origin;
        this.dest = dest;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "localDate=" + localDate +
                ", number='" + number + '\'' +
                ", origin='" + origin + '\'' +
                ", dest='" + dest + '\'' +
                '}';
    }
}
