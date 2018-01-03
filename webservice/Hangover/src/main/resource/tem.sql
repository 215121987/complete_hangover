with(id, category, parent_category_id, level, hierarchy) as
(select a.id, a.category, a.parent_category_id, b.level+1,b.hierarchy+' -> '+ a.category from category a inner join tree b on a.parent_category_id = b.id)
select id, category, parent_category_id, level, hierarchy from tree order by category;


 DELIMITER $$

CREATE PROCEDURE `GetItem`(int_catId varchar(20))
BEGIN
 with tree(id, Name, parent_category_id, level, hierarchy) as
(
 select id, Name, parent_category_id, 0, cast(Name as varchar(max))
   from category
   where id is 0
 union all
 select a.id, a.Name, a.parent_category_id, b.level+1,
   b.hierarchy+' -> '+a.category
  from category a
  inner join tree b on a.parent_category_id=b.id
)
select id,  Name, parent_category_id, level, hierarchy
 from tree
 order by hierarchy;

 END $$
 DELIMITER;


  with tree(StaffID, Manager, Name, JobTitle, Level, Hierarchy) as
(
 select StaffID, null, Name, JobTitle, 0, cast(Name as varchar(max))
   from tblStaff
   where Manager is null
 union all
 select a.StaffID, a.Manager, a.Name, a.JobTitle, b.Level+1,
   b.Hierarchy+' -> '+a.Name
  from tblStaff a
  inner join tree b on a.Manager=b.StaffID
)
select StaffID, Manager, Name, JobTitle, Level, Hierarchy
 from tree
 order by Hierarchy


insert into user_address(id, address, street, city, state, country, zipCode, user_id) value(1,'4th B cross, 9th Main', 'New Thippasandra', 'Bangalore', 'Karnataka', 'India', '566075',1);

insert into service_charge(id, name, value, is_percent) value(1,'VAT',14, 1);
insert into service_charge(id, name, value, is_percent) value(2,'CST',5, 1);
insert into service_charge(id, name, value, is_percent) value(3,'SBAT',0.5, 1);
insert into service_charge(id, name, value, is_percent) value(4,'KKT',0.5, 1);
insert into service_charge(id, name, value, is_percent) value(5,'Min Delivery Value',299.00, 0);
insert into service_charge(id, name, value, is_percent) value(6,'delivery charge',50.00, 0);

 insert into category(id, name, description, level, parent_category_id) value(1, 'Alcohol', 'Alcohol', 0, null);
 insert into category(id, name, description, level, parent_category_id) value(2, 'Whisky', 'Whisky', 0, 1);
 insert into category(id, name, description, level, parent_category_id) value(3, 'Wine', 'Wine', 1, 1);
 insert into category(id, name, description, level, parent_category_id) value(4, 'Rum', 'Indoor', 1, 1);
 insert into category(id, name, description, level, parent_category_id) value(5, 'Beer', 'Beer', 1, 1);
 insert into category(id, name, description, level, parent_category_id) value(6, 'Vodka', 'Vodka', 1, 1);
 insert into category(id, name, description, level, parent_category_id) value(7, 'Scotch', 'Scotch', 1, 1);

