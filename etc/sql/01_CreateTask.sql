INSERT INTO "task" (user_id, title, description, created_at, resolved, deadline) VALUES (2, 'Przygotowanie składników do zupy', 'opis', now(), FALSE, now());
insert into "group" (created_at, description, name) VALUES (now(), 'Przykładowa grupa', 'Moja grupka');
insert into "user_to_group" (group_id, user_id) VALUES (1, 1);