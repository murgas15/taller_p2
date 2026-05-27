package model;

public class BasicService extends Service {
    private int     durationMinutes;
    private boolean includesCertificate;

    public BasicService(String code, String name, String description,
                        String availabilityDate, String startTime, String endTime,
                        int totalSlots, double basePrice,
                        int durationMinutes, boolean includesCertificate) {
        super(code, name, description, availabilityDate, startTime, endTime,
                totalSlots, basePrice);
        this.durationMinutes     = durationMinutes;
        this.includesCertificate = includesCertificate;
    }

    @Override
    public double calculateFinalPrice() {
        return basePrice; // no extra charge
    }

    @Override
    public String getServiceType() {
        return "Servicio Básico";
    }

    public int     getDurationMinutes()     { return durationMinutes; }
    public boolean isIncludesCertificate()  { return includesCertificate; }

    @Override
    public String toString() {
        return super.toString() +
                "\nDuración    : " + durationMinutes + " min" +
                "\nCertificado : " + (includesCertificate ? "Sí" : "No");
    }
}