ALTER TABLE categories
    DROP CONSTRAINT categories_name_key;

ALTER TABLE categories
    ADD CONSTRAINT uk_user_category_name UNIQUE (user_id, name)
