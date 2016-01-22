(require 'cljs.repl)
(require 'cljs.build.api)
(require 'cljs.repl.browser)

(cljs.build.api/build "src" 
                      {:asset-path "../out" 
                       :main 'clj_web.core 
                       :output-to "out/main.js"
                       :verbose true})

(cljs.repl/repl (cljs.repl.browser/repl-env)
                :watch "src"
                :watch-fn (fn [] (println "rebuilt!"))
                :output-dir "out")
