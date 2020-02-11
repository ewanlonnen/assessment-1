# USE CASE 3: Display all countries in an area in descending order of population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I need to display all of the countries in a region/continent/world from the database in descending order of population.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate country information

### Success End Condition

Specified countries are displayed for the user in the correct order

### Failed End Condition

Information is not displayed or is displayed in the wrong order

### Primary Actor

Developer

### Trigger

A request for a list of countries is received

## MAIN SUCCESS SCENARIO

1. User supplies the area from which they would like countries to be displayed
2. Countries from the specified area are extracted from the database
3. Countries are sorted into descending order of population

## EXTENSIONS

2. **Area the user has requested does not exist in the database**:
    1. Inform user that the area is invalid

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb