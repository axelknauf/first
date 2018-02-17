(ns first.core
  (:gen-class))

(use 'clojure.java.io)

(def collected-words (atom {}))

(defn inc-word-using-key
  "increments a single word count in the given map with key"
  [m k]
  (update-in m [k] (fnil inc 0)))

(defn inc-word-count [word]
  "updates the count for the given word in `collected-words`"
  (swap! collected-words inc-word-using-key (keyword word)))

(defn process-line
  "counts the words in the given vector"
  [words]
  (doseq [word words]
    (inc-word-count word)))

(defn process-file
  "reads the given file line by line and extracts the words"
  [filename]
  (with-open [rdr (reader filename)]
    (doseq [line (line-seq rdr)]
      (process-line (clojure.string/split line #"\ +")))))

(defn -main
  "Read a file and produce a hit list of word counts"
  [& args]
  (process-file "short.txt") ; try some.txt
  (println @collected-words))
