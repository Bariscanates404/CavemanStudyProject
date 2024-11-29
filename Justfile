help:
    just --list

run:
    clojure -M -m example.main

greet:
    echo "Hello, Barış!"

nrepl:
    clojure -M:nREPL -m nrepl.cmdline