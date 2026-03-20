CREATE TABLE IF NOT EXISTS reviews (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP,

    product_id INT NOT NULL,
    order_id INT NOT NULL,
    rating DECIMAL(3, 2) NOT NULL,
    comment TEXT,

    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);