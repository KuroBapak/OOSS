# OOSS

> A Shopee-like clone E-commerce app serving the basic needs of President University dorm residents.

---

## Table of Contents

* [About](#about)
* [Team](#team)
* [Tech Stack](#tech-stack)
* [Features](#features)
* [Installation](#installation)
* [Usage](#usage)
* [Database Schema](#database-schema)
* [Flowchart](#flowchart)
* [Pages User](#pagesU)
* [Pages Admins](#pagesA)
* [Contributing](#contributing)
* [License](#license)

---

## About

Hello sir, this is the captain speaking. Our group consists of 3 people and 1 AI:

> Project SSIP: My project is called **MoRiJul** (Moreno, Rifqi, Zuldan) a clone of Shopee E-commerce, built to serve the basic needs of people at President University Dorm.

* **Framework**: Laravel
* **CSS Framework**: Bootstrap
* **Session-based cart**: Users (buyers) can browse and shop without logging in; sessions manage cart data.

---

## Team

| Name                   | Role              |
| ---------------------- | ----------------- |
| Rifqi Athallah Rizaldi | Frontend & Design |
| Zuldan Fahrizal Rahman | Frontend & Design |
| Moreno Dwiputra        | Backend           |
| ChatGPT                | Backend Assistant |

---

## Tech Stack

* **Backend**: PHP, Laravel 11
* **Frontend**: HTML, Blade Templates, Bootstrap 5.3
* **Database**: MySQL
* **Session Management**: Laravel Sessions

---

## Features

* Product listing with variations (e.g., size)
* Add to cart (session storage)
* Chat Between Admin&User
* Unique code used for Chat Between admin&user also for updating order status
* Admin login and dashboard
* Order management: view buyer details, room number, payment type/status
* Payment options: Cash on Delivery (COD), QRIS (integration pending)
* Responsive design for desktop and mobile

---

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/KuroBapak/OOSS.git
   cd morijul-ooss
   ```

2. **Install dependencies**

   ```bash
   composer install
   npm install && npm run dev
   ```

3. **Environment setup**

   * Copy `.env.example` to `.env`
   * Set database credentials
   * Generate application key:

     ```bash
     php artisan key:generate
     ```

4. **Database migration & seeding**

   ```bash
   php artisan migrate --seed
   ```

5. **Serve the app**

   ```bash
   php artisan serve
   ```

---

## Usage

1. Open your browser and navigate to `http://127.0.0.1:8000`
2. Browse products and add items to your cart (no login required).
3. To access the admin dashboard, click the **Morijul Logo** button and log in with your admin credentials.
4. Manage orders and update payment status from the dashboard.

---

## Database Schema

![Database Schema](./images/DB.png)

| Table            | Column          | Type            | Key                     | Notes / Relations                                      |
| ---------------- | --------------- | --------------- | ----------------------- | ------------------------------------------------------ |
| **admins**       | id              | BIGINT UNSIGNED | PK                      |                                                        |
|                  | username        | VARCHAR(50)     |                         |                                                        |
|                  | password        | VARCHAR(255)    |                         |                                                        |
|                  | role            | VARCHAR(20)     |                         |                                                        |
|                  | created\_at     | TIMESTAMP       |                         |                                                        |
|                  | updated\_at     | TIMESTAMP       |                         |                                                        |
| **products**     | id              | BIGINT UNSIGNED | PK                      |                                                        |
|                  | name            | VARCHAR(100)    |                         |                                                        |
|                  | image           | VARCHAR(255)    |                         |                                                        |
|                  | price           | DECIMAL(10,2)   |                         |                                                        |
|                  | size            | ENUM            |                         | Values: 'kecil', 'sedang', 'besar'                     |
|                  | stock           | INT             |                         |                                                        |
|                  | created\_at     | TIMESTAMP       |                         |                                                        |
|                  | updated\_at     | TIMESTAMP       |                         |                                                        |
| **orders**       | id              | BIGINT UNSIGNED | PK                      |                                                        |
|                  | buyer\_name     | VARCHAR(100)    |                         |                                                        |
|                  | room\_number    | VARCHAR(10)     |                         |                                                        |
|                  | payment\_type   | VARCHAR(20)     |                         | e.g., 'COD', 'QRIS'                                    |
|                  | payment\_status | ENUM            |                         | Values: 'pending', 'paid', 'failed'; default 'pending' |
|                  | total\_amount   | DECIMAL(10,2)   |                         |                                                        |
|                  | order\_code     | VARCHAR(50)     |                         | Unique code                                            |
|                  | created\_at     | TIMESTAMP       |                         |                                                        |
|                  | updated\_at     | TIMESTAMP       |                         |                                                        |
| **order\_items** | id              | BIGINT UNSIGNED | PK                      |                                                        |
|                  | order\_id       | BIGINT UNSIGNED | FK → orders.id          | Defines items in an order                              |
|                  | product\_id     | BIGINT UNSIGNED | FK → products.id        |                                                        |
|                  | quantity        | INT             |                         |                                                        |
|                  | price           | DECIMAL(10,2)   |                         | Snapshot of product price at order time                |
|                  | created\_at     | TIMESTAMP       |                         |                                                        |
|                  | updated\_at     | TIMESTAMP       |                         |                                                        |
| **chats**        | id              | BIGINT UNSIGNED | PK                      |                                                        |
|                  | order\_code     | VARCHAR(50)     | FK → orders.order\_code | Enables chat per order                                 |
|                  | sender          | ENUM            |                         | Values: 'user','admin','delivery'                      |
|                  | message         | TEXT            |                         |                                                        |
|                  | created\_at     | TIMESTAMP       |                         |                                                        |
|                  | updated\_at     | TIMESTAMP       |                         |                                                        |
| **sessions**     | id              | VARCHAR(255)    | PK                      |                                                        |
|                  | user\_id        | BIGINT UNSIGNED |                         | Nullable (session owner)                               |
|                  | ip\_address     | VARCHAR(45)     |                         |                                                        |
|                  | user\_agent     | TEXT            |                         |                                                        |
|                  | payload         | LONGTEXT        |                         | Serialized session data                                |
|                  | last\_activity  | INT             |                         | UNIX timestamp of last activity                        |

**Relationships:**

* **orders** ➔ **order\_items**: One-to-many (orders.id → order\_items.order\_id)
* **products** ➔ **order\_items**: One-to-many (products.id → order\_items.product\_id)
* **orders** ➔ **chats**: One-to-many (orders.order\_code → chats.order\_code)

---

## Flowchart

![Flowchart](./images/Flowchart.png)

The flowchart illustrates the user journey and admin processes.

---

## PagesU

1. **Home**: Product showcases
2. **Product**: Detailed view, add to cart
3. **Cart**: Session-based cart summary
4. **Payment**: Choose COD or QRIS, proceed to order
5. **Order Code**: Randomly generated unique code used for chatting between admin and user
6. **Chat Interface**: User chat interface


![Home Page](./images/User_page.png)
![Product Page](./images/User_product.png)
![Cart Page](./images/Cart_page.png)
![Checkout Page](./images/Checkout_page.png)
![Code Page](./images/Order_code.png)
![Chat User Page](./images/User_chat.png)

## PagesA

1. **Admin Login**: Secure access for admin
2. **Admin Dashboard**: Order list, update payment status,chat to user
3. **Admin Chat**: Admin chat interface
4. **Admin Product**: List of added product to the catalog. Add,delete&edit
5. **Admin Edit**: Admin edit product view 
6. **Delivery Page**: Delivery man page if they asked to send the ordered items to the
costumer so the delivery man validate the code for updating order status
![Admins Login](./images/Login.png)
![Admin Page](./images/Admin_page.png)
![Admin Chat Page](./images/Admin_chat.png)
![Admin Product Page](./images/Admin_products.png)
![Admin Edit Page](./images/Edit_page.png)
![Delivery Page](./images/Delivery_page.png)


---

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

---

## License

This project is licensed under the Bamor License.
