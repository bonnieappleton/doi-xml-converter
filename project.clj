(defproject doi-xml-converter "0.1.0-SNAPSHOT"
  :description "An app that converts application data from xml to json from the DOI"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [ring/ring-mock "0.3.2"]
                 [cheshire "5.10.0"]
                 [clj-http "3.10.1"]]
  :main ^:skip-aot doi-xml-converter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns doi-xml-converter.core})