insert into brand(id, code, name,displayName, description, category_id) value(1,'KK', 'Kingfisher', 'Kingfisher', 'Kingfisher',5);
insert into brand(id, code, name,displayName, description, category_id) value(2,'cb', 'Carlsberg', 'Carlsberg', 'Carlsberg',5);
insert into brand(id, code, name,displayName, description, category_id) value(3,'bws', 'Budweiser', 'Budweiser', 'Budweiser',5);
insert into brand(id, code, name,displayName, description, category_id) value(4,'Heineken', 'Heineken', 'Heineken', 'Heineken',5);
insert into brand(id, code, name,displayName, description, category_id) value(5,'crn', 'Corona', 'Corona', 'Corona',5);
insert into brand(id, code, name,displayName, description, category_id) value(6,'fl', 'Foster Lager', 'Foster Lager', ' Foster Lager',5);
insert into brand(id, code, name,displayName, description, category_id) value(7,'ds', 'Director’s Special', 'Director’s Special', 'Director’s Special',2);
insert into brand(id, code, name,displayName, description, category_id) value(8,'8pm', '8 PM Royale', '8 PM Royale', '8 PM Royale',2);
insert into brand(id, code, name,displayName, description, category_id) value(9,'ib', 'Imperial Blue', 'Imperial Blue', 'Imperial Blue',2);
insert into brand(id, code, name,displayName, description, category_id) value(10,'bpp', 'Bagpiper', 'Bagpiper', 'Bagpiper',2);
insert into brand(id, code, name,displayName, description, category_id) value(11,'rs', 'Royal Stag', 'Royal Stag', 'Royal Stag',2);
insert into brand(id, code, name,displayName, description, category_id) value(12,'oc', 'Officers Choice', 'Officers Choice', 'Officers Choice',2);
insert into brand(id, code, name,displayName, description, category_id) value(13,'mcd', 'McDowell’s No 1', 'McDowell’s No 1', 'McDowell’s No 1',2);
insert into brand(id, code, name,displayName, description, category_id) value(14,'snf', 'Smirnoff', 'Smirnoff', 'Smirnoff',6);
insert into brand(id, code, name,displayName, description, category_id) value(15,'esf', 'Eristoff', 'Eristoff', 'Eristoff',6);
insert into brand(id, code, name,displayName, description, category_id) value(16,'wm', 'White Mischief', 'White Mischief', 'White Mischief',6);
insert into brand(id, code, name,displayName, description, category_id) value(17,'wm', 'Romanov', 'Romanov', 'Romanov',6);
insert into brand(id, code, name,displayName, description, category_id) value(18,'mm', 'Magic Moments', 'Magic Moments', 'Magic Moments',6);
insert into brand(id, code, name,displayName, description, category_id) value(19,'abs', 'Absolut', 'Absolut', 'Absolut',6);

insert into supplier(id, code, companyName) value(1,'lad', 'Liquor At Door');
insert into supplier_store(id, street, city,state, country, zipCode,supplier_id , mainBranch) value(1,'New Thippasandra', 'Bangalore', 'Karnataka', 'India','560075', 1, 1);


insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(1,'Romano', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,3, 1);


insert into item(id, name, description,itemFor,image_url, store_id, category_id, brand_id, count)
   value(1,'Romanov Apple Vodka','Another award winning (a two-star rating from International taste and Quality Institute) vodka from the house of United Breweries, Romanov gets its name from the last Russian czar. Romanov is present in four flavour variants that include Premium, Orange, Lemon and Apple giving ample flavour choices to its users.',0,'http://104.155.202.8/hangover/images/product/vodka_romanov.jpg', 1,6, 17, 10);
insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(1,'750ML', '20','', '453', 1);

insert into item(id, name, description,itemFor,image_url, store_id, category_id, brand_id, count)
   value(2,'Smirnoff Honey Triple Distilled Vodka','Smirnoff is produced by the British company Diageo, one of the biggest alcohol companies in the world. Smirnoff Vodka is triple distilled and made from the finest grains. Smirnoff won the Gold medal in the San Francisco World Spirit Awards, 2012. Smirnoff offers a range of flavours that include Smirnoff Vanilla, Smirnoff Lime, Smirnoff Green Apple, Smirnoff Orange.',0,'http://104.155.202.8/hangover/images/product/vodka_smirnoff.jpg', 1,6, 17, 10);
insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(2,'750ML', '20','', '1117', 2);






   insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(2,'Beer', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,2, 1);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(3,'300', '20','AXYZ', '79', 2);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(4,'600', '20','AXYZ', '179', 2);

   insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(3,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(5,'300', '20','AXYZ', '99', 3);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(6,'600', '20','AXYZ', '199', 3);

   insert into user_address(id, address, street, city, state, country, zipCode, user_id) value(1,'4th B cross, 9th Main', 'New Thippasandra', 'Bangalore', 'Karnataka', 'India', '566075',1);
   insert into user_address(id, address, street, city, state, country, zipCode, user_id) value(2,'2nd cross, 6th Main', 'Indira Nagar', 'Bangalore', 'Karnataka', 'India', '566036',1);

  insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(4,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(7,'300', '20','AXYZ', '99', 4);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(8,'600', '30','AXYZ', '199', 4);

   insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(5,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1);
   insert into item_detail(id, size, quantity, modelNumber,  sellingPrice,item_id) value(9,'300', '40','AXYZ', '99', 5);
   insert into item_detail(id, size, quantity, modelNumber,  sellingPrice,item_id) value(10,'600', '30','AXYZ', '199', 5);


   insert into order_entity(id, orderId, customer_id, totalAmount,  order_from,shipping_address_id) value(1,'12345678', 1, 1299, '0', 1);
   insert into order_entity(id, orderId, customer_id, totalAmount,  order_from,shipping_address_id) value(2,'12345679', 1, 1399, '0', 1);
   insert into order_item(id, item_id, itemSize, price,  quantity,order_id, order_item_state, deliverBy) value(1,1,'300', 199, 5, 1, 0, null);
   insert into order_item(id, item_id, itemSize, price,  quantity,order_id, order_item_state, deliverBy) value(2,1,'600', 299, 5, 1, 0, null);

   insert into order_item(id, item_id, itemSize, price,  quantity,order_id, order_item_state, deliverBy) value(3,1,'600', 399, 5, 2, 0, null);

   insert into service_charge(id, name, value, is_percent) value(1,'VAT',14, 1);
   insert into service_charge(id, name, value, is_percent) value(2,'CST',5, 1);
   insert into service_charge(id, name, value, is_percent) value(3,'SBAT',0.5, 1);
   insert into service_charge(id, name, value, is_percent) value(4,'KKT',0.5, 1);
   insert into service_charge(id, name, value, is_percent) value(5,'Min Delivery Value',200, 0);
   insert into service_charge(id, name, value, is_percent) value(6,'delivery charge',100, 0);


   insert into user(id,created_at, updated_at,accountNonExpired,accountNonLocked,credentialsNonExpired, enabled,
  name, username, email, password, password_type, numberVerified)
   value(2, '2015-06-26 12:40:55', '2015-06-26 12:40:55',1,1,1,1, 'Lokesh Sharma','lokesh.sharma@gmail.com','lokesh.sharma@gmail.com',
'8e5d3299950d242bd51ea91c68ac08f9a79852b83a84822e3cc33ae51c3bea4208800afba20c6637c0c1d102bffbdf9cd3ddeb30658992c5553ab6d8ba0363f5',0,1);

insert into user_role(user_id,role_id) value(2,3);

insert into user(id,created_at, updated_at,accountNonExpired,accountNonLocked,credentialsNonExpired, enabled,
  name, username, email, password, password_type, numberVerified)
   value(3, '2015-06-26 12:40:55', '2015-06-26 12:40:55',1,1,1,1, 'Uday Kumar','uday.kumar@gmail.com','uday.kumar@gmail.com',
'8e5d3299950d242bd51ea91c68ac08f9a79852b83a84822e3cc33ae51c3bea4208800afba20c6637c0c1d102bffbdf9cd3ddeb30658992c5553ab6d8ba0363f5',0,1);

insert into user_role(user_id,role_id) value(3,5);

insert into user(id,created_at, updated_at,accountNonExpired,accountNonLocked,credentialsNonExpired, enabled,
  name, username, email, password, password_type,numberVerified)
   value(4, '2015-06-26 12:40:55', '2015-06-26 12:40:55',1,1,1,1, 'Rahul Kundra','rahul.kundra@gmail.com','rahul.kundra@gmail.com',
'8e5d3299950d242bd51ea91c68ac08f9a79852b83a84822e3cc33ae51c3bea4208800afba20c6637c0c1d102bffbdf9cd3ddeb30658992c5553ab6d8ba0363f5',0,1);

insert into user_role(user_id,role_id) value(4,6);

insert into user(id,created_at, updated_at,accountNonExpired,accountNonLocked,credentialsNonExpired, enabled,
  name, username, email, password, password_type,numberVerified)
   value(5, '2015-06-26 12:40:55', '2015-06-26 12:40:55',1,1,1,1, 'Arvind Kumar','Arvind.Kumar@gmail.com','Arvind.Kumar@gmail.com',
'8e5d3299950d242bd51ea91c68ac08f9a79852b83a84822e3cc33ae51c3bea4208800afba20c6637c0c1d102bffbdf9cd3ddeb30658992c5553ab6d8ba0363f5',0,1);

insert into user_role(user_id,role_id) value(5,4);

insert into supplier_staff(user_id, supplier_id, status, store_id) value(1,1,0,1);













 SELECT t1.id, t1.name, t1.parent_category_id FROM
category AS t1 LEFT JOIN category as t2
ON t1.id = t2.parent_category_id
WHERE t2.id IS NULL;


select @pv:=id as category_id, name, parent_category_id from category join (select @pv:=1)tmp where parent_category_id=@pv;


select  id,  name,parent_category_id from    (select * from category order by parent_category_id, id) base, (select @pv := '2') tmp where   find_in_set(parent_category_id, @pv) > 0 and     @pv := concat(@pv, ',', id);



select  id,  name,parent_category_id from    (select * from category) base, (select @pv := '1,3,5,6') tmp where find_in_set(parent_category_id, @pv)>0 or find_in_set(id, @pv) and     @pv := concat(@pv, ',', id);





insert into item(id, name, description,itemFor,image_url, store_id, category_id, brand_id, count,item_status)
   value(1,'Romanov Apple Vodka','Another award winning (a two-star rating from International taste and Quality Institute) vodka from the house of United Breweries, Romanov gets its name from the last Russian czar. Romanov is present in four flavour variants that include Premium, Orange, Lemon and Apple giving ample flavour choices to its users.','UNISEX','http://104.155.202.8/hangover/images/product/vodka_romanov.jpg',1 ,6, 17, 10, 'AVAILABLE');
insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(1,'750ML', '20','', '453', 1);

insert into item(id, name, description,itemFor,image_url, store_id, category_id, brand_id, count,item_status)
   value(2,'Smirnoff Honey Triple Distilled Vodka','Smirnoff is produced by the British company Diageo, one of the biggest alcohol companies in the world. Smirnoff Vodka is triple distilled and made from the finest grains. Smirnoff won the Gold medal in the San Francisco World Spirit Awards, 2012. Smirnoff offers a range of flavours that include Smirnoff Vanilla, Smirnoff Lime, Smirnoff Green Apple, Smirnoff Orange.','UNISEX','http://104.155.202.8/hangover/images/product/vodka_smirnoff.jpg', 1,6, 17, 10,'AVAILABLE');
insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(2,'750ML', '20','', '1117', 2);




 insert into item(id, name, description,count, supplier_location_id, category_id, brand_id, item_status, item_for) value(2,'Beer', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,2, 1,'AVAILABLE', 'UNISEX');
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(3,'300', '20','AXYZ', '79', 2);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(4,'600', '20','AXYZ', '179', 2);

   insert into item(id, name, description,count, supplier_location_id, category_id, brand_id, item_status, item_for) value(3,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1,'AVAILABLE', 'UNISEX');
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(5,'300', '20','AXYZ', '99', 3);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(6,'600', '20','AXYZ', '199', 3);

   insert into user_address(id, address, street, city, state, country, zipCode, user_id) value(1,'4th B cross, 9th Main', 'New Thippasandra', 'Bangalore', 'Karnataka', 'India', '566075',1);
   insert into user_address(id, address, street, city, state, country, zipCode, user_id) value(2,'2nd cross, 6th Main', 'Indira Nagar', 'Bangalore', 'Karnataka', 'India', '566036',1);

  insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(4,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(7,'300', '20','AXYZ', '99', 4);
   insert into item_detail(id, itemSize, quantity, modelNumber,  sellingPrice,item_id) value(8,'600', '30','AXYZ', '199', 4);

   insert into item(id, name, description,count, supplier_location_id, category_id, brand_id) value(5,'Wine', 'http://liquoratdoor.com/hangover/images/product/Antica-Formula-Carpano-min.png',10, 1,5, 1);
   insert into item_detail(id, size, quantity, modelNumber,  sellingPrice,item_id) value(9,'300', '40','AXYZ', '99', 5);
   insert into item_detail(id, size, quantity, modelNumber,  sellingPrice,item_id) value(10,'600', '30','AXYZ', '199', 5);
