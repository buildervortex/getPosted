// JavaScript function for searching products
const search = () => {
    // Get the search input value and convert it to uppercase for case-insensitive search
    const searchbox = document.getElementById("search-item").value.toUpperCase();
    // Get the container for product items
    const storeitems = document.querySelector(".cards");
    // Get all product elements
    const products = document.querySelectorAll(".card");

    for (let i = 0; i < products.length; i++) {
        // Find the product name within each product element
        const productName = products[i].querySelector("p");

        if (productName) {
            // Get the text content of the product name
            const textValue = productName.textContent || productName.innerHTML;

            // Check if the search text is found in the product name
            if (textValue.toUpperCase().indexOf(searchbox) > -1) {
                // Display the product element if it matches the search
                products[i].style.display = "";
            } else {
                // Hide the product element if it doesn't match the search
                products[i].style.display = "none";
            }
        }
    }
}





const searchAuthor = () => {
    // Get the search input value and convert it to uppercase for case-insensitive search
    const searchbox = document.getElementById("search-Author").value.toUpperCase();

    // Get all rows in the table's body
    const rows = document.querySelectorAll(".table tbody tr");

    // Iterate over all rows
    rows.forEach(row => {
        // Get the name cell from the row (assuming the name is in the second column, index 1)
        const nameCell = row.querySelector("td.name");

        // Check if the name cell exists
        if (nameCell) {
            // Get the text content of the name cell and convert it to uppercase
            const nameText = nameCell.textContent.toUpperCase();

            // Compare the name text with the search input
            if (nameText.indexOf(searchbox) > -1) {
                // Show the row if it matches the search
                row.style.display = "";
            } else {
                // Hide the row if it doesn't match the search
                row.style.display = "none";
            }
        }
    });
};



// Bar Chart


const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'bar',
    data: {
        //Get the labels in database
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July' , 'August' , 'September ' , 'October' , 'November ' , 'December'],
        datasets: [{
            label: 'This Year Profit',
            data: [42, 59, 36, 52, 26, 38, 50,56,78,34,12,45],//Get the data in database
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
