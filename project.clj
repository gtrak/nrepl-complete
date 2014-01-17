(defproject nrepl-complete "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clojure-complete "0.2.3"]
                 [cljs-complete "0.1.0"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.2"]]
                   :repl-options {:nrepl-middleware [nrepl-complete.middleware/wrap-complete]}
                   :plugins [[com.cemerick/austin "0.1.3"]]}}

  )



