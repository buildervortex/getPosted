// JavaScript for form validation

document.addEventListener('DOMContentLoaded', function () {
  const form = document.querySelector('form');

  form.addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the default form submission

    // Validate form fields
    const firstName = document.querySelector('input[type="text"][placeholder="Enter your first name"]');
    const middleName = document.querySelector('input[type="text"][placeholder="Enter your middle name"]');
    const lastName = document.querySelector('input[type="text"][placeholder="Enter your last name"]');
    const dob = document.querySelector('input[type="date"][placeholder="Enter birth date"]');
    const email = document.querySelector('input[type="email"][placeholder="Enter your email"]');
    const mobileNumber = document.querySelector('input[type="number"][placeholder="Enter mobile number"]');
    const username = document.querySelector('input[type="text"][placeholder="Enter your user name"]');
    const password = document.querySelector('input[type="password"][placeholder="Enter your password"]');
    const country = document.getElementById('country');
    const skills = document.getElementById('skills');

    // Check if any field is empty
    if (
      firstName.value === '' ||
      middleName.value === '' ||
      lastName.value === '' ||
      dob.value === '' ||
      email.value === '' ||
      mobileNumber.value === '' ||
      username.value === '' ||
      password.value === '' ||
      country.value === '' ||
      skills.value === ''
    ) {
      alert('Please fill in all fields.'); // Alert if any field is empty
    } else {
      alert('Registration Successful!'); // Alert for successful registration
      form.submit(); // Submit the form if all fields are filled
    }
  });
});

  

function previewImage(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function(){
      var img = document.getElementById('thumbnail');
      img.src = reader.result;
    };
    reader.readAsDataURL(input.files[0]);
  }