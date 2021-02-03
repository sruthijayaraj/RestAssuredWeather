Feature: Get Weather
  Scenario: User calls web service to get weather

     Given I like to surf in any beach of <city>
	 And I only like to surf on any 2 days specifically TUESDAY & FRIDAY in next 16 Days
	  When I look up the the weather forecast for the next 16 days using POSTALCODES <POSTALCODES>
	  Then I check to if see the temperature is between 15°C and 30°C
	  And I check to see if UV index is <= 2
	  And I Pick two spots based on suitable weather forecast for the day

	  Examples:
		  | POSTALCODES |Day        |city    |
		  |  2060    |"THURSDAY" | "Sydney,AU"       |
		  |  2765    |"WEDNESDAY"| "Sydney,AU"       |
		  |  2769    |"WEDNESDAY"| "Sydney,AU"       |

