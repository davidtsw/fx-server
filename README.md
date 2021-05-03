# Basics of fx-server

This backend server will process fx trades in below format:

```json
{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP",
"amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471,
"timePlaced" : "24-JAN-18 10:27:44", "originatingCountry" : "FR"}
```

Each trade will be processed and recorded.<br>
Simple validation will check if the amounts are negative.<br>
Total number of trades per user and per originating country will be recorded.<br>
A house view of total currency flow will also be calculated.

Sample global view output:

```json
{
  "tradeByUser": {
    "134256": 1
  },
  "tradeByCountry": {
    "FR": 1
  },
  "houseAccountBalance": {
    "EUR": 1000,
    "GBP": -747.1
  }
}
```

# Technology used
#### Java 8
Although 14 is already out there and with many improvements, Java 8 is still the widely used version in commercial world.

#### Spring boot
The promising way to build a Spring based backend server. Can build into docker image easily.

#### AXON framework
To build a global scalable system, event sourcing and CQRS comes with many benefits:
1. Atomic update of states
2. Easy to migrate to micro-services
3. Event replays for testing
4. No need to write audit logs anymore!

I wish I had come across AXON earlier, so that I don't have to write my own event driven framework few years ago.   

#### Swagger API doc
Swagger is so good that it will automatically generate API documentation for you.
Moreover, you can call your server directly using Swagger UI.

# Demo
The server is deployed to Heroku for showcase purpose.
If the server receives no web traffic in a 30-minute period, the VM will be forced to sleep.
When you wake up the server by touching it, the in-memory data will be lost.
This also causes a short delay for this first request, but subsequent requests will perform normally.

[Click here for API list](https://davidtsw-fxserver.herokuapp.com/swagger-ui/)

# Room of improvement
1. Unit testing
2. Proper logging
3. Spring Security
4. Spring Webflux
5. Persist data