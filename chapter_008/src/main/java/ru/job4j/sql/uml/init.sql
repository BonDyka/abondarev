-- roles
INSERT INTO roles (name) VALUES ('Admin');
INSERT INTO roles (name) VALUES ('Producer');
INSERT INTO roles (name) VALUES ('Consumer');

-- rules

INSERT into rules (name) VALUES ('Add');
INSERT into rules (name) VALUES ('Remove');
INSERT into rules (name) VALUES ('Handle');
INSERT into rules (name) VALUES ('Search');

-- role_rules
INSERT INTO role_rules (role_id, rule_id) VALUES (1, 1);
INSERT INTO role_rules (role_id, rule_id) VALUES (1, 2);
INSERT INTO role_rules (role_id, rule_id) VALUES (1, 4);
INSERT INTO role_rules (role_id, rule_id) VALUES (2, 1);
INSERT INTO role_rules (role_id, rule_id) VALUES (2, 4);
INSERT INTO role_rules (role_id, rule_id) VALUES (3, 4);
INSERT INTO role_rules (role_id, rule_id) VALUES (3, 3);

-- users
INSERT INTO users (name, role_id) VALUES ('abondarev', 1);
INSERT INTO users (name, role_id) VALUES ('prod test', 2);
INSERT INTO users (name, role_id) VALUES ('cons1 test', 3);
INSERT INTO users (name, role_id) VALUES ('cons2 test', 3);

-- states
INSERT INTO states (name) VALUES ('Created');
INSERT INTO states (name) VALUES ('Process');
INSERT INTO states (name) VALUES ('Handled');
INSERT INTO states (name) VALUES ('Closed');

-- categories
INSERT INTO categories (name) VALUES ('Bug');
INSERT INTO categories (name) VALUES ('Task');
INSERT INTO categories (name) VALUES ('Test');

-- items
INSERT INTO items (item_id, name, description, create_date, user_id, category_id, state_id)
    VALUES ('xd2458', 'test', 'test program', now(), 1, 3, 1);
INSERT INTO items (item_id, name, description, create_date, user_id, category_id, state_id)
    VALUES ('xr3751', 'task', 'create program', now(), 2, 3, 2);
INSERT INTO items (item_id, name, description, create_date, user_id, category_id, state_id)
    VALUES ('az5784', 'bug', 'fix bug', now(), 3, 1, 3);

-- attaches
INSERT INTO attaches (name, content, item_id) VALUES ('testing_prog', NULL , 1);
INSERT INTO attaches (name, content, item_id) VALUES ('task_info', NULL , 2);
INSERT INTO attaches (name, content, item_id) VALUES ('task_test', NULL , 2);
INSERT INTO attaches (name, content, item_id) VALUES ('bug_info', NULL , 3);
INSERT INTO attaches (name, content, item_id) VALUES ('bug_test', NULL , 3);

-- comments
INSERT INTO comments (name, create_date, item_id) VALUES ('test_comment', current_timestamp, 1);
INSERT INTO comments (name, create_date, item_id) VALUES ('bad comment', current_timestamp, 3);
INSERT INTO comments (name, create_date, item_id) VALUES ('good comment', current_timestamp, 2);