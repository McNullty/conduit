(ns conduit.routes.articles
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST PUT DELETE resource]]))

(def routes
  (context "/articles" []
    :tags ["Articles"]

    (GET "/" []
      :summary "Get all articles"
      :query-params [{tag :- String ""}
                     {author :- String ""}
                     {favorited :- String ""}
                     {limit :- Long 20}
                     {offset :- Long 0}]
      (ok (str "coll of articles")))

    (POST "/" []
      :summary "Create Article"
      (ok (str "Create article")))

    (GET "/feed" []
      :summary "Get feed"
      :query-params [{limit :- Long 20} {offset :- Long 0}]
      (ok (str "coll of feed")));; Article

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
