@smoke

Feature: Booking Feature

  Scenario Outline: validate search results

    When User is on Search page
    When User performs a Search from "<fromStation>” to Manchester Station
    And User selects a ticket
    Then User must be displayed with Summary page with journey details

    Examples:
      | fromStation |
      | London      |
      | Brighton  |
