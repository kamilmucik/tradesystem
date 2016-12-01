Feature: the order can be created
  Scenario: client log in failure to system by REST Api
    When user logs in using Username as "k.mucik@gft.com" and Password as "haslo-bledne"
    Then the api receives status code of 401