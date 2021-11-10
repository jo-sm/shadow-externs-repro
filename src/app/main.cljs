(ns app.main
  (:require ["random-module" :refer [coolFunction]]))

; coolFunction returns N objects with the type of { someProperty: number }.
; See <repo-root>/random-module/index.js
;
; In `:target :browser` with `:infer-externs :auto` this works without warnings
; , as Shadow can figure out that `.someProperty` exists. In `:target :npm-module`
; however, it can't, and the only way to get it to work is to add `^js` as a type
; hint to `%`. I also can't seem to get `externs.js` to work, but maybe that's just
; a misunderstanding issue.

; TL;DR: for `:target :npm-module` this produces a type inference warning...
(let [objects (coolFunction 20)]
  (->> objects
       (map #(-> % .-someProperty))))

; ...and this does not
(let [objects (coolFunction 20)]
  (->> objects
       (map #(-> ^js % .-someProperty))))
