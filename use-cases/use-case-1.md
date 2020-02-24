# USE CASE 1: Facilitate the creation of a city report

## CHARACTERISTIC INFORMATION

### Goal in Context

As a developer I must facilitate the creation of a city report that contains the correct columns for the organisation.

### Scope

Group

### Level

Primary task

### Preconditions

Database contains appropriate city information

### Success End Condition

A suitable report is available for the organisation

### Failed End Condition

No report is produced

### Primary Actor

Developer

### Trigger

A request for city information is received

## MAIN SUCCESS SCENARIO

1. Customer requests city information for a given criteria
2. Relevant city information is extracted from the database
3. Report is created using extracted information
4. Report is supplied to customer

## EXTENSIONS

2. **Criteria supplied by customer is invalid (eg. asks for more cities than exist in the database)**:
    1. Inform customer that criteria is invalid

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: 17th Feb