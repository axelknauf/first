(ns first.core
  (:gen-class))

(use 'clojure.java.io)

(defn -main
  "Read a file and produce a hit list of word counts"
  [& args]
  (with-open [rdr (reader "short.txt")]
    (doseq [line (line-seq rdr)]
      (->> (clojure.string/split line #"\ +")
           (println)))))

