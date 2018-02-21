(defproject first "0.1.0-SNAPSHOT"
  :description "Create word count top-ten from file"
  :url "n/a"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot first.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
