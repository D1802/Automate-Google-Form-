# Automate Google Form - _Project Author (Darpan Sarode)_

## Project Name and Description:
## Description:

This project aims to automate the process of filling out a Google Form using Java and Selenium WebDriver. The automation script navigates to the specified Google Form, fills in various fields with predefined or dynamically generated data, and submits the form.

## Features:

1. Automated Form Filling: The script fills in the required fields of the Google Form automatically, including text boxes, radio buttons, checkboxes, dropdowns, and date and time fields.
2. Dynamic Data Generation: Some fields, such as the current epoch time, current date minus 7 days, and current time, are dynamically generated to ensure accuracy and relevance.
3. Wrapper Functions: Each action, such as filling a text box or selecting an option from a dropdown, is encapsulated within a wrapper function for better organization and maintainability of the code.
4. Handling Pop-ups: The script handles pop-ups that may appear during the form-filling process, such as the pop-up encountered when changing the URL of a tab.
5. Success Message Confirmation: Upon successful submission of the form, the script verifies the presence of the success message on the website and prints it to the console.
Local Setup Instructions:



## Installation Instructions:
Clear steps to set up and run the project locally.

Clone the project repository from the provided link.
Set up the necessary dependencies, including Java, Selenium WebDriver, and TestNG.
Configure the project to use the appropriate WebDriver for your browser (e.g., ChromeDriver for Google Chrome).
Run the automation script to execute the form-filling process.
# java version 17
java --version
```

## Usage and Examples:
Provide sample code or usage scenarios to showcase how the project works.
> Example:
```
# to run the project
./gradlew run
```

## Important Links:
Details about useful external links
 
