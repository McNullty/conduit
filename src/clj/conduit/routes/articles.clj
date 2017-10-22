(ns conduit.routes.articles
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST PUT DELETE resource]]
            [clojure.spec.alpha :as s]))

(s/def ::slug string?)
(s/def ::title string?)
(s/def ::description string?)
(s/def ::body string?)

(s/def ::tagList (s/coll-of string?))

(s/def ::createdAt string?)
(s/def ::updatedAt string?)
(s/def ::favorited boolean?)
(s/def ::favoritesCount int?)

(s/def ::username string?)
(s/def ::bio string?)
(s/def ::image string?)
(s/def ::following boolean?)
(s/def ::author (s/keys :req-un [::username ::bio ::image ::following]))

(s/def ::article
  (s/keys :req-un [::slug ::title ::description ::body ::tagList ::createdAt ::updatedAt ::favorited ::favoritesCount ::author]))

(s/def ::articles (s/coll-of ::article))

(s/def ::articlesCount int?)

(s/def ::tag string?)

(def routes
  (context "/articles" []
    :tags ["Articles"]

    (resource
        {:coercion :spec
         :get {:summary "Get recent articles globally"
               :description "Get most recent articles globally. Use query parameters to filter results. Auth is optional"
               :responses {200 {:description "OK"
                                :schema (s/keys :req-un [::articles ::articlesCount])}}
               :parameters {:query-params (s/keys :opt-un [::tag])}
               :handler (fn [_] (ok (str "coll of articles")))}})

    (POST "/" []
      :summary "Create Article"
      (ok (str "Create article")))

    ; (GET "/feed" []
    ;   :summary "Get feed"
    ;   :query-params [{limit :- Long 20} {offset :- Long 0}]
    ;   (ok (str "coll of feed")));; Article

    (GET "/:slug" [slug]
      :summary "Get specific article"
      (ok (str "Get article " slug)))

    (PUT "/:slug" [slug]
      :summary "Update specific article"
      (ok (str "Update article " slug)))

    (DELETE "/:slug" [slug]
      :summary "Delete specific article"
      (ok (str "Delete article " slug "?")))

    ;; Favorite article
    (POST "/:slug/favorite" []
      :summary "Favorite article"
      (ok (str "favorite")))

    (DELETE "/:slug/favorite" []
      :summary "Unfavorite article"
      (ok (str "unfacorite")))

    ;; Comments
    (GET "/:slug/comments" []
      :summary "Get Article comments"
      (ok (str "Get comments for article")))

    (POST "/:slug/comments" []
      :summary "Add comment to article"
      (ok (str "Add comment to article")))

    (DELETE "/:slug/comments/:id" [slug id]
      :summary "Delete comment from article"
      (ok (str "Delete " id " in " slug " article")))))
