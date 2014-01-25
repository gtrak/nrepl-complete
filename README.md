# nrepl-complete

a middleware abstracting autocompletion for clj and cljs into an nrepl "complete" op.

## Usage

for auto-completion in cljs, refer to piggieback and austin documentation for how to set those up.

Add nrepl-complete to your dev dependencies, and add the nrepl middleware to your project.

```clojure
:profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.2"]
                                [nrepl-complete "0.1.0]]
                 :repl-options {:nrepl-middleware [nrepl-complete.middleware/wrap-complete]}
                 :plugins [[com.cemerick/austin "0.1.3"]]}}
```

Start up a normal repl.  Start up a cljs repl with `(cemerick.piggieback/cljs-repl)` or another method.

Use an nrepl client that has support for a "complete" op.  Cider's on the way.  More can be added here.

Have fun.

## License

Copyright © 2014 Gary Trakhman

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
