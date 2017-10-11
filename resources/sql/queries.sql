-- :name create-user! :<!
-- :doc creates a new user record
INSERT INTO users
(username, email, token, image, bio)
VALUES (:username, :email, :token, :image, :bio)
RETURNING id

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name delete-user! :! :n
-- :doc delete a user given the id
DELETE FROM users
WHERE id = :id

----------------------- TAGS --------------------

-- :name create-tag! :! :n
-- :doc creates a new tag record
INSERT INTO tags
(name)
VALUES (:name)

-- :name get-tags :? :*
-- :doc retrieve all tags.
SELECT * FROM tags

-- :name delete-all-tags! :! :n
-- :doc utility method to clean test database
DELETE FROM tags
