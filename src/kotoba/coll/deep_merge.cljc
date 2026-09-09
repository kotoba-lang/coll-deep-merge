(ns kotoba.coll.deep-merge
  "deep-merge -- one definition, addressed on its own.

  Split out of kotoba.lang.text on 2026-09-09. The unit here is the
  DEFINITION, not the library: this repo holds deep-merge and names, in its
  deps.edn, exactly the definitions deep-merge reaches. Nothing else."
  )

(defn deep-merge
  "Recursively merge maps: when both values at a key are maps, they are merged
  recursively; otherwise the rightmost value wins. Scalars and collections that
  are not both maps are replaced (not concatenated) — this is merge semantics,
  not conj semantics."
  ([a] a)
  ([a b]
   (if (and (map? a) (map? b))
     (merge-with deep-merge a b)
     b))
  ([a b & more]
   (reduce deep-merge (deep-merge a b) more)))
