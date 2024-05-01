document.addEventListener("DOMContentLoaded", function() {
  // Get references to elements
  const searchInput = document.getElementById("search-input");
  const filterSelect = document.getElementById("filter-select");
  const cardContainer = document.querySelector(".card-container");
  const cards = cardContainer.querySelectorAll(".card");

  // Event listener for search button
  document.getElementById("search-btn").addEventListener("click", function() {
      const searchText = searchInput.value.trim().toLowerCase();
      const filter = filterSelect.value.toLowerCase();

      cards.forEach(function(card) {
          const title = card.querySelector("h3").innerText.toLowerCase();
          const price = card.querySelector(".price").innerText.toLowerCase();
          const rating = card.querySelector(".rating").innerText.toLowerCase();

          // Check if title, price, or rating contains the search text based on the filter
          const matchesSearch = title.includes(searchText) || price.includes(searchText) || rating.includes(searchText);
          const matchesFilter = filter === "filter" || filter === "like" && title.includes(searchText)
              || filter === "comment" && price.includes(searchText)
              || filter === "rating" && rating.includes(searchText);

          // Show or hide card based on search result and filter
          card.style.display = matchesSearch && matchesFilter ? "block" : "none";
      });
  });
});



const books = [
  { title: "Science", price: "$19.99", rating: 5, imgSrc: "img/books/science book.jpg" },
  { title: "Mathematics", price: "$24.99", rating: 5, imgSrc: "img/books/maths book.jpg" },
  { title: "History", price: "$29.99", rating: 5, imgSrc: "img/books/history book.jpg" },
  { title: "Java", price: "$30.45", rating: 5, imgSrc: "img/books/java.jpg" },
  { title: "Python", price: "$19.99", rating: 5, imgSrc: "img/books/python book.jpg" },
  { title: "English", price: "$24.99", rating: 5, imgSrc: "img/books/english book.jpg" },
  { title: "DBMS", price: "$19.99", rating: 5, imgSrc: "img/books/dbms book.jpg" },
  { title: "HTML", price: "$24.99", rating: 5, imgSrc: "img/books/html book.jpg" },
  { title: "MUSIC", price: "$24.99", rating: 5, imgSrc: "img/books/musicbook.jpg" },
  { title: "ART", price: "$24.99", rating: 5, imgSrc: "img/books/art book.jpg" },

];

// Function to create card elements
function createCard(book) {
  const card = document.createElement("div");
  card.classList.add("card");

  const img = document.createElement("img");
  img.classList.add("book");
  img.src = book.imgSrc;
  img.alt = "Product Image";
  card.appendChild(img);

  const title = document.createElement("h3");
  title.textContent = book.title;
  card.appendChild(title);

  const price = document.createElement("p");
  price.classList.add("price");
  price.textContent = book.price;
  card.appendChild(price);

  const rating = document.createElement("div");
  rating.classList.add("rating");
  for (let i = 0; i < book.rating; i++) {
    const star = document.createElement("img");
    star.src = "img/books/star-removebg-preview.png";
    star.alt = "star";
    rating.appendChild(star);
  }
  card.appendChild(rating);

  const button = document.createElement("button");
  button.classList.add("button");
  button.type = "submit";
  button.textContent = "Add to Cart";
  card.appendChild(button);

  return card;
}

// Function to initialize the card container
function initCardContainer() {
  const container = document.getElementById("card-container");
  books.forEach(book => {
    const card = createCard(book);
    container.appendChild(card);
  });
}

// Call the initialization function
initCardContainer();

function submitOrder() {
  alert("Item added to cart!");
}