package com.CodeAlpha;

import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {

    // Destination class to store information for each destination
    static class Destination {
        private String location;
        private String startDate;
        private String endDate;
        private String accommodationPreference;
        private double budget;

        // Constructor for Destination
        public Destination(String location, String startDate, String endDate, String accommodationPreference, double budget) {
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.accommodationPreference = accommodationPreference;
            this.budget = budget;
        }

        // Getter methods for each property
        public String getLocation() {
            return location;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getAccommodationPreference() {
            return accommodationPreference;
        }

        public double getBudget() {
            return budget;
        }

        // Display destination details including dummy weather and map link
        public void displayDestination() {
            System.out.println("\nDestination: " + location);
            System.out.println("Travel Dates: " + startDate + " to " + endDate);
            System.out.println("Accommodation: " + accommodationPreference);
            System.out.println("Budget: $" + budget);
            System.out.println("Weather Forecast: " + getWeather(location));  // Placeholder for weather API
            System.out.println("Map Link: " + getMapLink(location));           // Placeholder for map link
            System.out.println("--------------------------------------");
        }

        // Dummy weather info, replace with real API integration if needed
        private String getWeather(String location) {
            return "Sunny with temperatures around 25°C";  // Placeholder weather
        }

        // Dummy Google Maps link, can be replaced with real map data later
        private String getMapLink(String location) {
            return "https://maps.google.com/?q=" + location;  // Simulated map link
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Destination> itinerary = new ArrayList<>();

        // Prompt the user to input travel information
        System.out.println("Welcome to the Travel Itinerary Planner!");
        while (true) {
            System.out.println("\nEnter details for your next destination:");

            // Destination input
            System.out.print("Destination (City/Country): ");
            String location = scanner.nextLine();

            // Start and end dates input
            System.out.print("Start Date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();

            System.out.print("End Date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();

            // Accommodation preference
            System.out.print("Accommodation Preference (e.g., Hotel, Airbnb): ");
            String accommodation = scanner.nextLine();

            // Budget input
            System.out.print("Budget for this destination (in USD): ");
            double budget = scanner.nextDouble();
            scanner.nextLine();  // Clear newline after nextDouble

            // Add destination to itinerary
            itinerary.add(new Destination(location, startDate, endDate, accommodation, budget));

            // Ask if user wants to add another destination
            System.out.print("Do you want to add another destination? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Calculate total budget
        double totalBudget = 0;

        // Display detailed itinerary
        System.out.println("\n----- Your Travel Itinerary -----");
        for (Destination dest : itinerary) {
            dest.displayDestination();
            totalBudget += dest.getBudget();
        }

        // Display total budget for the entire trip
        System.out.printf("\nTotal Estimated Budget: $%.2f\n", totalBudget);

        // Close the scanner
        scanner.close();
    }
}
