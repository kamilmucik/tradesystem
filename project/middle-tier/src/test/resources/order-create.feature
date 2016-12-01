Feature: the order can be created
  Scenario: client create order by middle-tier REST Api

    When user logs in using Username as "k.mucik@gft.com" and Password as "haslo"
    Then the api receives status code of 200
    And login should be successful
    And get user detail 'k.mucik@gft.com'
    And the api receives status code of 200
    And the client receives server response param 'firstName' body 'Kamil'
    And the client receives server response param 'freeResources' body '25553.95': double

    When get request url '/product/supported'
    Then the api receives status code of 200

    When the client select product by param productId='1'
    Then the api receives status code of 200

    When the client receives server response param 'id' body '1': integer
    Then the client prepare order request by params userId='1', productId='1', type'SALE', price'1500', volume'10'
    And the api receives status code of 200



