
Feature: Feature to perform CRUD operations

  Scenario: To Perform the GET Operation on Employe DB
    Given The base URI is "http://localhost:3000/employees"
    When I perform the Get Operation
    Then Response code should be 200

  Scenario: To Perform the POST Operation to create Employee
    Given The base URI is "http://localhost:3000/employees"
    When I pass the name "Jachin" and salary "8000"
    And I perform Post operation
    Then Response code should be 201

  Scenario: To Perform the DELETE Operation to delete Employee
    Given The base URI is "http://localhost:3000/employees"
    When I delete the created employee
    Then Response code should be 200
