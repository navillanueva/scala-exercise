# Welcome to PowerDale

PowerDale is a small town with around 100 residents. Most houses have a smart meter installed that can save and send information
about how much energy a house is using at a given point in time.

There are three major providers of energy in town that charge different amounts for the power they supply.

- _Dr Evil's Dark Energy_
- _The Green Eco_
- _Power for Everyone_

# Introducing JOI Energy

JOI Energy is a new startup in the energy industry.
Rather than selling energy they want to differentiate themselves from the market by recording their customers' energy usage from their smart meters and
recommending the best suppler to meet their needs.

You have been placed into their development team, whose current goal is to produce an API which their customers and smart meters will interact with.

Unfortunately, two of the team are on annual leave, and another has called in sick!
You are left with a ThoughtWorker to progress with the current user stories on the story wall. This is your chance to make an impact on the business, improve the code base and deliver value.

## Personal Story Wall Training

- [ ] **Implement Price Plan Recommendations Based on Cost Threshold**  
  Create a method to suggest the cheapest price plan based on a specified cost threshold for a smart meter.

- [ ] **Retrieve Consumption for a Given Date Range**  
  Extend functionality to fetch electricity readings within a specific date range for reporting or analysis purposes.

- [ ] **Add Monthly Aggregation Functionality**  
  Develop a feature to calculate and aggregate monthly energy consumption for each smart meter.

- [ ] **Add Unit Tests for the Price Calculation Logic**  
  Write comprehensive tests to validate the accuracy of cost calculations under various scenarios.

- [ ] **Integrate Multi-Rate Pricing Models**  
  Implement support for peak and off-peak rates in price plans, applying different rates based on time of day.

- [ ] **Refactor to Store Readings Efficiently**  
  Improve storage efficiency by switching to a more optimized structure or database for storing large datasets of readings.

- [ ] **Implement Caching for Repeated Calculations**  
  Add a caching mechanism to store the results of frequently requested calculations and reduce repeated processing.

- [ ] **Implement a Batch Processing Service**  
  Create a service to process readings for multiple smart meters in parallel, useful for bulk calculations and reporting.

- [ ] **Design an API Layer**  
  Build a REST API to expose functionalities, such as fetching readings, calculating costs, and recommending plans.

- [ ] **Add Error Handling and Validation for Input Data**  
  Enhance robustness by adding error handling and validation to ensure only valid and correctly formatted data is processed.


## Users

To trial the new JOI software 5 people from the JOI accounts team have agreed to test the service and share their energy data.

- Sarah - Smart Meter Id: "smart-meter-0", current power supplier: Dr Evil's Dark Energy.
- Peter - Smart Meter Id: "smart-meter-1", current power supplier: The Green Eco.
- Charlie - Smart Meter Id: "smart-meter-2", current power supplier: Dr Evil's Dark Energy.
- Andrea - Smart Meter Id: "smart-meter-3", current power supplier: Power for Everyone.
- Alex - Smart Meter Id: "smart-meter-4", current power supplier: The Green Eco.

## Overview

JOI Energy is a new energy company that uses data to ensure customers are 
able to be on the best price plan for their energy consumption.

## API

Below is a list of API endpoints with their respective input and output.

### Store Readings

#### Endpoint

```
POST
/readings/store
```

#### Input

```json
{
    "smartMeterId": <smartMeterId>,
    "electricityReadings": [
        { "time": "2019-01-24T18:11:27.142Z", "reading": <reading> },
        ...
    ]
}
```

`timestamp`: Timestamp in ISO Format   
`reading`: kW reading of meter at that time as a number, e.g. `0.0503`

### Get Stored Readings

#### Endpoint

```
GET
/readings/read/<smartMeterId>
```

`smartMeterId`: A string value, e.g. `smart-meter-0`

#### Output

```json
[
  { "time": "2017-09-07T10:37:52.362Z", "reading": 1.3524882598124337 },
  ...
]
```

### View Current Price Plan and Compare Usage Cost Against all Price Plans

#### Endpoint

```
GET
/price-plans/compare-all/<smartMeterId>
```

`smartMeterId`: A string value, e.g. `smart-meter-0`

#### Output

```json
{
    "pricePlanId": "price-plan-2",
    "pricePlanComparisons": { 
        "price-plan-0": 21.78133785680731809,
        ...
    }
}
```

### View Recommended Price Plans for Usage

#### Endpoint

```
GET
/price-plans/recommend/<smartMeterId>[?limit=<limit>]
```

`smartMeterId`: A string value, e.g. `smart-meter-0`

`limit`: Optional limit to display only a number of price plans, e.g. `2`

#### Output

```json
[
    { 
        "price-plan-0": 15.084324881035297
    },
    ...
]
```

## Requirements

The project is written in Scala 3. We recommend using  [Java 17](https://adoptium.net/en-GB/) or higher.

The build system is SBT - we are using the latest version, currently 1.9.3.

We use Akka HTTP in this implementation.

## Build

## Test

```console
$ sbt test
```

## Run

```console
$ sbt run
```

The application starts on `localhost:8080` by default.