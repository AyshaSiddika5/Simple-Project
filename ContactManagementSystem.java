package contactmanagementsystem;

import java.io.*;
import java.util.*;

abstract class Contact {
    private String name;
    private String email;
    private String phone;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Abstract method for displaying contact details
    public abstract void displayDetails();

    // Abstract method for saving contact details to a file 
    public abstract String toFileFormat();
}

class PersonalContact extends Contact {

    public PersonalContact(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    public void displayDetails() {
        System.out.println("Personal Contact");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
    }

    @Override
    public String toFileFormat() {
        return getName() + "," + getEmail() + "," + getPhone();
    }
}

class BusinessContact extends Contact {
    private String company;

    public BusinessContact(String name, String email, String phone, String company) {
        super(name, email, phone);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public void displayDetails() {
        System.out.println("Business Contact");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Company: " + getCompany());
    }

    @Override
    public String toFileFormat() {
        return getName() + "," + getEmail() + "," + getPhone() + "," + company;
    }
}


public class ContactManagementSystem {
    private static final String FILE_PATH = "contacts.txt";
    private List<Contact> contacts;

    public ContactManagementSystem() {
        this.contacts = new ArrayList<>();
        loadContacts();
    }

    public void loadContacts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    contacts.add(new PersonalContact(data[0], data[1], data[2]));
                } else if (data.length == 4) {
                    contacts.add(new BusinessContact(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }

    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.write(contact.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
        System.out.println("Contact added successfully.");
    }

    public void updateContact(String name, String newEmail, String newPhone) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setEmail(newEmail);
                contact.setPhone(newPhone);
                saveContacts();
                System.out.println("Contact updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
        saveContacts();
        System.out.println("Contact deleted successfully.");
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        for (Contact contact : contacts) {
            contact.displayDetails();
            System.out.println("------------------------------");
        }
    }

    public static void main(String[] args) {
        ContactManagementSystem system = new ContactManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Contact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Is this a Business Contact? (y/n): ");
                    String type = scanner.nextLine();

                    if (type.equalsIgnoreCase("y")) {
                        System.out.print("Enter Company: ");
                        String company = scanner.nextLine();
                        BusinessContact businessContact = new BusinessContact(name, email, phone, company);
                        system.addContact(businessContact);
                    } else {
                        PersonalContact personalContact = new PersonalContact(name, email, phone);
                        system.addContact(personalContact);
                    }
                    break;
                case 2:
                    System.out.print("Enter Name to Update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter New Phone: ");
                    phone = scanner.nextLine();
                    system.updateContact(name, email, phone);
                    break;
                case 3:
                    System.out.print("Enter Name to Delete: ");
                    name = scanner.nextLine();
                    system.deleteContact(name);
                    break;
                case 4:
                    system.viewContacts();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


