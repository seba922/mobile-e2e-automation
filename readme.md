# Appium Mobile E2E Automation

This project is an end-to-end automation framework for mobile applications, built with **Appium**, **Selenium**, **Java**, and **Maven**. Code structure should be easy to understand.

## Features

- Clean test structure using the Page Object Model (POM)
- Easy configuration for Android
- Helper methods and action wrappers for reusable code

---

## Project Requirements Checklist

All items from the list below have been completed:

### Basic Requirements

- [x] Implemented the Page Object Model (POM) to structure and organize test code
- [x] Created configuration files for Android or iOS to manage platform-specific settings
- [x] Used environment variables to dynamically manage key parameters such as platform version, application path, and more - you can provide APP_NAME and DEVICE_NAME

### Advanced (optional, but recommended)

- [x] Developed helper methods to simplify common test interactions
- [x] Implemented framework-level action wrappers to improve code reusability and maintainability
- [x] Optimized test execution by handling dynamic waits, retries, or custom logging

### Required Test Cases Automated

1. [x] Add the product to the shopping cart. Confirm that the added product is displayed.
2. [x] Open the shopping cart and continue. Fill in all required information fields.
3. [x] Navigate to the payment screen. Enter payment information and proceed to order overview.
4. [x] Ensure the Checkout screen displays the correct order details.
5. [x] Place the Order and complete the purchase process.

### Additional Test Cases (optional)

- [x] Validate error messages for empty required fields
- [x] Filter products by name, price
- [x] Remove items from the cart and verify the update

---

## How to Run


1. **Start the Appium Server**
2. **Run android emulator**  
3. **Execute mvn command mvn clean install**  
