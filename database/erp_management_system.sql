DROP TABLE IF EXISTS OrderDetails;
DROP TABLE IF EXISTS Bill;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Client;

CREATE TABLE Products(
    productID INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    productPrice DECIMAL(10, 2) NOT NULL,
    productStock INT NOT NULL
);

CREATE TABLE Client(
    clientID INT AUTO_INCREMENT PRIMARY KEY,
    clientName VARCHAR(255) NOT NULL,
    clientEmail VARCHAR(255) NOT NULL,
    clientAddress VARCHAR(255) NOT NULL,
    clientPhoneNumber VARCHAR(20) NOT NULL,
    CONSTRAINT UC_clientEmail UNIQUE(clientEmail),
    CONSTRAINT UC_clientPhoneNumber UNIQUE(clientPhoneNumber)
);

CREATE TABLE Orders(
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    clientID INT NOT NULL,
    orderDate DATE NOT NULL,
    orderStatus VARCHAR(255) NOT NULL,
    orderDeliveryAddress VARCHAR(255) NOT NULL,
    clientPhoneNumber VARCHAR(20) NOT NULL,
    CONSTRAINT fk_Orders_Client
        FOREIGN KEY (clientID) REFERENCES Client(clientID)
);

CREATE TABLE Bill(
    billID INT AUTO_INCREMENT PRIMARY KEY,
    orderID INT NOT NULL,
    billIssueDate DATE NOT NULL,
    billSeries VARCHAR(3) NOT NULL,
    billNumber VARCHAR(5) NOT NULL,
    clientName VARCHAR(255) NOT NULL,
    clientAddress VARCHAR(255) NOT NULL,
    billTotalAmount DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_Bill_Order
        FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    CONSTRAINT UC_orderID UNIQUE(orderID),
    CONSTRAINT UC__billSeriesAndNumber UNIQUE(billSeries, billNumber)
);

CREATE TABLE OrderDetails(
    orderDetailsID INT AUTO_INCREMENT PRIMARY KEY,
    orderID INT NOT NULL,
    productID INT NOT NULL,
    orderDetailsQuantity INT NOT NULL,
    sellingPriceAtTheMoment DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_OrderDetails_Orders
        FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    CONSTRAINT fk_OrderDetails_Products
        FOREIGN KEY (productID) REFERENCES Products(productID)
);


