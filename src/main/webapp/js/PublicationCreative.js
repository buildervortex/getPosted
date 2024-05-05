// Function to preview image before uploading
function previewImage(event) {
  var reader = new FileReader();
  reader.onload = function() {
    var thumbnail = document.getElementById('thumbnail');
    thumbnail.src = reader.result;
  }
  reader.readAsDataURL(event.target.files[0]);
}

// Function to display selected PDF file name
function displayFileName(event) {
  var input = event.target;
  var fileName = input.files[0].name;
  var label = input.nextElementSibling;
  label.innerHTML = fileName;
}

// Function to validate form fields
function validateForm() {
  var title = document.querySelector('input[type="text"]').value;
  var pdf = document.querySelector('.pdf').value;
  var description = document.querySelector('.description').value;
  var softCopyPrice = document.querySelector('input[type="number"]:nth-of-type(1)').value;
  var softCopyDiscount = document.querySelector('input[type="number"]:nth-of-type(2)').value;
  var date = document.querySelector('input[type="date"]').value;
  var userName = document.querySelector('input[type="text"]:nth-of-type(2)').value;
  var password = document.querySelector('input[type="password"]').value;
  var language = document.getElementById('language').value;
  var category = document.getElementById('category').value;

  // Basic form validation, you can customize this based on your requirements
  if (title === "" || pdf === "" || description === "" || softCopyPrice === "" || softCopyDiscount === "" || date === "" || userName === "" || password === "" || language === "" || category === "") {
    alert("Please fill in all fields.");
    return false;
  }

  // Prevent form submission
  event.preventDefault();

  // If all fields are filled, you can submit the form or perform further actions
  alert("Publication created successfully!");
  return true;
}
