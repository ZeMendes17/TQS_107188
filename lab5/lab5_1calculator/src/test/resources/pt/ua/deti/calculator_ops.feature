@calc_sample
Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

    Scenario: Addition
        When I add 3 and 4
        Then the result is 7

    Scenario: Subtraction
        When I subtract 7 to 2
        Then the result is 5

    Scenario: Multiplication
        When I multiply 5 and 3
        Then the result is 15

    Scenario: Division
        When I divide 10 by 2
        Then the result is 5

      Scenario Outline: Several additions
        When I add <a> and <b>
        Then the result is <result>

        Examples: Single digits
            | a | b | result |
            | 1 | 2 | 3      |
            | 3 | 4 | 7      |
            | 5 | 6 | 11     |

        Examples: Double digits
            | a  | b  | result |
            | 10 | 20 | 30     |
            | 30 | 40 | 70     |
            | 50 | 60 | 110    |

      Scenario Outline: Several multiplications
        When I multiply <a> and <b>
        Then the result is <result>

        Examples: Single digits
          | a | b | result |
          | 1 | 2 | 2      |
          | 3 | 4 | 12     |
          | 5 | 6 | 30     |