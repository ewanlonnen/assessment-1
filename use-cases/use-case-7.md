# USE CASE 7: Display the top N populated cities in a district/country/region/continent/world in descending order of population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I need to display a non-set list (N) of the cities with the highest population in a district/country/region/continent/world from the database in descending order where N is provided by the user.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate country and accurate city information

### Success End Condition

List of cities of length (N) are displayed for the user in the correct order

### Failed End Condition

Information is not displayed or is displayed in the wrong order

### Primary Actor

Developer

### Trigger

A request for a list of length N of cities in a specified area is received

## MAIN SUCCESS SCENARIO
1. User specifies a number N to set the length of the list.
1. User specifies an area that they want the cities to be located in (district/country/region/continent/the world)
2. Cities from the specified area are extracted from the database
3. N Cities are sorted into descending order of population

## EXTENSIONS

1. **Area the user has requested does not exist in the database**:
    1.1. Inform user that the area is invalid
2. **Value the user has entered is not valid (e.g -1, 999999999999)
    2.1 Inform the user that the value is not valid.
## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb
