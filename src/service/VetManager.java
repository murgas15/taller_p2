package service;

import model.*;
import exception.*;
import java.util.ArrayList;

public class VetManager {

    private ArrayList<Owner>       ownerList       = new ArrayList<>();
    private ArrayList<Service>     serviceList     = new ArrayList<>();
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    // ======================== OWNERS ========================

    public void registerOwner(String idNumber, String firstName, String lastName,
                              String email, String phone, String address,
                              String petName, String petSpecies,
                              String petBreed, int petAge)
            throws DuplicateOwnerException, InvalidEmailException, PetVetException {

        if (idNumber.isBlank() || firstName.isBlank() || lastName.isBlank() ||
                email.isBlank() || phone.isBlank() || address.isBlank() ||
                petName.isBlank() || petSpecies.isBlank() || petBreed.isBlank()) {
            throw new PetVetException("Todos los campos son obligatorios");
        }
        if (petAge < 0) {
            throw new PetVetException("La edad de la mascota debe ser mayor o igual a 0");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailException();
        }
        for (Owner o : ownerList) {
            if (o.getIdNumber().equals(idNumber)) {
                throw new DuplicateOwnerException();
            }
        }
        ownerList.add(new Owner(idNumber, firstName, lastName, email, phone,
                address, petName, petSpecies, petBreed, petAge));
        System.out.println("✔ Dueño registrado exitosamente.");
    }

    public Owner findOwner(String idNumber) throws OwnerNotFoundException {
        for (Owner o : ownerList) {
            if (o.getIdNumber().equals(idNumber)) return o;
        }
        throw new OwnerNotFoundException();
    }

    public int getTotalOwners() {
        return ownerList.size();
    }

    public void listOwners() {
        if (ownerList.isEmpty()) {
            System.out.println("No hay dueños registrados.");
            return;
        }
        for (Owner o : ownerList) {
            System.out.println(o);
            System.out.println("-----------------------------");
        }
    }

    // ======================== SERVICES ========================

    public void registerBasicService(String code, String name, String description,
                                     String date, String startTime, String endTime,
                                     int slots, double basePrice,
                                     int duration, boolean certificate)
            throws DuplicateServiceException, InvalidPriceException, PetVetException {

        validateService(code, slots, basePrice);
        serviceList.add(new BasicService(code, name, description, date,
                startTime, endTime, slots, basePrice,
                duration, certificate));
        System.out.println("✔ Servicio básico registrado exitosamente.");
    }

    public void registerSpecializedService(String code, String name, String description,
                                           String date, String startTime, String endTime,
                                           int slots, double basePrice, String specialty,
                                           boolean exams, double extraCharge)
            throws DuplicateServiceException, InvalidPriceException, PetVetException {

        validateService(code, slots, basePrice);
        serviceList.add(new SpecializedService(code, name, description, date,
                startTime, endTime, slots, basePrice,
                specialty, exams, extraCharge));
        System.out.println("✔ Servicio especializado registrado exitosamente.");
    }

    private void validateService(String code, int slots, double basePrice)
            throws DuplicateServiceException, InvalidPriceException, PetVetException {
        if (basePrice <= 0) throw new InvalidPriceException();
        if (slots <= 0)     throw new PetVetException("Los cupos totales deben ser mayor a 0");
        for (Service s : serviceList) {
            if (s.getCode().equals(code)) throw new DuplicateServiceException();
        }
    }

    public Service findService(String code) throws PetVetException {
        for (Service s : serviceList) {
            if (s.getCode().equals(code)) return s;
        }
        throw new PetVetException("No se encontró el servicio con código: " + code);
    }

    public void listServices() {
        if (serviceList.isEmpty()) {
            System.out.println("No hay servicios registrados.");
            return;
        }
        // POLYMORPHISM: toString() and getServiceType() resolve dynamically
        for (Service s : serviceList) {
            System.out.println(s);
            System.out.println("-----------------------------");
        }
    }

    // ======================== APPOINTMENTS ========================

    public void bookAppointment(String appointmentCode, String idNumber,
                                String serviceCode, int slots, String date)
            throws PetVetException {

        if (slots < 1 || slots > 3) throw new ExceededSlotsException();

        Owner   owner   = findOwner(idNumber);
        Service service = findService(serviceCode);

        if (!service.getStatus().equals("Disponible")) throw new ServiceNotAvailableException();
        if (service.getRemainingSlots() < slots)       throw new NoSlotsAvailableException();

        for (Appointment a : appointmentList) {
            if (a.getCode().equals(appointmentCode)) throw new DuplicateAppointmentException();
        }

        Appointment appointment = new Appointment(appointmentCode, owner, service, slots, date);
        service.reduceSlots(slots);
        appointmentList.add(appointment);

        System.out.println("✔ Cita agendada exitosamente.");
        System.out.println(appointment);
    }

    public void cancelAppointment(String appointmentCode) throws AppointmentNotFoundException {
        for (Appointment a : appointmentList) {
            if (a.getCode().equals(appointmentCode)) {
                if (a.getStatus().equals("Cancelada")) {
                    System.out.println("La cita ya estaba cancelada.");
                    return;
                }
                a.setStatus("Cancelada");
                a.getService().returnSlots(a.getBookedSlots());
                System.out.println("✔ Cita cancelada. Cupos devueltos al servicio "
                        + a.getService().getCode() + ".");
                return;
            }
        }
        throw new AppointmentNotFoundException();
    }

    public void findAppointmentByCode(String appointmentCode) throws AppointmentNotFoundException {
        for (Appointment a : appointmentList) {
            if (a.getCode().equals(appointmentCode)) {
                System.out.println(a);
                return;
            }
        }
        throw new AppointmentNotFoundException();
    }

    public void listAppointmentsByOwner(String idNumber) throws OwnerNotFoundException {
        findOwner(idNumber); // verify owner exists
        boolean found = false;
        for (Appointment a : appointmentList) {
            if (a.getOwner().getIdNumber().equals(idNumber)) {
                System.out.println(a);
                System.out.println("-----------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("El dueño no tiene citas registradas.");
    }
}