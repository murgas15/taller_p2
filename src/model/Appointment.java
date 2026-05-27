package model;

public class Appointment {
    private String  code;
    private Owner   owner;
    private Service service;
    private int     bookedSlots;
    private String  bookingDate;
    private double  totalPrice;
    private String  status; // "Confirmada", "Cancelada", "Completada"

    public Appointment(String code, Owner owner, Service service,
                       int bookedSlots, String bookingDate) {
        this.code        = code;
        this.owner       = owner;
        this.service     = service;
        this.bookedSlots = bookedSlots;
        this.bookingDate = bookingDate;
        // POLYMORPHISM: calculateFinalPrice() resolves at runtime
        this.totalPrice  = service.calculateFinalPrice() * bookedSlots;
        this.status      = "Confirmada";
    }

    public String  getCode()        { return code; }
    public Owner   getOwner()       { return owner; }
    public Service getService()     { return service; }
    public int     getBookedSlots() { return bookedSlots; }
    public String  getBookingDate() { return bookingDate; }
    public double  getTotalPrice()  { return totalPrice; }
    public String  getStatus()      { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "=== CITA ===" +
                "\nCódigo cita  : " + code +
                "\nDueño        : " + owner.getFirstName() + " " + owner.getLastName() +
                "\nCédula       : " + owner.getIdNumber() +
                "\nMascota      : " + owner.getPetName() +
                "\nServicio     : " + service.getName() + " (" + service.getCode() + ")" +
                "\nTipo         : " + service.getServiceType() +
                "\nCupos        : " + bookedSlots +
                "\nFecha agendada: " + bookingDate +
                "\nPrecio total : $" + String.format("%,.0f", totalPrice) +
                "\nEstado       : " + status;
    }
}