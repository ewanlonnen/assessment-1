# USE CASE 8: Display the total population of a country/region/continent, the amount of people living in cities and the amount of people not living in cities in that country/region/continent.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I need to display the total population of a country/region/continent, it's urban population and its non-urban population from the database in descending order.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate area and city information

### Success End Condition

List of the different population demographics are displayed for the user correctly.

### Failed End Condition

Information is not displayed or is displayed incorrectly.

### Primary Actor

Developer

### Trigger

A request for a list of the demographics of a specified area is received

## MAIN SUCCESS SCENARIO

1. User specifies an area that they want the demographics of (country/region/continent)
2. Total, city and non city populations from the specified area are extracted from the database
3. Demographics are sorted into tables displaying their respective population

## EXTENSIONS

1. **Area the user has requested does not exist in the database**:
    1.1. Inform user that the area is invalid
    
## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb
