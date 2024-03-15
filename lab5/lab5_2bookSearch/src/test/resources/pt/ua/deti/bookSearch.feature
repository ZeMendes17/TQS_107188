@library
Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given that the library has the following books
      | title              | author          | published  |
      | One good book      | Anonymous       | 2013-03-14 |
      | Some other book    | Tim Tomson      | 2014-08-23 |
      | How to cook a dino | Fred Flintstone | 2012-01-01 |
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 1 should have the title 'One good book'
    And Book 2 should have the title 'Some other book'

  Scenario: Search books by title
    Given that the library has the following books
      | title              | author          | published  |
      | One good book      | Anonymous       | 2013-03-14 |
      | Some other book    | Tim Tomson      | 2014-08-23 |
      | How to cook a dino | Fred Flintstone | 2012-01-01 |
    When the customer searches for books with the title 'book'
    Then 2 books should have been found
    And Book 1 should have the title 'One good book'
    And Book 2 should have the title 'Some other book'

  Scenario: Search books by author
    Given that the library has the following books
      | title              | author          | published  |
      | One good book      | Anonymous       | 2013-03-14 |
      | Some other book    | Tim Tomson      | 2014-08-23 |
      | How to cook a dino | Fred Flintstone | 2012-01-01 |
    When the customer searches for books written by 'Tim Tomson'
    Then 1 books should have been found
    And Book 1 should have the title 'Some other book'