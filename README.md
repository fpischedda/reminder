# reminder

Periodically send reminders to friends/collegaues until they decide it is time
to help you with whatever you requested them...or until you lose their
friendship...

## Installation

Clone from https://github.com/fpischedda/reminder

## Build

    $ lein deps

    $ lein compile :all

## Tests

    $ lein midje :all

## Run locally

    $ docker-compose up -d mongodb
    $ lein run

This starts the web server lisetning to port 8080

## License

Copyright © 2018 Francesco Pischedda

Distributed under the AGLP 3 License
