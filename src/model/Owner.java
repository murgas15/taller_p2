package model;

public class Owner {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String petName;
    private String petSpecies;
    private String petBreed;
    private int    petAge;

    public Owner(String idNumber, String firstName, String lastName, String email,
                 String phone, String address, String petName,
                 String petSpecies, String petBreed, int petAge) {
        this.idNumber   = idNumber;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.phone      = phone;
        this.address    = address;
        this.petName    = petName;
        this.petSpecies = petSpecies;
        this.petBreed   = petBreed;
        this.petAge     = petAge;
    }

    public String getIdNumber()   { return idNumber; }
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getEmail()      { return email; }
    public String getPhone()      { return phone; }
    public String getAddress()    { return address; }
    public String getPetName()    { return petName; }
    public String getPetSpecies() { return petSpecies; }
    public String getPetBreed()   { return petBreed; }
    public int    getPetAge()     { return petAge; }

    public void setEmail(String email)     { this.email = email; }
    public void setPhone(String phone)     { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "=== DUEÑO ===" +
                "\nCédula      : " + idNumber +
                "\nNombre      : " + firstName + " " + lastName +
                "\nEmail       : " + email +
                "\nTeléfono    : " + phone +
                "\nDirección   : " + address +
                "\nMascota     : " + petName +
                "\nEspecie     : " + petSpecies +
                "\nRaza        : " + petBreed +
                "\nEdad mascota: " + petAge + " año(s)";
    }
}