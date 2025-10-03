-- Користувачі
INSERT INTO users (id, username, email, password_hash, created_at, updated_at)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'alice', 'alice@example.com', '$2a$10$abcdefghijklmnopqrstuv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('22222222-2222-2222-2222-222222222222', 'bob',   'bob@example.com',   '$2a$10$abcdefghijklmnopqrstuv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('33333333-3333-3333-3333-333333333333', 'carol', 'carol@example.com', '$2a$10$abcdefghijklmnopqrstuv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('44444444-4444-4444-4444-444444444444', 'dave',  'dave@example.com',  '$2a$10$abcdefghijklmnopqrstuv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('55555555-5555-5555-5555-555555555555', 'eve',   'eve@example.com',   '$2a$10$abcdefghijklmnopqrstuv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Пости
INSERT INTO posts (id, slug, title, content, user_id, created_at, updated_at)
VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'spring-intro', 'Intro to Spring', 'This is Alice''s first post.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'jwt-auth', 'Securing REST with JWT', 'Alice explains JWT authentication.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'spring-data', 'Spring Data JPA', 'Alice writes about Spring Data.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'react-hooks', 'React Hooks Basics', 'Bob writes about React.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'docker-guide', 'Docker for Devs', 'Bob shares a Docker guide.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'spring-security', 'Spring Security', 'Bob explores Spring Security basics.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'kafka-intro', 'Intro to Kafka', 'Carol writes about Kafka.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'junit-testing', 'JUnit Testing', 'Carol shares JUnit tips.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ccccccc3-cccc-cccc-cccc-ccccccccccc3', 'spring-boot', 'Spring Boot Tips', 'Carol explains Spring Boot configuration.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'typescript-basics', 'TypeScript Basics', 'Dave writes about TypeScript.', '44444444-4444-4444-4444-444444444444', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ddddddd2-dddd-dddd-dddd-ddddddddddd2', 'nodejs-api', 'Node.js API', 'Dave shares Node.js API patterns.', '44444444-4444-4444-4444-444444444444', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ddddddd3-dddd-dddd-dddd-ddddddddddd3', 'docker-compose', 'Docker Compose', 'Dave explains Docker Compose usage.', '44444444-4444-4444-4444-444444444444', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeee1', 'python-data', 'Python for Data', 'Eve writes about Python data libraries.', '55555555-5555-5555-5555-555555555555', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('eeeeeee2-eeee-eeee-eeee-eeeeeeeeeee2', 'ml-intro', 'Intro to Machine Learning', 'Eve explains ML basics.', '55555555-5555-5555-5555-555555555555', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('eeeeeee3-eeee-eeee-eeee-eeeeeeeeeee3', 'flask-api', 'Building APIs with Flask', 'Eve shares Flask API tips.', '55555555-5555-5555-5555-555555555555', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
