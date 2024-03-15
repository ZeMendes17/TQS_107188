@blazedemo
Feature: Booking a trip in BlazeDemo

    Scenario: Booking a flight
        When I navigate to "https://blazedemo.com/"
        And I select the departure city as "Boston" and destination city as "London"
        And I click on the "Find Flights" button
        And I select the flight number 3
        And I enter the passenger details, "Ze", "Mendes", "FAFE", "Braga", "4820"
        And I enter the credit card details, "1234567890", "12", "2024", "Ze Mendes"
        And I click on the "Purchase Flight" button
        Then I assert that the title of the page is "BlazeDemo Confirmation"