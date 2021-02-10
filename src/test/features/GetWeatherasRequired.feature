#@troubleShoot
Feature: Get Weather update for surfing

	Background:
		Given all API configurations are set


	Scenario Outline: As a choosey surfer

		Given I like to surf in any beach of <city>
		And I only like to surf on any 2 days specifically <Day1> & <Day2> in next 16 Days
		When I look up the the weather forecast for the next 16 days using POSTALCODES <POSTALCODES>
		Then I check to if see the temperature is between 15°C and 30°C
		And I check to see if UV index is <= 5
		And I Pick two spots based on suitable weather forecast for the day

		Examples:
			| POSTALCODES | Day1        |Day2			| city       |
			| 2060        | THURSDAY  	|    FRIDAY		|"Sydney,AU" |
			| 2765        |THURSDAY 	|    FRIDAY		|"Sydney,AU" |
			| 2769        | THURSDAY 	|    FRIDAY		|"Sydney,AU" |