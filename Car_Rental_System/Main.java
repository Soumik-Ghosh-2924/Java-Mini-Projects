package Car_Rental_System;
import java.util.*;
class Car
{
    private String car_id;
    private String Model;
    private String Brand;
    private double Base_Priceperday;
    private boolean isAvailable;

    Car(String c_id, String model, String brand, double base_priceperday )
    {
        this.car_id=c_id;
        this.Model=model;
        this.Brand=brand;
        this.Base_Priceperday=base_priceperday;
        this.isAvailable=true;
    }

    public String getCar()
    {
        return car_id;
    }

    public String getModel()
    {
        return Model;
    }

    public String getBrand()
    {
        return Brand;
    }

    public double calculate_Rent(int days_rented)
    {
        return Base_Priceperday*days_rented;
    }

    public boolean isAvailable()
    {
        return isAvailable;
    }

    public void rent()
    {
        isAvailable=false;
    }

    public void returnCar()
    {
        isAvailable=true;
    }
}


class Customer
{
    private String cus_id;
    private String Name;

    Customer(String c_id,String n)    
    {
        this.cus_id=c_id;
        this.Name=n;
    }

    public String getName()
    {
        return Name;
    }

    public String getcusid()
    {
        return cus_id;
    }
}


class Rental
{
    private Car car;
    private Customer customer;

    private int days;

    Rental(Car c, Customer cust, int d)
    {
        this.car=c;
        this.customer=cust;
        this.days=d;
    }

    public Car getcar()
    {
        return car;
    }
    public Customer getCust()
    {
        return customer;
    }
    public int getdays()
    {
        return days;
    }
}

class car_rental_sys
{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    
    public car_rental_sys()
    {
        cars= new ArrayList<>();
        customers= new ArrayList<>(); 
        rentals= new ArrayList<>();
    }


    public void addCar(Car car)
    {
        cars.add(car);
    }

    public void addCustomer(Customer cust)
    {
        customers.add(cust);
    }

    public void rent_car(Car car, Customer customer, int days)
    {
        if(car.isAvailable())
        {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        }
        else
        {
            System.out.println("The car is already rented.");
        }
    }

    public void returnCar(Car car)
    {
        car.returnCar();
        Rental rentalToRemove=null;
        for(Rental rental : rentals)
        {
            if(rental.getcar()==car)
            {
                rentalToRemove=rental;
                break;
            }
        }

        if(rentalToRemove!=null)
        {
            rentals.remove(rentalToRemove);
            System.out.println("Car has been removed successfully.");
        }
        else{
            System.out.println("The car was not rented.");
        }
    }


    public void Menu()
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("=======  CAR RENTAL SYSTEM  =======");
            System.out.println("1. Rent a Car.");
            System.out.println("2. Return a Car.");
            System.out.println("3. Exit. ");
            System.out.println("Enter your choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice==1)
            {
                System.out.println("\n==== Rent a Car ====\n");
                System.out.println("Enter your name : ");
                String customerName = sc.nextLine();

                System.out.println("\n=== Available Cars ===\n");
                for(Car car : cars)
                {
                    if(car.isAvailable())
                    {
                        System.out.println(car.getCar()+" - "+ car.getBrand() +  " " + car.getModel());
                    }
                }


                System.out.println("Enter the car id u want to rent :");
                String C_id= sc.nextLine();
                
                System.out.println("Enter the number of days u want to rent the car for :");
                int rentDays=sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCar().equals(C_id) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculate_Rent(rentDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getcusid());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rent_car(selectedCar, newCustomer, rentDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCar().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getcar() == carToReturn) {
                            customer = rental.getCust();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }

}
public class Main{
    public static void main(String[] args) {
        car_rental_sys rentalSystem = new car_rental_sys();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); 
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        Car car4 = new Car("C004", "Gl600", "Mercedes", 350.0);
        Car car5 = new Car("C005", "Thar", "BMW", 300.0);
        Car car6 = new Car("C006", "SF90", "Ferrari", 380.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);

        rentalSystem.Menu();
    }
}
