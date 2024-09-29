package com.CodeAlpha;

import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {

    // Room class to represent a hotel room
    static class Room {
        private int roomNumber;
        private String roomType; // e.g., Standard, Deluxe, Suite
        private double pricePerNight;
        private boolean isAvailable;

        public Room(int roomNumber, String roomType, double pricePerNight) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.isAvailable = true; // All rooms are available initially
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void reserveRoom() {
            this.isAvailable = false;
        }

        public void freeRoom() {
            this.isAvailable = true;
        }

        @Override
        public String toString() {
            return "Room " + roomNumber + " (" + roomType + ") - $" + pricePerNight + " per night";
        }
    }

    // Reservation class to represent a booking
    static class Reservation {
        private Room room;
        private String guestName;
        private int numberOfNights;
        private double totalCost;

        public Reservation(Room room, String guestName, int numberOfNights) {
            this.room = room;
            this.guestName = guestName;
            this.numberOfNights = numberOfNights;
            this.totalCost = room.getPricePerNight() * numberOfNights;
        }

        public void displayReservationDetails() {
            System.out.println("Reservation Details:");
            System.out.println("Guest: " + guestName);
            System.out.println("Room: " + room);
            System.out.println("Nights: " + numberOfNights);
            System.out.println("Total Cost: $" + totalCost);
            System.out.println("----------------------------------");
        }

        public double getTotalCost() {
            return totalCost;
        }
    }

    public static void main(String[] args) {
        // Initialize room data (usually, this would come from a database)
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Standard", 100.00));
        rooms.add(new Room(102, "Standard", 100.00));
        rooms.add(new Room(201, "Deluxe", 200.00));
        rooms.add(new Room(202, "Deluxe", 200.00));
        rooms.add(new Room(301, "Suite", 500.00));

        ArrayList<Reservation> reservations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Main Menu
            System.out.println("Welcome to the Hotel Reservation System!");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1: // Search for available rooms
                    System.out.println("Available Rooms:");
                    for (Room room : rooms) {
                        if (room.isAvailable()) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2: // Make a reservation
                    System.out.print("Enter your name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numberOfNights = scanner.nextInt();

                    Room selectedRoom = null;
                    for (Room room : rooms) {
                        if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                            selectedRoom = room;
                            break;
                        }
                    }

                    if (selectedRoom != null) {
                        Reservation reservation = new Reservation(selectedRoom, guestName, numberOfNights);
                        reservations.add(reservation);
                        selectedRoom.reserveRoom(); // Mark room as reserved
                        System.out.println("Reservation successful!");
                        reservation.displayReservationDetails();

                        // Payment Process (Simplified)
                        System.out.println("Payment Process:");
                        System.out.printf("Total Amount Due: $%.2f\n", reservation.getTotalCost());
                        System.out.print("Enter payment amount: $");
                        double paymentAmount = scanner.nextDouble();
                        if (paymentAmount >= reservation.getTotalCost()) {
                            System.out.println("Payment successful. Enjoy your stay!");
                        } else {
                            System.out.println("Insufficient payment. Reservation canceled.");
                            reservations.remove(reservation);
                            selectedRoom.freeRoom(); // Free the room if payment fails
                        }

                    } else {
                        System.out.println("Room not available or does not exist.");
                    }
                    break;

                case 3: // View booking details
                    System.out.print("Enter your name to view your reservations: ");
                    String nameToSearch = scanner.nextLine();
                    boolean reservationFound = false;
                    for (Reservation reservation : reservations) {
                        if (reservation.guestName.equalsIgnoreCase(nameToSearch)) {
                            reservation.displayReservationDetails();
                            reservationFound = true;
                        }
                    }
                    if (!reservationFound) {
                        System.out.println("No reservations found for " + nameToSearch);
                    }
                    break;

                case 4: // Exit
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

