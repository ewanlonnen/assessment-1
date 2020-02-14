# USE CASE 6: Display the top populated capital cities in a region/continent/world in descending order of population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I need to display a set list of the capital cities with the highest population in a region/continent/world from the database in descending order.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate country and accurate capital information

### Success End Condition

List of cities are displayed for the user in the correct order

### Failed End Condition

Information is not displayed or is displayed in the wrong order

### Primary Actor

Developer

### Trigger

A request for a list of capital cities in a specified area is received

## MAIN SUCCESS SCENARIO

1. User specifies an area that they want the capital cities to be located in (region/continent/the world)
2. Capital cities from the specified area are extracted from the database
3. Capital cities are sorted into descending order of population

## EXTENSIONS

1. **Area the user has requested does not exist in the database**:
    1.1. Inform user that the area is invalid
    
## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb
