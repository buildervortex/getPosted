function validateForm() {
    var firstName = document.getElementById('first-name').value;
    var middleName = document.getElementById('middle-name').value;
    var lastName = document.getElementById('last-name').value;
    var id = document.getElementById('id').value;
    var userName = document.getElementById('user-name').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var bio = document.getElementById('bio').value;
    var phoneNumber = document.getElementById('phone-number').value;
    var dateOfBirth = document.getElementById('date-of-birth').value;
    var country = document.getElementById('country').value;
    var skills = document.getElementById('skills').value;

    
    if (firstName === '' || middleName === '' || lastName === '' || id === '' || userName === '' ||
        email === '' || password === '' || bio === '' || phoneNumber === '' ||
        dateOfBirth === '' || country === '' || skills === '') {
        alert('Please fill in all fields.');
        return;
    }

    
    var validationMessages = document.getElementsByClassName('validation-message');
    for (var i = 0; i < validationMessages.length; i++) {
        validationMessages[i].innerText = '';
    }

    
    if (firstName === '') {
        document.getElementById('first-name-validation').innerText = 'Please enter your first name.';
        return;
    }

    if (middleName === '') {
        document.getElementById('middle-name-validation').innerText = 'Please enter your middle name.';
        return;
    }

    if (lastName === '') {
        document.getElementById('last-name-validation').innerText = 'Please enter your last name.';
        return;
    }

    if (id === '') {
        document.getElementById('id-validation').innerText = 'Please enter your ID.';
        return;
    }

    if (userName === '') {
        document.getElementById('user-name-validation').innerText = 'Please enter your username.';
        return;
    }

    if (email === '') {
        document.getElementById('email-validation').innerText = 'Please enter your email.';
        return;
    }

    if (password === '') {
        document.getElementById('password-validation').innerText = 'Please enter your password.';
        return;
    }

    if (bio === '') {
        document.getElementById('bio-validation').innerText = 'Please enter your bio.';
        return;
    }

    if (phoneNumber === '') {
        document.getElementById('phone-number-validation').innerText = 'Please enter your phone number.';
        return;
    } else if (!/^\d{10}$/.test(phoneNumber)) { 
        document.getElementById('phone-number-validation').innerText = 'Phone number must be 10 digits.';
        return;
    }

    if (dateOfBirth === '') {
        document.getElementById('date-of-birth-validation').innerText = 'Please enter your date of birth.';
        return;
    }

    if (country === '') {
        document.getElementById('country-validation').innerText = 'Please select your country.';
        return;
    }

    if (skills === '') {
        document.getElementById('skills-validation').innerText = 'Please select your skills.';
        return;
    }

    
    alert('Sign up successful!');

    
}
