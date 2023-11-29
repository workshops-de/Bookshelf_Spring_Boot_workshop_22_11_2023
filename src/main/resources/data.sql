DELETE FROM book;

INSERT INTO book (isbn, title, author, description) VALUES ('978-0201633610', 'Design Patterns', 'Erich Gamma', 'Mit Design Patterns lassen sich wiederkehrende Aufgaben in der objektorientierten Softwareentwicklung effektiv lösen.');
INSERT INTO book (isbn, title, author, description) VALUES ('978-3826655487', 'Clean Code', 'Robert C. Martin', 'Das einzige praxisnahe Buch, mit dem Sie lernen, guten Code zu schreiben!');
INSERT INTO book (isbn, title, author, description) VALUES ('978-3836211161', 'Coding for Fun', 'Gottfried Wolmeringer', 'Dieses unterhaltsam geschriebene Buch führt Sie spielerisch durch die spektakuläre Geschichte unserer Blechkollegen.');


DELETE FROM bookshelf_user;

INSERT INTO bookshelf_user (username, password, roles) VALUES ('dbUser', '$2a$10$bLVsMd5mO56RzC4Q5fagv.Qc3GkpPqkYpMKid6NrmTcCzTKs7hPYm', 'ROLE_USER');
INSERT INTO bookshelf_user (username, password, roles) VALUES ('dbAdmin', '$2a$10$xuegqkXNOdv4/zIbBmvI7.QYD01V58nlFuEvF3GT.FZQ6Nb9KdpmC', 'ROLE_USER,ROLE_ADMIN');
