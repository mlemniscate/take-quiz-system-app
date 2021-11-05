$(document).ready(function () {
  ajaxGetUsersAndShow(
    `http://localhost:8080/user/filter-users?firstName=&lastName=&gender=&role=&isActive=`
  );
  // when admin click on search users
  $('#searchUsers').click((event) => {
    console.log('hello');
    let firstName = $('#firstNameInput').val();
    let lastName = $('#lastNameInput').val();
    let isActive = $('#isActive').val();
    let gender = $('#gender').val();
    let role = $('#role').val();
    let url = `http://localhost:8080/user/filter-users?firstName=${firstName}&lastName=${lastName}&gender=${
      gender == null ? '' : gender
    }&role=${role == null ? '' : role}&isActive=${
      isActive == null ? '' : isActive
    }`;
    console.log(url);
    ajaxGetUsersAndShow(url);
  });

  // set user information to modal
  $(document).on('click', '.open-edit-user-modal', function () {
    dataUsername = $(this).data('username');
    dataFirstName = $(this).data('first_name');
    dataLastName = $(this).data('last_name');
    dataEmail = $(this).data('email');
    dataGender = $(this).data('gender');
    dataRole = $(this).data('role');
    dataIsActive = $(this).data('is_active');
    $('.modal-body #usernameEditInput').val(dataUsername);
    $('.modal-body #firstNameEditInput').val(dataFirstName);
    $('.modal-body #lastNameEditInput').val(dataLastName);
    $('.modal-body #emailEditInput').val(dataEmail);
    $('#isActiveEditInput').prop('checked', dataIsActive);
    $('#genderEditInput').val(dataGender);
    $('#roleEditInput').val(dataRole);
  });

  $('#editUserModal').on('hidden.bs.modal', function () {
    let user = `{
      "username" : "${$('#usernameEditInput').val()}",
      "firstName" : "${$('#firstNameEditInput').val()}",
      "lastName" : "${$('#lastNameEditInput').val()}",
      "email" : "${$('#emailEditInput').val()}",
      "gender" : "${$('#genderEditInput').val()}",
      "role" : "${$('#roleEditInput').val()}",
      "isActive" : ${$('#isActiveEditInput').is(':checked')}
    }`;
    ajaxSaveUserChanges('http://localhost:8080/user/edit', user);
    location.reload();
  });

  // when admin click for save user changes
  $('#saveEditUser').click((event) => {
    $('#editUserModal').modal('hide');
  });
});

function ajaxSaveUserChanges(urlSave, user) {
  $.ajax({
    type: 'POST',
    url: urlSave,
    contentType: 'application/json',
    data: user,
    success: function (response) {
      console.log('success');
    },
  });
}

// ajax request for getting users
function ajaxGetUsersAndShow(url) {
  $.ajax({
    type: 'GET',
    url: url,
    success: function (response) {
      $('#userContainer').html('');
      for (let userIndex = 0; userIndex < response.length; userIndex++) {
        const element = response[userIndex];
        let html = getShowUserHTML(element);
        $('#userContainer').append(html);
      }
    },
  });
}

// when admin click for edit user
function editUser(username) {}

// html creator for showing users
function getShowUserHTML(user) {
  return `<div class="card mb-4" style="width: 25rem; justify-self: center">
  <div class="card-body">
    <div class="card-title d-flex align-items-center">
      <div
        class="bg-secondary rounded-circle position-relative"
        style="width: 60px; height: 50px"
      >
        <span
          class="
            position-absolute
            top-10
            start-90
            translate-middle
            p-2
            bg-${user.isActive ? 'success' : 'danger'}
            border border-light
            rounded-circle
          "
        >
          <span class="visually-hidden">New alerts</span>
        </span>
      </div>
      <div class="d-flex justify-content-between w-100">
        <h5 class="mx-3">${user.firstName} ${user.lastName}</h5>
        <h5 class="mx-3 float-end"><i class="fas fa-${
          user.gender == 'm' ? 'male' : 'female'
        }"></i></h5>
      </div>
    </div>
    <div class="d-flex">
      <p class="mx-1">ایمیل:</p>
      <p>${user.email}</p>
    </div>
    <div class="d-flex justify-content-between">
      <h6>${user.role == 'STUDENT' ? 'دانشجو' : 'استاد'}</h6>
      <button class="btn btn-primary open-edit-user-modal" onclick="editUser('${
        user.username
      }')" data-bs-toggle="modal" data-bs-target="#editUserModal"
      data-username = ${user.username}
      data-first_name = '${user.firstName}'
      data-last_name = '${user.lastName}'
      data-email = '${user.email}'
      data-gender = '${user.gender}'
      data-role = '${user.role}'
      data-is_active = '${user.isActive}'
        ><i class="fas fa-user-edit"></i
      ></button>
    </div>
  </div>
</div>`;
}
