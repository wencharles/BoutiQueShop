
delete from role_permission;
delete from  user_role;
delete from  permissions;
delete from  roles;
delete from  users;

delete from  order_items;
delete from  orders;
delete from  payments;
delete from  customers;
delete from  addresses;
delete from  products;
delete from  categories;



insert into categories(id, name, disp_order,disabled) values
(1,'Clothes',1,false),
(2,'Shoes',2,false),
(3,'Women',3,false),
(4,'Boots',4,false);

INSERT INTO products (id,cat_id,sku,name,description,image_url,price,disabled,created_on) VALUES
 (1,1,'P1001','T-shirt','Good and smart T-shirt','1.jpg','430.00',false,now()),
 (15,1,'P1001','Polo Shirt','Good and smart T-shirt','15.jpg','4500.00',false,now()),
 (16,1,'P1001','Swimmy','Good and smart T-shirt','16.jpg','2000.00',false,now()),
 (17,1,'P1001','Hood','Good and smart T-shirt','17.jpg','3800.00',false,now()),
 (21,2,'P1002','Green','Good smart','21.jpg','4600.00',false,now());


INSERT INTO customers (id,firstname,lastname,email,phone,password) 
VALUES
  (1,'Alfred','A','alfred@gmail.com','999999999','$2a$10$ptpfdPGbiwCzhZs4ScFA7epb/OebazwJw2m1DCeBV8nJHzlgjlty6'),
  (2,'Sopia','S','sopia@gmail.com','8888888888','$2a$10$ptpfdPGbiwCzhZs4ScFA7epb/OebazwJw2m1DCeBV8nJHzlgjlty6');




INSERT INTO permissions (id, name) VALUES
(1, 'MANAGE_CATEGORIES'),
(2, 'MANAGE_PRODUCTS'),
(3, 'MANAGE_ORDERS'),
(4, 'MANAGE_CUSTOMERS'),
(5, 'MANAGE_PAYMENT_SYSTEMS'),
(6, 'MANAGE_USERS'),
(7, 'MANAGE_ROLES'),
(8, 'MANAGE_PERMISSIONS'),
(9, 'MANAGE_SETTINGS')
;

INSERT INTO roles (id, name) VALUES
(1, 'ROLE_SUPER_ADMIN'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_CMS_ADMIN'),
(4, 'ROLE_USER')
;

INSERT INTO users (id, email, password, name) VALUES
(1, 'superadmin@gmail.com', '$2a$10$p3PHnpoBAnzZiL8mr3gMieMhVVSb585ajC2ZsBB0kwb4KvZKFSdNi', 'Super Admin'),
(2, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin'),
(3, 'cms@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'CMSGuy'),
(4, 'sopia@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Sopia'),
(5, 'user@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'DemoUser')
;



insert into role_permission(role_id, perm_id) values
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),
(2,1),(2,2),(2,3),(2,4),(2,5),(2,9),
(3,1),(3,2)
;

insert into user_role(user_id, role_id) values
(1,1),
(2,2),
(3,3),
(4,2),(4,3),
(5,4)
;