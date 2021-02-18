INSERT INTO tools (description, link, title) values('All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.', 'https://notion.so', 'Notion')
INSERT INTO tools (description, link, title) values('Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.', 'https://github.com/typicode/json-server', 'json-server')

INSERT INTO tags (name) values ('organization')
INSERT INTO tags (name) values ('planning')
INSERT INTO tags (name) values ('collaboration')
INSERT INTO tags (name) values ('writing')
INSERT INTO tags (name) values ('calendar')
INSERT INTO tags (name) values ('api')
INSERT INTO tags (name) values ('json')
INSERT INTO tags (name) values ('schema')
INSERT INTO tags (name) values ('node')
INSERT INTO tags (name) values ('github')
INSERT INTO tags (name) values ('rest')

INSERT INTO tool_tag (tool_id, tag_id) values (1,1)
INSERT INTO tool_tag (tool_id, tag_id) values (1,2)


