Feature: developers_by_countries.DevelopersByCountries

  Scenario: developers statistics can be calculated
    Given input dataframe developers
      | id | name   | country_id |
      | 1  | Ivan   | 10         |
      | 2  | Dmitry | 10         |
      | 3  | Petr   | 10         |
      | 4  | Lyavon | 11         |
      | 5  | Yas    | 11         |
    And input dataframe countries
      | id | name    |
      | 10 | Russia  |
      | 11 | Belarus |
    When task is executed
    Then output equals to
      | country | count |
      | Belarus | 2     |
      | Russia  | 3     |
