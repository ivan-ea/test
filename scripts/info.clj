(ns info 
  (:require [babashka.process :as pr]
            [babashka.fs :as fs]
            [clojure.string :as str]))

(def FILES "Configuration constants that are files"
  {:fiji-home     (fs/file (System/getProperty "user.home") "blank_fiji")})

(def os-related-info
  "Names of the fiji zip and executable depend on the OS"
  {"windows" {:zip-name   "fiji-win64.zip"
              :executable (str (fs/file (:fiji-home FILES) "Fiji.app" "ImageJ-win64.exe"))}
   "linux"   {:zip-name   "fiji-linux64.zip"
              :executable (str (fs/file (:fiji-home FILES) "Fiji.app" "ImageJ-linux64"))}
   "mac"     {:zip-name   "fiji-macosx.zip"
              :executable (str (fs/file (:fiji-home FILES) "Fiji.app" "Contents" "MacOS" "ImageJ-macosx"))}})

(def current-os-info
  "Map entry of the actual OS that is running the program"
  (last (first (filter (fn [[k v]] (str/includes? (str/lower-case (System/getProperty "os.name")) k)) os-related-info))))

(def CONSTANTS "Constants that are not files"
  {:fiji-flags             ["--headless" "--ij2" "--console" "--run"]
   :fiji-zip-name          (:zip-name current-os-info)
   :fiji-executable        (:executable current-os-info)})

(defn os-name [_]
  (println "The OS you are in, according to java is:" (System/getProperty "os.name")))

(defn ls-fiji [_]
  (pr/shell "ls" "-l" (:fiji-executable CONSTANTS)))

(os-name nil)