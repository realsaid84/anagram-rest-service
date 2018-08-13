Challenge
===================
1 	Anagram Program
An anagram is the result of rearranging the letters of a word to forSm a new word using all the original letters exactly once. For example, an anagram of the word orchestra is carthorse.
The candidate should create a program that reads in a dictionary file (a plain text file consisting of a list of English words) on start-up. After start-up, the user is presented with a prompt. The user should then be able to input a word, and the program will output a list of valid anagrams for that input. A words file has been included with this test. Candidates should consider both the performance and scalability of their solution.
2	Anagram Program – Client / Server
The candidate should then extend their program to support a Client/Server mode. The candidate should be able to provide both a server program, which listens to connections (via sockets, RMI or other method of the candidate’s choosing), and a client program, which connects to the server.
The client program should interact with the server in the same way as in the first phase, whereby the client inputs a word, and the server responds with a list of valid anagrams. The client should be able to disconnect cleanly from the server. 
3	Anagram Program – Interaction
The candidate should then extend their program to take more advanced input from the client. The client should be presented with three options :
	[A]	Add a word
	[D]	Delete a word
	[P]	Print anagrams
If the client selects option [A] they will be able to add a word to the programs dictionary list. If the client selects option [D] the client will be prompted for a word to delete from the list. If the client selects option [P] the server will act as in stage 2 and return with a list of anagrams for the users input.












SQL Technical Test
Imagines we have three tables, Trades, Instruments and Customers. An example of each is below.

Trades
TradeId	CustomerId	InstrumentId	TradeDate	Quantity	Price
1	1000	5000	05/06/2012	1000	1.452
2	1000	5050	05/06/2012	2500	23.56
3	1001	5082	05/06/2012	5000	42.8
4	1002	5092	05/06/2012	-7500	30.034
5	1010	5050	05/06/2012	-25000	56.123
…	…	…	…	…	…
1001	1001	5092	20/12/2012	50000	45.2

Customers						Instruments
CustomerId	CustomerName
1000	ABC Fund
1001	International Bank of DEF
1002	GHI Pension Fund
1003	JKL Asset Management
1004	MNO Holdings Inc
…	…
1999	XYZ Group
InstrumentId	InstrumentName
5000	Pineapple Computers
5001	Macrosoft PLC
5002	Giggle Inc
5003	Headbook PLC
5004	Hayoo.com
…	…
5999	Tony Incorporated


1	Provide a query that shows the number of trades per client traded on the 1st of August, 2012.

2	Provide a query that shows the trade details of the largest (in terms of quantity) trade done for each client. The result set should show the customer name, instrument name, quantity, price, and trade date.

3	Produce a query that shows the last date a client traded. The result set should show the customer id, customer name, and last trade date (or null if the client has never traded).


SOLUTION TO EXERCISE 1
===============
To build the application, please follow the following steps :
1. Clone the repository `git clone git@ggithub.com:realsaid84/anagram-rest-service.git`
2. Navigate into the application directory using `cd anagram-rest-service/complete `
3. Run `mvn package && java -jar target/anagram-rest-service-0.1.0.jar` or
`./gradlew build && java -jar build/libs/anagram-rest-service-0.1.0.jar` if installed.
If you run into issues, import as an existing Maven Project in your IDE (Eclipse or Intellij) and run as a java 
application.
4. After packaging, application startup and loading of Dictionary Reference Data at post construct time, you can interact with the application by using a browser UI:
https:localhost:8080 or curl your REST requests from command line for each of the operation i.e.

- `curl localhost:8080/anagram/love` 
to find all anagrams of love

- `curl -d '{"word":"lover"}'  -H  "Content-Type: application/json" http://localhost:8080/word `
  to add a new word

- `curl -X DELETE http://localhost:8080/word/lover ` 
 to delete an existing word called lover


SOLUTION TO EXERCISE 2
================
The solutions to the sql test can be found in the path [src/main/resources/trades_analysis.sql](src/main/resources/trades_analysis.sql)