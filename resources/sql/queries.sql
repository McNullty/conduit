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
