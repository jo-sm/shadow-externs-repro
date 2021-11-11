(ns app.main
  (:require ["random-module" :refer [coolFunction]]))

; `coolFunction` returns an array of N objects with the type of { someProperty: number }.
; See <repo-root>/random-module/index.js
;
; In `:target :browser`, code that uses the result of this function works without compilation warnings, as
; `shadow-cljs` seems to be able to figure out that `.someProperty` exists. In `:target :npm-module`
; however, it doesn't seem to work as expected. I expected that it would work if adding `^js` to the
; binding but this fails and the only way to get it to work (in this case) is to add the type hint to
; the anonymous function's argument `%`.
;
; In other words, if using an anonymous function with `%`, `:target :browser` can figure out
; if the property exists on the result of a JS function while `:target :npm-module` cannot, even with a
; type hint on the binding.
;
; ---
;
; This warns in `:npm-module`, as expected.
(let [objects (coolFunction 20)]
  (->> objects
       (map #(-> % .-someProperty))))

; This also unexpectedly warns
(let [objects ^js (coolFunction 20)]
  (->> objects
       (map #(-> % .-someProperty))))

; This does not warn
(let [objects (coolFunction 20)]
  (->> objects
       (map #(-> ^js % .-someProperty))))
