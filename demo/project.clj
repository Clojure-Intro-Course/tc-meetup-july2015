(defproject demo "0.1.0-SNAPSHOT"
  :description "demo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [quil.core]
                 [quil.middleware]
                 [quil.q_functions]]
  :main demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
