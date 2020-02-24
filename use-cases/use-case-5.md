# USE CASE 5: Display the top (N) populated capital cities in a region/continent/world in descending order of population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I need to display a non-set list of length N of the capital cities with the highest population in a region/continent/world from the database in descending order of population where N is provided by the user.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate country and accurate capital information

### Success End Condition

List of length specified containing N cities are displayed for the user in the correct order

### Failed End Condition

Information is not displayed or is displayed in the wrong order

### Primary Actor

Developer

### Trigger

A request for a list of cities of length N is received

## MAIN SUCCESS SCENARIO

1. User supplies the amount of cities N which they would like countries to be displayed
2. User specifies an area that they want the cities to be located in
3. Cities from the specified area are extracted from the database
4. Cities are sorted into descending order of population

## EXTENSIONS

1. **Area the user has requested does not exist in the database**:
    1.1. Inform user that the area is invalid
2. **User specifies an invalid value for N e.g -1, 100000000**
    2.1 Inform user that the value is invalid
## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb
