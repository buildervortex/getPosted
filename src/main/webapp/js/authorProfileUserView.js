const scrollContainer = document.getElementById('scrollContainer');
const scrollLeftButton = document.getElementById('scrollLeftButton');
const scrollRightButton = document.getElementById('scrollRightButton');

// Add error handling
if (!scrollContainer || !scrollLeftButton || !scrollRightButton) {
    console.error('One or more elements not found');
} else {
    // Add event listeners to buttons
    scrollLeftButton.addEventListener('click', scrollLeft);
    scrollRightButton.addEventListener('click', scrollRight);
}

// Function to scroll left
function scrollLeft() {
    console.log('Scrolling left');
    scrollContainer.scrollBy({
        left: -100,
        behavior: 'smooth'
    });
}

// Function to scroll right
function scrollRight() {
    console.log('Scrolling right');
    scrollContainer.scrollBy({
        left: 100,
        behavior: 'smooth'
    });
}
