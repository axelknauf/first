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
  (swap! collected-words inc-word-using-key
         (keyword (clojure.string/lower-case word))))

(defn sanitize-words
  "filters and cleans up the given list of words"
  [words]
  (->> (map clojure.string/trim words)
       (filter (complement empty?))))

(defn process-line
  "counts the words in the given vector"
  [input-words]
  (doseq [word (sanitize-words input-words)]
    (inc-word-count word)))

(defn process-file
  "reads the given file line by line and extracts the words"
  [filename]
  (with-open [rdr (reader filename)]
    (doseq [line (line-seq rdr)]
      (process-line (clojure.string/split line #"\ +")))))

(defn sort-map-by-value
  "sorts the given map by the value descending"
  [m]
  (into (sorted-map-by (fn [key1 key2]
                         (compare [(get m key2) key2]
                                  [(get m key1) key1])))
        m))

(defn top-ten
  "fetches the top ten entries from the given map"
  [m]
  (take 10 (sort-map-by-value m)))

(defn print-entry
  "prints a single entry from the hitlist"
  [[w c]]
  (println (subs (str w) 1) "->" c))

(defn -main
  "Read a file and produce a hit list of word counts"
  [& args]
  (process-file "some.txt") ; try some.txt
  (->> (top-ten @collected-words)
       (map print-entry)))
