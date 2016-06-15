package Paysheets;

import Employee.Cashier;
import Employee.Employee;
import Payments.Payment;
import Payments.paymentHistory;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Xavier
 */
//Clase que contiene una lista de empleados, de manera que funciona como la case planilla, donde se guarda la informacion de todos los empleados registrados y contiene las respectivas 
//funciones para realizar pagos
public class Paysheet {

    public static LinkedList<Employee> employeeList = new LinkedList<>();
    private String name;

    public Paysheet(String nameP) {
        this.name = nameP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Funcion que recibe el ID de un empleado, busca en lista, y retorna ese objeto de tipo empleado
    public static Employee getEmployee(String numCedulaP) {
        for (Employee employeeList1 : employeeList) {
            if (employeeList1.getIdNumber().equals(numCedulaP) == true) {
                return employeeList1;
            }
        }
        return null;
    }

    //Funcion que recibe un objeto de tipo empleado y lo inserta en la lista de empleados 

    public static void addEmployee(Employee empleadoP) {
        employeeList.add(empleadoP);
    }

    //Funcion que recibe un numero de ID, una cantidad de horas, busca en la lista de empleados el objeto con ese id, y agrega las horas como horas extras
    public static void addExtraHours(String idNumberP, int hours) {
        if (hours > 0) {
            for (Employee employeeList1 : employeeList) {
                if (employeeList1.getIdNumber().equals(idNumberP) == true) {
                    employeeList1.addExtraHours(hours);
                }
            }
        }
    }
    
    public  static Employee search(String id){
        
        for (Employee employeeList1 : employeeList) {
            if(employeeList1==null){
                return null;
            }
            if (employeeList1.getIdNumber().equals(id)) {
                return employeeList1;
            }
        }
        return null;
    }
    
    public static String infoEmployee(String id){
        Cashier cashier=(Cashier)search(id);
        if(cashier==null){
            return null;
        }
        return cashier.toString();
    }
    
    //Funcion que recibe un numero de ID, busca en la lista de empleados el objeto con ese id y lo elimina
    public static void deleteEmployee(String idNumberP) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getIdNumber().equals(idNumberP) == true) {
                employeeList.remove(i);
            }
        }
    }

    //Funcion que recibe toda la informacion de un empleado, busca en la lista de empleados el objeto con ese id y reemplaza toda la informacion con la nueva informacion recibida

    public static void modifyEmployee(String Name, String dateOfBirth, String gender, String phoneNumber, String email, String address, String idNumberP, String payment, int pph, int workHoursP) {
        if(pph<0 || workHoursP<0){
            throw new ArithmeticException("Ingreso de horas menores que cero");
        }
        for(int i=0;i<employeeList.size();i++)
        {
            if(employeeList.get(i).getIdNumber().equals(idNumberP)==true)
            {
                employeeList.get(i).setAddress(address);
                employeeList.get(i).setDateOfBirth(dateOfBirth);
                employeeList.get(i).setEmail(email);
                employeeList.get(i).setGender(gender);
                employeeList.get(i).setName(Name);
                employeeList.get(i).setPaymentPerHour(pph);
                employeeList.get(i).setPaymentType(payment);
                employeeList.get(i).setPhoneNumber(phoneNumber);
                employeeList.get(i).setWorkHours(workHoursP);
            }
        }
    }

    //Funcion que realiza el pago a todos los empleados cuya forma de pago es semanal, y retorna un String con toda la informacion de los pagos realizados
    public static String WeeklyPayroll() throws Exception {
        double total = 0;
        double salary = 0;
        String payroll;
        boolean empty = true;
        Date date = new Date();
        payroll = "--------------------Weekly Payroll--------------------\n";
        for (int i = 0; i < Paysheet.employeeList.size(); i++) {
            Employee emp = Paysheet.employeeList.get(i);
            if ("Weekly".equals(emp.getPaymentType())) {
                empty = false;
                salary = (emp.getWorkHours() * emp.getPaymentPerHour()) * 7;
                salary += emp.getExtraHours() * (emp.getPaymentPerHour() * 2);
                total = total + salary;
                Paysheet.getEmployee(emp.getIdNumber()).resetExtraHours();
                payroll += "Employee: " + emp.getName() + " Weekly Salary: " + salary + "\n";
                Payment pay = new Payment(date, emp, salary);
                paymentHistory.addPayment(pay);
            }
        }
        payroll += ("Total paid this week: " + total);
        payroll += ("\n-------------------------End-------------------------");
        if (empty == true) {
            return null;
        } else {
            return payroll;
        }
    }

    //Funcion que realiza el pago a todos los empleados cuya forma de pago es quincenal, y retorna un String con toda la informacion de los pagos realizados
    public static String BiweeklyPayroll() throws Exception {
        double total = 0;
        double salary = 0;
        String payroll;
        boolean empty = true;
        Date date = new Date();
        payroll = "--------------------Biweekly Payroll--------------------\n";
        for (int i = 0; i < Paysheet.employeeList.size(); i++) {
            Employee emp = Paysheet.employeeList.get(i);
            if ("Biweekly".equals(emp.getPaymentType())) {
                empty = false;
                salary = (emp.getWorkHours() * emp.getPaymentPerHour()) * 14;
                salary += emp.getExtraHours() * (emp.getPaymentPerHour() * 2);
                total = total + salary;
                Paysheet.getEmployee(emp.getIdNumber()).resetExtraHours();
                payroll += "Employee: " + emp.getName() + " Biweekly Salary: " + salary + "\n";
                Payment pay = new Payment(date, emp, salary);
                paymentHistory.addPayment(pay);
            }
        }
        payroll += ("Total paid this Biweek: " + total);
        payroll += ("\n-------------------------End-------------------------");
        if (empty == true) {
            return null;
        } else {
            return payroll;
        }
    }

    //Funcion que realiza el pago a todos los empleados cuya forma de pago es mensual, y retorna un String con toda la informacion de los pagos realizados

    public static String MonthlyPayroll() throws Exception {
        double total = 0;
        double salary = 0;
        String payroll;
        boolean empty = true;
        Date date = new Date();
        payroll = "--------------------Monthly Payroll--------------------\n";
        for (int i = 0; i < Paysheet.employeeList.size(); i++) {
            Employee emp = Paysheet.employeeList.get(i);
            if ("Monthly".equals(emp.getPaymentType())) {
                empty = false;
                salary = (emp.getWorkHours() * emp.getPaymentPerHour()) * 30;
                salary += emp.getExtraHours() * (emp.getPaymentPerHour() * 2);
                total = total + salary;
                Paysheet.getEmployee(emp.getIdNumber()).resetExtraHours();
                payroll += "Employee: " + emp.getName() + " Monthly Salary: " + salary + "\n";
                Payment pay = new Payment(date, emp, salary);
                paymentHistory.addPayment(pay);
            }
        }
        payroll += ("Total paid this month: " + total);
        payroll += ("\n-------------------------End-------------------------");
        if (empty == true) {
            return null;
        } else {
            return payroll;
        }
    }
}
