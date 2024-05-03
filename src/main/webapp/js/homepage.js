const carousel = document.getElementById('carousel');
const radios = document.querySelectorAll('input[type="radio"]');
let position = 0;
let rotateLeft = true;

function rotateItems() {
    position = rotateLeft ? (position === 4 ? 0 : position + 1) : (position === 0 ? 4 : position - 1);
    carousel.style.setProperty('--position', position + 1);
    radios[position].checked = true;
}

setInterval(() => {
    rotateItems();
    if (position === 4 && rotateLeft) {
        rotateLeft = false;
    } else if (position === 0 && !rotateLeft) {
        rotateLeft = true;
    }
}, 2000);


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


const scrollContainer1 = document.getElementById('scrollContainer1');
const scrollLeftButton1 = document.getElementById('scrollLeftButton1');
const scrollRightButton1 = document.getElementById('scrollRightButton1');

// Add error handling
if (!scrollContainer1 || !scrollLeftButton1 || !scrollRightButton1) {
    console.error('One or more elements not found');
} else {
    // Add event listeners to buttons
    scrollLeftButton1.addEventListener('click', scrollLeft1);
    scrollRightButton1.addEventListener('click', scrollRight1);
}

// Function to scroll left
function scrollLeft1() {
    console.log('Scrolling left');
    scrollContainer1.scrollBy({
        left: -100,
        behavior: 'smooth'
    });
}

// Function to scroll right
function scrollRight1() {
    console.log('Scrolling right');
    scrollContainer1.scrollBy({
        left: 100,
        behavior: 'smooth'
    });
}


const scrollContainer2 = document.getElementById('scrollContainer2');
const scrollLeftButton2 = document.getElementById('scrollLeftButton2');
const scrollRightButton2 = document.getElementById('scrollRightButton2');

// Add error handling
if (!scrollContainer2 || !scrollLeftButton2 || !scrollRightButton2) {
    console.error('One or more elements not found');
} else {
    // Add event listeners to buttons
    scrollLeftButton2.addEventListener('click', scrollLeft2);
    scrollRightButton2.addEventListener('click', scrollRight2);
}

// Function to scroll left
function scrollLeft2() {
    console.log('Scrolling left');
    scrollContainer2.scrollBy({
        left: -100,
        behavior: 'smooth'
    });
}

// Function to scroll right
function scrollRight2() {
    console.log('Scrolling right');
    scrollContainer2.scrollBy({
        left: 100,
        behavior: 'smooth'
    });
}



const scrollContainer3 = document.getElementById('scrollContainer3');
const scrollLeftButton3 = document.getElementById('scrollLeftButton3');
const scrollRightButton3 = document.getElementById('scrollRightButton3');

// Add error handling
if (!scrollContainer3 || !scrollLeftButton3 || !scrollRightButton3) {
    console.error('One or more elements not found');
} else {
    // Add event listeners to buttons
    scrollLeftButton3.addEventListener('click', scrollLeft3);
    scrollRightButton3.addEventListener('click', scrollRight3);
}

// Function to scroll left
function scrollLeft3() {
    console.log('Scrolling left');
    scrollContainer3.scrollBy({
        left: -100,
        behavior: 'smooth'
    });
}

// Function to scroll right
function scrollRight3() {
    console.log('Scrolling right');
    scrollContainer3.scrollBy({
        left: 100,
        behavior: 'smooth'
    });
}


