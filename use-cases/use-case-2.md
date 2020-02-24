# USE CASE 2: Display a non-set list of n countries

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I must provide a way to display a non-set list of n countries in a region/continent/world so that the user can choose the value of n.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate country information

### Success End Condition

List of countries is displayed for the user

### Failed End Condition

Information is not displayed or incorrect information is displayed

### Primary Actor

Developer

### Trigger

A request for a list of countries is received

## MAIN SUCCESS SCENARIO

1. User supplies number of countries they wish to be displayed and the area in which the countries are to be found
2. n countries from the desired area are extracted from the database
3. Countries are displayed to the user as a list

## EXTENSIONS

2. **Area the user has requested does not exist in the database**:
    1. Inform user that the area is invalid
2. **User has requested a list larger or smaller than the number of valid countries in the database**:
    1. Inform the user that the size provided is invalid

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb
