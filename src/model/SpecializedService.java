package model;

public class SpecializedService extends Service {
    private String  specialty;
    private boolean requiresPriorExams;
    private double  additionalCharge;

    public SpecializedService(String code, String name, String description,
                              String availabilityDate, String startTime, String endTime,
                              int totalSlots, double basePrice,
                              String specialty, boolean requiresPriorExams, double additionalCharge) {
        super(code, name, description, availabilityDate, startTime, endTime,
                totalSlots, basePrice);
        this.specialty          = specialty;
        this.requiresPriorExams = requiresPriorExams;
        this.additionalCharge   = additionalCharge;
    }

    @Override
    public double calculateFinalPrice() {
        return basePrice + additionalCharge;
    }

    @Override
    public String getServiceType() {
        return "Servicio Especializado";
    }

    public String  getSpecialty()          { return specialty; }
    public boolean isRequiresPriorExams()  { return requiresPriorExams; }
    public double  getAdditionalCharge()   { return additionalCharge; }

    @Override
    public String toString() {
        return super.toString() +
                "\nEspecialidad: " + specialty +
                "\nExámenes    : " + (requiresPriorExams ? "Sí" : "No") +
                "\nCargo extra : $" + String.format("%,.0f", additionalCharge);
    }
}