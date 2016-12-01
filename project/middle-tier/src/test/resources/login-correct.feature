Feature: the order can be created
  Scenario: client log in correctly to system by REST Api

    When user logs in using Username as "k.mucik@gft.com" and Password as "haslo"
    Then the api receives status code of 200

    When get all product
    Then the api receives status code of 200
    And list of all product

    When get all active assets
    Then the api receives status code of 200
    And the client receives server response body

    When get all active user assets 'k.mucik@gft.com'
    Then the api receives status code of 200
    And the client receives all active user assets