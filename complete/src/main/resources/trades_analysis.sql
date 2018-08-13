--1. 1	Provide a query that shows the number of trades per client traded on the 1st of August, 2012.
SELECT CUSTOMERID, COUNT(*) as NumberOfTrades FROM TRADES 
WHERE TRADEDATE = '01-AUG-2012' 
GROUP BY CUSTOMERID;

-- Sample Results 
/*
{
  "trades_per_client": [
    {
      "customerid": 1000,
      "numberoftrades": 2
    },
    {
      "customerid": 1010,
      "numberoftrades": 1
    }
  ]
}
*/

--2	Provide a query that shows the trade details of the largest (in terms of quantity) trade done for each client. The result set should show the customer name, instrument name, quantity, price, and trade date.
WITH maxtrades as (SELECT customerId, max(quantity) as quantity FROM trades group  by customerId)
SELECT t.tradeId,c.customername,i.instrumentname, m.quantity,t.price,t.tradedate FROM maxtrades m 
inner join trades t
ON m.customerId = t.customerId and m.quantity = t.quantity
inner join customers c
ON m.customerid = c.customerid
inner join instruments i
ON t.instrumentid = i.instrumentid
order by quantity asc

--Sample Results
/** 
{
  "largest_trades_per_client": [
    {
      "tradeid": 4,
      "customername": "GHI Pension Fund",
      "instrumentname": "Tony Incorporated",
      "quantity": -7500,
      "price": 30,
      "tradedate": "05-JUN-12"
    },
    {
      "tradeid": 1002,
      "customername": "ABC Fund",
      "instrumentname": "Pineapple Computers",
      "quantity": 6000,
      "price": 1,
      "tradedate": "01-AUG-12"
    },
    {
      "tradeid": 6,
      "customername": "Meyers Holdings Inc",
      "instrumentname": "Hayoo.com",
      "quantity": 24000,
      "price": 58,
      "tradedate": "01-AUG-12"
    },
    {
      "tradeid": 1001,
      "customername": "International Bank of DEF",
      "instrumentname": "Tony Incorporated",
      "quantity": 50000,
      "price": 45,
      "tradedate": "20-DEC-12"
    }
  ]
}
**/

--3	Produce a query that shows the last date a client traded. The result set should show the customer id, customer name, and last trade date (or null if the client has never traded).
WITH lasttrade as (SELECT customerId, max(tradedate) as tradedate FROM trades group  by customerId)
SELECT t.tradeId,c.customername,l.tradedate as last_tradedate FROM lasttrade l 
inner join trades t
ON l.customerId = t.customerId and l.tradedate = t.tradedate
right join customers c
ON l.customerid = c.customerid
order by last_tradedate asc

-- Sample Results 
/*
{
    "lastTradeDates":
    [
        {"tradeid":4,"customername":"GHI Pension Fund","last_tradedate":"05-JUN-12"},
        {"tradeid":6,"customername":"Meyers Holdings Inc","last_tradedate":"01-AUG-12"},
        {"tradeid":1,"customername":"ABC Fund","last_tradedate":"01-AUG-12"},
        {"tradeid":1002,"customername":"ABC Fund","last_tradedate":"01-AUG-12"},
        {"tradeid":1001,"customername":"International Bank of DEF","last_tradedate":"20-DEC-12"},
        {"customername":"XYZ Group"},
        {"customername":"MNO Holdings Inc"},
        {"customername":"JKL Asset Management"}
    ]
}
*/