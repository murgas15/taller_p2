package model;

public abstract class Service {
    protected String code;
    protected String name;
    protected String description;
    protected String availabilityDate;
    protected String startTime;
    protected String endTime;
    protected int    totalSlots;
    protected int    remainingSlots;
    protected double basePrice;
    protected String status; // "Disponible", "En Curso", "Finalizado", "Cancelado"

    public Service(String code, String name, String description,
                   String availabilityDate, String startTime, String endTime,
                   int totalSlots, double basePrice) {
        this.code             = code;
        this.name             = name;
        this.description      = description;
        this.availabilityDate = availabilityDate;
        this.startTime        = startTime;
        this.endTime          = endTime;
        this.totalSlots       = totalSlots;
        this.remainingSlots   = totalSlots;
        this.basePrice        = basePrice;
        this.status           = "Disponible";
    }

    // Abstract methods — each subclass implements differently (POLYMORPHISM)
    public abstract double calculateFinalPrice();
    public abstract String getServiceType();

    public String getCode()             { return code; }
    public String getName()             { return name; }
    public String getDescription()      { return description; }
    public String getAvailabilityDate() { return availabilityDate; }
    public String getStartTime()        { return startTime; }
    public String getEndTime()          { return endTime; }
    public int    getTotalSlots()       { return totalSlots; }
    public int    getRemainingSlots()   { return remainingSlots; }
    public double getBasePrice()        { return basePrice; }
    public String getStatus()           { return status; }

    public void setRemainingSlots(int remainingSlots) { this.remainingSlots = remainingSlots; }
    public void setStatus(String status)              { this.status = status; }

    public void reduceSlots(int amount)  { this.remainingSlots -= amount; }
    public void returnSlots(int amount)  { this.remainingSlots += amount; }

    @Override
    public String toString() {
        return "=== " + getServiceType().toUpperCase() + " ===" +
                "\nCódigo      : " + code +
                "\nNombre      : " + name +
                "\nDescripción : " + description +
                "\nFecha       : " + availabilityDate +
                "\nHorario     : " + startTime + " - " + endTime +
                "\nCupos       : " + remainingSlots + "/" + totalSlots +
                "\nPrecio base : $" + String.format("%,.0f", basePrice) +
                "\nPrecio final: $" + String.format("%,.0f", calculateFinalPrice()) +
                "\nEstado      : " + status;
    }
}