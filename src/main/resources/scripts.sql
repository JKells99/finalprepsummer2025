CREATE TABLE users2 (
        id SERIAL PRIMARY KEY,
        username VARCHAR(50) UNIQUE NOT NULL,
        password TEXT NOT NULL, -- This stores the BCrypt hash
        role VARCHAR(20) NOT NULL CHECK (role IN ('Commander', 'Chief'))
);

CREATE TABLE products (
              id SERIAL PRIMARY KEY,
              name VARCHAR(100) NOT NULL,
              description TEXT,
              price DECIMAL NOT NULL,
              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO products (name, description, price) VALUES
                ('Apple iPhone 14', 'Latest Apple smartphone with advanced features', 799.00),
                ('Samsung Galaxy S23', 'Flagship Android phone with high-end specs', 749.99),
                ('Sony WH-1000XM5', 'Noise-cancelling wireless headphones', 349.99),
                ('Dell XPS 13', 'Compact and powerful ultrabook laptop', 999.00),
                ('Apple MacBook Air', 'Lightweight laptop with M2 chip', 1099.00),
                ('Logitech MX Master 3', 'Ergonomic wireless mouse', 99.99),
                ('Amazon Echo Dot', 'Smart speaker with Alexa', 49.99),
                ('Google Nest Hub', 'Smart display for home automation', 89.99),
                ('Fitbit Charge 5', 'Advanced fitness tracker', 129.95),
                ('Nintendo Switch', 'Hybrid gaming console', 299.99),
                ('Canon EOS Rebel T7', 'Entry-level DSLR camera', 449.00),
                ('GoPro HERO11', 'Action camera for adventure', 399.99),
                ('JBL Flip 6', 'Portable Bluetooth speaker', 119.95),
                ('Kindle Paperwhite', 'E-reader with high-resolution display', 139.99),
                ('Apple AirPods Pro', 'Wireless earbuds with noise cancellation', 249.00),
                ('Samsung Galaxy Tab S8', 'High-performance Android tablet', 699.99),
                ('HP Envy 6055e', 'All-in-one wireless printer', 129.99),
                ('Seagate Portable 2TB', 'External hard drive for backup', 62.99),
                ('WD My Passport SSD', 'Portable solid state drive', 149.99),
                ('Roku Streaming Stick+', '4K streaming device', 49.99),
                ('Philips Hue Starter Kit', 'Smart LED lighting system', 179.99),
                ('Instant Pot Duo 7-in-1', 'Multi-use programmable pressure cooker', 99.99),
                ('Dyson V8 Absolute', 'Cordless stick vacuum cleaner', 399.99),
                ('Nespresso VertuoPlus', 'Coffee and espresso machine', 159.00),
                ('Cuisinart Air Fryer', 'Compact air fryer for healthy cooking', 99.95),
                ('Samsung 32" Curved Monitor', 'Full HD curved computer monitor', 229.99),
                ('LG UltraGear Gaming Monitor', '27" QHD gaming monitor', 349.99),
                ('Bose SoundLink Micro', 'Waterproof Bluetooth speaker', 99.00),
                ('Garmin Forerunner 55', 'GPS running smartwatch', 199.99),
                ('Tile Mate', 'Bluetooth tracker for keys and bags', 24.99),
                ('Anker PowerCore 10000', 'Portable USB power bank', 25.99),
                ('TP-Link Archer AX50', 'WiFi 6 router for fast internet', 149.99),
                ('Oral-B Pro 1000', 'Electric rechargeable toothbrush', 49.99),
                ('Brita Water Pitcher', 'Filtered water pitcher for home', 32.99),
                ('Yeti Rambler 20oz', 'Insulated stainless steel tumbler', 34.99),
                ('Hydro Flask 32oz', 'Wide mouth water bottle', 44.95),
                ('Apple Watch SE', 'Smartwatch with fitness tracking', 279.00),
                ('Samsung Galaxy Watch 5', 'Advanced health smartwatch', 299.99),
                ('Ring Video Doorbell 4', 'Smart doorbell with HD video', 199.99),
                ('Eufy RoboVac 11S', 'Slim robot vacuum cleaner', 229.99),
                ('Corsair K95 RGB Platinum', 'Mechanical gaming keyboard', 199.99),
                ('Razer DeathAdder V2', 'High-precision gaming mouse', 69.99),
                ('HyperX Cloud II', 'Gaming headset with surround sound', 99.99),
                ('ASUS ZenBook 14', 'Slim and lightweight laptop', 899.99),
                ('Lenovo ThinkPad X1 Carbon', 'Business ultrabook laptop', 1299.00),
                ('Microsoft Surface Pro 9', '2-in-1 tablet and laptop', 999.99),
                ('Bose QuietComfort 45', 'Wireless noise-cancelling headphones', 329.00),
                ('Sony PlayStation 5', 'Next-gen gaming console', 499.99),
                ('Xbox Series X', 'Powerful gaming console from Microsoft', 499.99);

## Query to calculate the total value of all products
SELECT SUM(price) AS total_value FROM products