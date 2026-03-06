
-- Categories Table
create table categories(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP ,
deleted_at TIMESTAMP,

name VARCHAR(255) NOT NULL UNIQUE
);


-- Products Table

create table products(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
updated_at TIMESTAMP ,
deleted_at TIMESTAMP,

title VARCHAR(255) NOT NULL,
description TEXT NOT NULL,
price DECIMAL(10,3) NOT NULL,
image VARCHAR(255),
rating DECIMAL(3,2),
units INT NOT NULL,
category_id INT NOT NULL,

FOREIGN KEY (category_id) REFERENCES categories(id),
INDEX idx_products_category (category_id)
);


-- Orders Table
create table orders(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP ,
deleted_at TIMESTAMP,

status VARCHAR(100) NOT NULL,
total_value DECIMAL(10,3) NOT NULL
);

-- Order Product Table

create table order_products(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP ,
deleted_at TIMESTAMP,

order_id INT NOT NULL,
product_id INT NOT NULL,

FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (product_id) REFERENCES products(id),

INDEX idx_order_products_order (order_id),
INDEX idx_order_products_product (product_id)
);