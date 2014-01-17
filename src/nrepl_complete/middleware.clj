(ns nrepl-complete.middleware
  (:require [clojure.string :as s]
            [clojure.tools.nrepl.transport :as transport]
            [clojure.tools.nrepl.middleware :refer [set-descriptor!]]
            [clojure.tools.nrepl.misc :refer [response-for]]
            [complete.core :as jvm-complete]
            [cljs-complete.core :as cljs-complete]))

(defn as-sym
  [x]
  (if x (symbol x)))

(defn complete
  [ns prefix public-only?]
  (jvm-complete/completions (str prefix) ns)
  )

(defn complete-reply
  ;; these are lifted from ritz
  [{:keys [symbol ns public-only? case-sensitive? prefer-ns transport] :as msg}]
  (let [results (complete (as-sym ns) (str symbol) public-only?)]
    (transport/send
     transport (response-for msg :value results))
    (transport/send transport (response-for msg :status :done))
    ))

(defn wrap-complete'
  "Middleware that looks up possible functions for the given (partial) symbol."
  [handler]
  (fn [{:keys [op] :as msg}]
    (if (= "complete" op)
      (complete-reply msg)
      (handler msg))))

(defn wrap-complete
  "Middleware that looks up possible functions for the given (partial) symbol."
  [handler]
  (wrap-complete' handler))

(set-descriptor!
 #'wrap-complete
 {:handles
  {"complete"
   {:doc "Return a list of symbols matching the specified (partial) symbol."
    :requires {"symbol" "The symbol to lookup"
               "ns" "The current namespace"}
    :returns {"status" "done"}}}})


