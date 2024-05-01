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

