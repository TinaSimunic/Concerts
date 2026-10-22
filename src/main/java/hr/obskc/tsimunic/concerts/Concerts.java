package hr.obskc.tsimunic.concerts;

public class Concerts {
    
    public static void main(String[] args) {
        Concert first = new Concert("Prljavo kazalište", 25.00, 120);
        Concert second = new Concert("Gibonni", 32.50, 80);
        
        System.out.println(first.description());
        System.out.println(second.description());
        
        Concert third = new Concert("Hladno Pivo", 20);
        
        System.out.println(third.description());
        
        first.reserve(20);
        third.cancel(5);
        second.setTicketPrice(30);
        System.out.println(first.getArtist() + ": slobodno još " + first.getFreeSeats() + " mjesta");
        
        try {
        second.reserve(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: "  + e.getMessage());
        }
        
        try {
            new Concert("       ", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: "  + e.getMessage());
        }
        
        try {
            third.setTicketPrice(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: "  + e.getMessage());
        }
        
        System.out.printf("Preostala zarada (%s): %.2f EUR%n", second.getArtists(), second.remainingRevanue());
        System.out.printf("Preostala zarada uz 20 %% popusta (%s): %.2f EUR%n", second.getArtists(), second.remainingRevanue(20));
        
        System.out.println("Broj koncerata: " + Concert.getConcertCount());
        System.out.println("Zadani kapacitet: " + Concert.DEFAULT_CAPACITY);
        System.out.println("Je li K042 ispravna šifra? " + Concert.isValidCode("K042"));
        
        Concert[] program = {first, second, third};
        int totalFreeSeats = 0;
        for (Concert c : program) {
            totalFreeSeats += c.getFreeSeats();
        }
        System.out.println("Ukupno slobodnih mjesta: " + totalFreeSeats);
        
        Concert  same = first;
        same.reserve(10);
        System.out.println("Preko varijable first: " + first.getFreeSeats() + "mjesta");
        
        int seats = 50;
        doubleIt(seats);
        System.out.println("Varijabla seats nakon 'dupliranja': " + seats);
        
        raisePrice(third);
        System.out.println("Cijena nakon podizanja cijene: " + third.getTicketPrice());
        
        replace(third);
        System.out.println("Izvođač nakon zamjene: " + third.getArtists());
        
        
        String typed = new String("gibonni");
        System.out.println("== :" + (typed == "gibonni"));
        System.out.println("equals: " + typed.equals("gibonni"));
        System.out.println("isArtist: " + second.isArtist(typed));
        
        String name = "gibonni";
        name.toUpperCase();
        System.out.println("Provjera imena: " + newName);
        
        StringBuilder poster = new StringBuilder("Program: ");
        for(Concert c : program){
            poster.append(c.getArtists()).append("; ");
        }
        System.out.println(poster);
        
    }
    
    static void doubleIt(int x) {
        x = x * 2;
    }
    
    static void raisePrice(Concert c){
        c.setTicketPrice(c.getTicketPrice() + 5);
    }
    
    static void replace(Concert c) {
        c = new Concert("Netko drugi", 1.00);
    }
}


    
