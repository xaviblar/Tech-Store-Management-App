package Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Xavier
 */
//Clase tipo abstract que hereda a los tipos de empleados con los que cuenta la tienda
public abstract class Employee {
    private String Name;
    private String dateOfBirth;
    private String Gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String idNumber;
    private String paymentType;
    private int paymentPerHour;
    private int workHours;
    private int extraHours;

    public Employee(String Name, String DateOfBirth, String gender, String phoneNumber, String email, String direccion, String idNumber,String payment, int pph,int workHoursP) throws Exception {
        String EMAIL_REGEX="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        if(!(email.matches(EMAIL_REGEX))){
            throw new Exception("Formato de correo incorrecto");
        }
       
        try{
            date=sdf.parse(DateOfBirth);
        }
        catch(Exception e){
            throw new Exception("Formato de fecha incorrecto");
        }
        if(pph<0){
            throw new Exception("Pago por hora menor que cero");
        }
        this.Name = Name;
        this.dateOfBirth = DateOfBirth;
        this.Gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = direccion;
        this.idNumber = idNumber;
        this.paymentType=payment;
        this.paymentPerHour=pph;
        this.extraHours=0;
        this.workHours=workHoursP;
    }
    
    
    public String getName() {
        return Name;
    }

    public void setName(String Nombre) {
        this.Name = Nombre;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String FechaNac) {
        this.dateOfBirth = FechaNac;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Sexo) {
        this.Gender = Sexo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String numTelefono) {
        this.phoneNumber = numTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Direccion) {
        this.address = Direccion;
    }

    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(int paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void addExtraHours(int extraHours) {
        this.extraHours += extraHours;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }
    
    public void resetExtraHours()
    {
        this.extraHours=0;
    }
    
    @Override
    public String toString() {
        return "Employee{\n" + "Name=" + Name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", idNumber=" + idNumber + '}';
    }
}
