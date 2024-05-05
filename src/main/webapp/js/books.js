// Function to handle search button click
document.getElementById("search-btn").addEventListener("click", searchBooks);

// Function to handle search input field change
document.getElementById("search-input").addEventListener("input", searchBooks);

// Search function
function searchBooks() {
    var searchInput = document.getElementById("search-input").value.toLowerCase();
    var cards = document.getElementsByClassName("card");

    // Loop through all cards and hide those that don't match the search input
    Array.from(cards).forEach(function(card) {
        var title = card.getElementsByTagName("h2")[0].innerText.toLowerCase();
        if (title.indexOf(searchInput) > -1 || searchInput === "") {
            card.style.display = "flex";
        } else {
            card.style.display = "none";
        }
    });
}

// Function to handle add to cart button click
function submitOrder() {
    // Add your logic here to handle adding to cart
    alert("Item added to cart!");
}

// Function to handle filter by category
document.getElementById("category").addEventListener("change", function() {
    var selectedCategory = this.value.toLowerCase();
    var cards = document.getElementsByClassName("card");

    // Loop through all cards and hide those that don't match the selected category
    Array.from(cards).forEach(function(card) {
        var category = card.getElementsByTagName("h2")[0].innerText.toLowerCase();
        if (selectedCategory === "" || category === selectedCategory) {
            card.style.display = "flex";
        } else {
            card.style.display = "none";
        }
    });
});

// Function to handle filter by price
document.getElementById("priceFilter").addEventListener("change", function() {
    var selectedPrice = this.value;
    var cards = document.getElementsByClassName("card");

    // Loop through all cards and hide those that don't match the selected price range
    Array.from(cards).forEach(function(card) {
        var price = parseFloat(card.getElementsByClassName("price")[0].innerText.replace("$", ""));
        if (selectedPrice === "all" || isPriceInRange(price, selectedPrice)) {
            card.style.display = "flex";
        } else {
            card.style.display = "none";
        }
    });
});

// Function to check if price is within the selected range
function isPriceInRange(price, selectedPrice) {
    var range = selectedPrice.split("-");
    var minPrice = parseFloat(range[0]);
    var maxPrice = parseFloat(range[1]);
    return price >= minPrice && price <= maxPrice;
}

// Function to handle filter by rating
document.querySelectorAll('input[name="rating"]').forEach(function(radio) {
    radio.addEventListener('change', function() {
        var selectedRating = this.value;
        var cards = document.getElementsByClassName("card");

        // Loop through all cards and hide those that don't match the selected rating
        Array.from(cards).forEach(function(card) {
            var rating = parseFloat(card.getElementsByClassName("points")[0].innerText);
            if (rating >= selectedRating) {
                card.style.display = "flex";
            } else {
                card.style.display = "none";
            }
        });
    });
});

// Function to handle filter by date
document.getElementById("startDate").addEventListener("change", filterByDate);
document.getElementById("endDate").addEventListener("change", filterByDate);

function filterByDate() {
    var startDate = new Date(document.getElementById("startDate").value);
    var endDate = new Date(document.getElementById("endDate").value);
    var cards = document.getElementsByClassName("card");

    // Loop through all cards and hide those that don't match the selected date range
    Array.from(cards).forEach(function(card) {
        var date = new Date(card.getElementsByClassName("date")[0].innerText);
        if ((isNaN(startDate) || date >= startDate) && (isNaN(endDate) || date <= endDate)) {
            card.style.display = "flex";
        } else {
            card.style.display = "none";
        }
    });
}

// Function to handle filter by size
document.querySelectorAll('input[name="size"]').forEach(function(radio) {
    radio.addEventListener('change', function() {
        var selectedSize = this.value;
        var cards = document.getElementsByClassName("card");

        // Loop through all cards and hide those that don't match the selected size
        Array.from(cards).forEach(function(card) {
            var size = card.getElementsByClassName("book")[0].getAttribute("alt").toLowerCase();
            if (selectedSize === size) {
                card.style.display = "flex";
            } else {
                card.style.display = "none";
            }
        });
    });
});
