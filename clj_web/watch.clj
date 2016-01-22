(require 'cljs.build.api)
(cljs.build.api/watch "src" {:asset-path "../out" :main 'clj_web.core :output-to "out/main.js"})
