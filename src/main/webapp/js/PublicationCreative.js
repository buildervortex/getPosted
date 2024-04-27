function displayFileName(event) {
  var input = event.target;
  var fileName = input.files[0].name;
  alert("Selected PDF: " + fileName);
}

function previewImage(event) {
  var input = event.target;
  var reader = new FileReader();
  reader.onload = function(){
    var img = document.getElementById('thumbnail');
    img.src = reader.result;
  };
  reader.readAsDataURL(input.files[0]);
}
  
  function validateForm() {
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const language = document.getElementById('language').value;
    const softCopyDiscount = document.getElementById('soft-copy-discount').value;
    const softCopyPrice = document.getElementById('soft-copy-price').value;
    const date = document.getElementById('date').value;
    const category = document.getElementById('category').value;
  
    if (!title || !description || !language || !softCopyDiscount || !softCopyPrice || !date || !category) {
      alert("Please fill in all fields.");
    } else {
      alert("Form submitted successfully!");
    }
  }
  
  function clearFields() {
    document.getElementById('title').value = "";
    document.getElementById('description').value = "";
    document.getElementById('language').value = "";
    document.getElementById('soft-copy-discount').value = "";
    document.getElementById('soft-copy-price').value = "";
    document.getElementById('date').value = "";
    document.getElementById('category').value = "";
  }
  