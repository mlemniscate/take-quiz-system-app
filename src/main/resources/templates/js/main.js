$(document).ready(function () {
  // for submit sign up form
  $('#signUpForm').submit(function (event) {
    event.preventDefault();
    let values = `{
      "username" : "${$('#username').val()}",
      "password" : "${$('#password').val()}",
      "firstName" :  "${$('#firstName').val()}",
      "lastName" : "${$('#lastName').val()}",
      "email" :  "${$('#email').val()}",
      "gender" : "${$('input[type=radio][name=gender]:checked').val()}"
    }`;
    $.ajax({
      type: 'POST',
      url: `http://localhost:8080/${$(
        'input[type=radio][name=role]:checked'
      ).val()}/sign-up`,
      contentType: 'application/json',
      data: values,
      success: function (response) {
        if (response == 'USER_ALREADY_EXISTS') {
          $('#userExistAlert').removeClass('d-none');
          setTimeout(function () {
            $('#userExistAlert').addClass('d-none');
          }, 5000);
        } else {
          $('#userSignUpSuccessAlert').removeClass('d-none');
          setTimeout(function () {
            $('#userSignUpSuccessAlert').addClass('d-none');
          }, 10000);
        }
      },
    });
  });

  // for submit login form
  $('#loginForm').submit(function (event) {
    event.preventDefault();
    let values = `{
      "username" : "${$('#usernameLogin').val()}",
      "password" : "${$('#passwordLogin').val()}"
    }`;
    $.ajax({
      type: 'POST',
      url: `http://localhost:8080/user/login`,
      contentType: 'application/json',
      data: values,
      success: function (response) {
        if (response == 'IS_NOT_ACTIVE') {
          $('#userIsNotActiveAlert').removeClass('d-none');
          setTimeout(function () {
            $('#userIsNotActiveAlert').addClass('d-none');
          }, 6000);
        } else if (response == 'ADMIN') {
          sessionStorage.setItem('username', $('#usernameLogin').val());
          window.location.href = 'admin-page.html';
        } else if (response == 'STUDENT') {
        } else if (response == 'TEACHER') {
          sessionStorage.setItem('username', $('#usernameLogin').val());
          window.location.href = 'teacher-page.html';
        } else if (response == 'WRONG_PASSWORD') {
          $('#userWrongPasswordAlert').removeClass('d-none');
          setTimeout(function () {
            $('#userWrongPasswordAlert').addClass('d-none');
          }, 5000);
        } else if (response == 'WRONG_USERNAME') {
          $('#userWrongUsernameAlert').removeClass('d-none');
          setTimeout(function () {
            $('#userWrongUsernameAlert').addClass('d-none');
          }, 5000);
        }
      },
    });
  });

  // for navbar items click
  $('#signUpItem').click(function (event) {
    $('#signUpContainer').removeClass('d-none');
    $('#loginContainer').addClass('d-none');
  });
  $('#loginItem').click(function (event) {
    $('#loginContainer').removeClass('d-none');
    $('#signUpContainer').addClass('d-none');
  });
});
