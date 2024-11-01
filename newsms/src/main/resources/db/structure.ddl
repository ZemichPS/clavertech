DROP SCHEMA IF EXISTS app CASCADE;
CREATE SCHEMA app;

CREATE TABLE app.news
(

    id         UUID,
    author_id  UUID,
    created_at timestamp,
    updated_at timestamp,
    title      varchar(250),
    text       TEXT,
    constraint pk_news PRIMARY KEY (id)

);

CREATE TABLE app.comments
(
    id         UUID,
    news_id    UUID,
    created_at timestamp,
    updated_at timestamp,
    username   VARCHAR(100),
    text       TEXT,
    constraint pk_comment PRIMARY KEY (id),
    constraint fk_comment_on_news FOREIGN KEY (news_id) REFERENCES app.news (id)
)

