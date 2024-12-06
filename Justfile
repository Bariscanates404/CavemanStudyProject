help:
    just --list

run:
    clojure -M -m example.main

greet:
    echo "Hello, Barış!"

nrepl:
    clojure -M:nREPL -m nrepl.cmdline

format_check:
    clojure -M:format -m cljfmt.main check src dev

format:
    clojure -M:format -m cljfmt.main fix src dev

lint:
    clojure -M:lint -m clj-kondo.main --lint .

test:
    clojure -M:dev -m kaocha.runner