

import service.VetManager;
import exception.*;
import java.util.Scanner;

public class Main {

    static Scanner    sc         = new Scanner(System.in);
    static VetManager vetManager = new VetManager();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   SISTEMA PETVET COLOMBIA - POO    ║");
        System.out.println("╚════════════════════════════════════╝");

        int option;
        do {
            showMenu();
            option = readInt("Seleccione una opción: ");
            processOption(option);
        } while (option != 0);

        System.out.println("¡Hasta luego!");
    }

    static void showMenu() {
        System.out.println("\n========= MENÚ PRINCIPAL =========");
        System.out.println("--- DUEÑOS ---");
        System.out.println(" 1. Registrar dueño");
        System.out.println(" 2. Buscar dueño por cédula");
        System.out.println(" 3. Total de dueños registrados");
        System.out.println(" 4. Listar todos los dueños");
        System.out.println("--- SERVICIOS ---");
        System.out.println(" 5. Registrar servicio BÁSICO");
        System.out.println(" 6. Registrar servicio ESPECIALIZADO");
        System.out.println(" 7. Listar todos los servicios");
        System.out.println("--- CITAS ---");
        System.out.println(" 8. Agendar cita");
        System.out.println(" 9. Cancelar cita");
        System.out.println("10. Buscar cita por código");
        System.out.println("11. Citas por dueño");
        System.out.println("-----------------------------------");
        System.out.println(" 0. Salir");
        System.out.println("===================================");
    }

    static void processOption(int option) {
        try {
            switch (option) {
                case 1  -> registerOwner();
                case 2  -> searchOwner();
                case 3  -> System.out.println("Total de dueños: " + vetManager.getTotalOwners());
                case 4  -> vetManager.listOwners();
                case 5  -> registerBasicService();
                case 6  -> registerSpecializedService();
                case 7  -> vetManager.listServices();
                case 8  -> bookAppointment();
                case 9  -> cancelAppointment();
                case 10 -> searchAppointment();
                case 11 -> appointmentsByOwner();
                case 0  -> {}
                default -> System.out.println("Opción no válida.");
            }
        } catch (PetVetException e) {
            // Catches all system exceptions through the hierarchy
            System.out.println("⚠ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠ Error inesperado: " + e.getMessage());
        }
    }



    static void registerOwner() throws PetVetException {
        System.out.println("\n-- REGISTRAR DUEÑO --");
        String idNumber   = readText("Cédula: ");
        String firstName  = readText("Nombres: ");
        String lastName   = readText("Apellidos: ");
        String email      = readText("Email: ");
        String phone      = readText("Teléfono: ");
        String address    = readText("Dirección: ");
        System.out.println("-- DATOS DE LA MASCOTA --");
        String petName    = readText("Nombre mascota: ");
        System.out.println("Especie (perro/gato/conejo/ave/otro):");
        String petSpecies = readText("Especie: ");
        String petBreed   = readText("Raza: ");
        int    petAge     = readInt("Edad : ");

        vetManager.registerOwner(idNumber, firstName, lastName, email, phone,
                address, petName, petSpecies, petBreed, petAge);
    }

    static void searchOwner() throws PetVetException {
        String idNumber = readText("Cédula del dueño: ");
        System.out.println(vetManager.findOwner(idNumber));
    }


    static void registerBasicService() throws PetVetException {
        System.out.println("\n-- REGISTRAR SERVICIO BÁSICO --");
        String code        = readText("Código (ej: SV001): ");
        String name        = readText("Nombre: ");
        String description = readText("Descripción: ");
        String date        = readText("Fecha disponibilidad (DD/MM/YYYY): ");
        String startTime   = readText("Hora inicio (HH:MM): ");
        String endTime     = readText("Hora fin (HH:MM): ");
        int    slots       = readInt("Cupos totales: ");
        double basePrice   = readDouble("Precio base: $");
        int    duration    = readInt("Duración en minutos: ");
        String cert        = readText("¿Incluye certificado de salud? (s/n): ");

        vetManager.registerBasicService(code, name, description, date, startTime, endTime,
                slots, basePrice, duration, cert.equalsIgnoreCase("s"));
    }

    static void registerSpecializedService() throws PetVetException {
        System.out.println("\n-- REGISTRAR SERVICIO ESPECIALIZADO --");
        String code        = readText("Código (ej: SV050): ");
        String name        = readText("Nombre: ");
        String description = readText("Descripción: ");
        String date        = readText("Fecha disponibilidad (DD/MM/YYYY): ");
        String startTime   = readText("Hora inicio (HH:MM): ");
        String endTime     = readText("Hora fin (HH:MM): ");
        int    slots       = readInt("Cupos totales: ");
        double basePrice   = readDouble("Precio base: $");
        String specialty   = readText("Especialidad: ");
        String exams       = readText("¿Requiere exámenes previos? (s/n): ");
        double extraCharge = readDouble("Cargo adicional por especialización: $");

        vetManager.registerSpecializedService(code, name, description, date, startTime, endTime,
                slots, basePrice, specialty,
                exams.equalsIgnoreCase("s"), extraCharge);
    }



    static void bookAppointment() throws PetVetException {
        System.out.println("\n-- AGENDAR CITA --");
        String code        = readText("Código de cita (ej: C001): ");
        String idNumber    = readText("Cédula del dueño: ");
        String serviceCode = readText("Código del servicio: ");
        int    slots       = readInt("Cantidad de cupos (1-3): ");
        String date        = readText("Fecha de la cita (DD/MM/YYYY): ");

        vetManager.bookAppointment(code, idNumber, serviceCode, slots, date);
    }

    static void cancelAppointment() throws PetVetException {
        String code = readText("Código de la cita a cancelar: ");
        vetManager.cancelAppointment(code);
    }

    static void searchAppointment() throws PetVetException {
        String code = readText("Código de la cita: ");
        vetManager.findAppointmentByCode(code);
    }

    static void appointmentsByOwner() throws PetVetException {
        String idNumber = readText("Cédula del dueño: ");
        vetManager.listAppointmentsByOwner(idNumber);
    }



    static String readText(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese un número entero válido.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese un número válido.");
            }
        }
    }
}