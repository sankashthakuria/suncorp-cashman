# Suncorp Assignment - Sankash Thakuria

## Scope
The program implements the mandatory feature set as outlined in the problem statement.
### Mandatory Feature Set
* The device will have a supply of cash (physical bank notes) available.
* It must know how many of each type of bank note it has. It should be able to report back how much of each note it has.
* It should be possible to tell it that it has so many of each type of note during initialisation. After initialisation, it is only possible to add or remove notes.
* It must support $20 and $50 Australian denominations.
* It should be able to dispense legal combinations of notes. For example, a request for $100 can be satisfied by either five $20 notes or 2 $50 notes. It is not required to present a list of options.
* If a request can not be satisfied due to failure to find a suitable combination of notes, it should report an error condition in some fashion. For example, in an ATM with only $20 and $50 notes, it is not possible to dispense $30.
* Dispensing money should reduce the amount of available cash in the machine.
* Failure to dispense money due to an error should not reduce the amount of available cash in the machine.


## Requirements

* [Java 8](https://www.java.com/en/download/) or higher
* [Maven](https://maven.apache.org/) 3.5.4


## Usage

The application can be run via Apache Maven from the commandline. `cd` into the project directory that contains the pom.xml file and issue `mvn clean install`. This will build the project.

To run the application with the json files that were provided for the challenge, issue the following:

```shell
mvn exec:java
```
To run the tests issue the following:
```shell
mvn test
```

The project can also be run from Intellij Idea. To do so, import as maven project, navigate to the `App.java` class and run the `main` method.



## Limitations
* Only the mandatory feature set is implemented

## Demonstration
Below is sample execution of the program


## Sample results

###Organization


```shell
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

1
Add denomination
TWENTY -> 20
FIFTY -> 50

Enter denomination

20
Enter the number of notes for the chosen denomination
3
Enter (Y/y) to go back to main menu - Press any other character to quit.....
y
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

4
Get Balance

Total balance left in the ATM is 60
Enter (Y/y) to go back to main menu - Press any other character to quit.....
y
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

1
Add denomination
TWENTY -> 20
FIFTY -> 50

Enter denomination

50
Enter the number of notes for the chosen denomination
5
Enter (Y/y) to go back to main menu - Press any other character to quit.....
y
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

5
Demonination:TWENTY Count:3
Demonination:FIFTY Count:5
Enter (Y/y) to go back to main menu - Press any other character to quit.....
y
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

3
Withdraw Cash

100
The ATM dispensed the following notes for your request [50, 50]
Enter (Y/y) to go back to main menu - Press any other character to quit.....
y
Enter a choice
1 -> Add cash
2 -> Remove cash
3 -> Withdraw cash
4 -> Get cash balance
5 -> Get denominations balance

4
Get Balance

Total balance left in the ATM is 210
Enter (Y/y) to go back to main menu - Press any other character to quit.....
q

```


## Further Improvements
* A Dependency Injection framework such as Spring or Google Guice can be used as the project grows in size and more classes are written. To manage the dependencies a DI framework could come in handy
* The `Driver.java` class could be refactored to separate out the standard in and standard out. This would facilitate writing robust unit tests
* Only the mandatory feature set from the problem statement is implemented in the current version.
* More unit tests can be written especially for the `Driver.java` class

## Testing

Some of the libraries that this project uses for testing are the following:

* [Junit 5](https://junit.org/junit5/) - Testing Framework
* [PowerMock](https://github.com/powermock/powermock) -Testing Framework


