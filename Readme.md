# 📦 Product API Testing Project

## 📌 Project Overview

This project focuses on testing REST APIs for a Product Inventory system built using Spring Boot. It includes both functional validation and automated API testing using Postman.

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Maven
* Postman (API Testing & Automation)

---

## 📂 Base URL

http://localhost:8080/products

---

## 🚀 API Endpoints

### 🔹 1. Create Product

* **Method:** POST
* **Endpoint:** /products

**Request Body:**
{
"name": "Laptop",
"price": 50000,
"quantity": 10
}

**Expected Result:**

* Product created successfully

---

### 🔹 2. Get All Products

* **Method:** GET
* **Endpoint:** /products

**Expected Result:**

* Returns list of products

---

### 🔹 3. Get Product by ID

* **Method:** GET
* **Endpoint:** /products/{id}

**Example:**
/products/1

**Expected Result:**

* Returns product details

---

### 🔹 4. Update Product (Full Update)

* **Method:** PUT
* **Endpoint:** /products/{id}

**Request Body:**
{
"name": "Mobile",
"price": 20000,
"quantity": 5
}

**Expected Result:**

* Product updated successfully

---

### 🔹 5. Partial Update Product

* **Method:** PATCH
* **Endpoint:** /products/{id}

**Request Body (example):**
{
"price": 18000
}

**Expected Result:**

* Selected fields updated

---

### 🔹 6. Delete Product

* **Method:** DELETE
* **Endpoint:** /products/{id}

**Expected Result:**

* 200 → Deleted successfully
* 404 → Product not found

---

### 🔹 7. Header Validation API

* **Method:** GET
* **Endpoint:** /products/headers

**Required Headers:**

* client: web
* version: 1.0
* Authorization: Bearer <token>

**Expected Results:**

* Success → Returns client, version, user
* Unauthorized → Missing/invalid token
* Invalid or Expired Token

---

## 🤖 Postman Automation

API tests are automated using Postman scripts.

### 🔹 Collection Includes:

* CRUD API requests
* Header validation API
* Positive & Negative scenarios

---

### 🔹 Sample Test Script (Postman)

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response is not empty", function () {
    pm.expect(pm.response.text()).to.not.be.empty;
});
```

---

### 🔹 Automated Validations

* Status code validation
* Response body validation
* JSON field validation
* Header validation
* Negative testing

---

### 🔹 Running Automation

1. Open Postman
2. Import collection
3. Click **Runner**
4. Run all requests

---

## 🧪 Sample Test Scenario

**Test Case:** Verify Create Product

**Steps:**

1. Send POST request
2. Validate status = 200/201
3. Validate response contains product details

---

## 📊 Project Highlights

* Full CRUD API testing
* Postman-based automation
* Header authentication testing
* Real-time validation scripts
* Interview-ready project

---

## ▶️ How to Run

1. Start Spring Boot application
2. Open Postman
3. Run collection using Runner
4. Check test results

---

## 👨‍💻 Author

Vishnuvardhan P
