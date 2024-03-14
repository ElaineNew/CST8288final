/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();
    // Perform form validation here
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let userType = document.getElementById("userType").value;

    // Basic validation example: check if all fields are filled
    if (name && email && password && userType) {
        // Send registration data to server
        console.log("Registration data:", name, email, password, userType);
        // Here you would send the registration data to your backend server
    } else {
        alert("Please fill in all fields");
    }
});

document.getElementById("inventoryForm").addEventListener("submit", function(event) {
    event.preventDefault();
    // Perform form validation here
    let itemName = document.getElementById("itemName").value;
    let quantity = document.getElementById("quantity").value;
    let expirationDate = document.getElementById("expirationDate").value;

    // Basic validation example: check if all fields are filled
    if (itemName && quantity && expirationDate) {
        // Create a new inventory item object
        let newItem = {
            itemName: itemName,
            quantity: quantity,
            expirationDate: expirationDate
        };

        // Send the new item data to the server (simulated here)
        console.log("New inventory item:", newItem);
        // Here you would send the new item data to your backend server for storage

        // Clear form fields after submission
        document.getElementById("itemName").value = "";
        document.getElementById("quantity").value = "";
        document.getElementById("expirationDate").value = "";
    } else {
        alert("Please fill in all fields");
    }
    
    // Check for surplus food items
    checkSurplusItems();
});

function checkSurplusItems() {
    let today = new Date();
    today.setDate(today.getDate() + 7); // Get date 7 days from now

    let items = document.querySelectorAll("#surplusList li");
    items.forEach(item => item.remove());

    // Fetch inventory items
    let inventoryItems = []; // Simulated inventory items
    // Add your logic to fetch inventory items from the server

    // Check for surplus items
    inventoryItems.forEach(item => {
        let expirationDate = new Date(item.expirationDate);
        if (expirationDate <= today) {
            let listItem = document.createElement("li");
            listItem.textContent = `${item.itemName} - Quantity: ${item.quantity}`;
            document.getElementById("surplusList").appendChild(listItem);
        }
    });
}


