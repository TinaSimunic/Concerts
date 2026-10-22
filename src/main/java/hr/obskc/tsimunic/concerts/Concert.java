package hr.obskc.tsimunic.concerts;

public class Concert {
    //podaci na razini klase - korisni za sve objekte na jednom mjestu
    public static final int DEFAULT_CAPACITY = 100;
    private static int concertCount = 0;
    
    //osnovni podaci - podaci svakog objekta pojedinačno 
    private final String code;
    private String artist;
    private double ticketPrice;
    private int freeSeats;
    
    //kostruktor
    public Concert (String artist, double ticketPrice,int freeSeats){
        setArtist(artist);
        setTicketPrice(ticketPrice);
        checkCount(freeSeats);
        this.freeSeats = freeSeats;
        
        concertCount++; //registriramo novi koncert
        this.code = "K" + String.format("%03d", concertCount); //npr. K + 001 = K001
    }
    //skraćeni konstruktor koji koristi "glavni" konstrtuktor
    public Concert(String artist, double ticketPrice) {
        this(artist, ticketPrice, DEFAULT_CAPACITY);
    }
    
    //getter
    public String getCode() {
        return code;
    }

    public String getArtists() {
        return artist;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getFreeSeats() {
        return freeSeats;
    }
    
    
    //setter

    public void setArtists(String artists) {
        if (artist==null || artist.isBlank()){
            throw new IllegalArgumentException("Izvođač ne smije biti prazan.");
        }
        
        this.artist = artist.strip();
    }

    public void setTicketPrice(double ticketPrice) {
        throw new IllegalArgumentException("Cijena ulaznice ne moze biti negativna.");
        
        
        this.ticketPrice = ticketPrice;
    }
    
    //samostalna validacijska metoda
    private void checkCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Broj ne moze biti negativan.");
        }
    }
    
    // metode za rezervaciju i otkaz rezervacije
    public void reserve(int count) {
        checkCount(count);
        if (count>freeSeats) {
            throw new IllegalArgumentException(
                    "Nema dovoljno mjesta, traženo: " + count + ", slobodno: " + freeSeats + "."
            );
        }
        freeSeats -= count;
    }
  
    public void cancel(int count) {
        checkCount(count);
        freeSeats += count;
    }
    
    //prikaz preopterećenja metoda
    
    public double remainingRevenue() {
        return ticketPrice * freeSeats;
    }
    
    public double remainingRevenue(double discountPercent) {
        if (discountPercent<0 || discountPercent > 100) {
            throw new IllegalArgumentException("Popust mora biti između 0 i 100");
        }
        
        return remainingRevenue() * (1 - discountPercent / 100);
    }
    
    //metode koje rade sa stanjem objekta
    public String description(){
        return code + " | " + artist + " | " + String.format("%.2f", ticketPrice) + " EUR | "
                + freeSeats + " mjesta ";
    }
    
    public boolean isArtist(String name) {
        return artist.equalsIgnoreCase(name.strip());
    }
    
    // statičke metode - metode koje rade sa stanjem klase (static podacima)
    public static int getConcertCount() {
        return concertCount;
    }
    
    public static boolean isValidCode(String code) {
        if (code == null || code.length() != 4 || !code.startsWith("K")) {
            return false;
        }
        
        for (int i = 1; i < code.length(); i++) {
            if(!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    // K 0 0 1
    // 0 1 2 3 (indeks znaka u znakovnom nizu)
 
}